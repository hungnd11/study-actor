package com.github.hungnd11.miniactor.actors.viktorklang;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class Actor {
  // Visibility is achieved by volatile-piggybacking of reads+writes to "on"

  // An Effect returns a Behavior given a Behavior
  public interface Effect extends Function<Behavior, Behavior> {
  }

  // A Behavior is a message (Object) which returns the behavior for the next message
  public interface Behavior extends Function<Object, Effect> {
  }


  // An Address is somewhere you can send messages
  public interface Address {
    Address tell(Object msg);
  }

  // Defining a composite of AtomicInteger, Runnable and Address
  public static abstract class AtomicRunnableAddress implements Runnable, Address {
    protected final AtomicInteger on = new AtomicInteger();
  }

  // Become is an Effect that returns a captured Behavior no matter what the old Behavior is
  public static Effect Become(final Behavior behavior) {
    return new Effect() {
      public Behavior apply(Behavior old) {
        return behavior;
      }
    };
  }

  // Stay is an Effect that returns the old Behavior when applied.
  public final static Effect Stay = new Effect() {
    public Behavior apply(Behavior old) {
      return old;
    }
  };

  // Die is an Effect which replaces the old Behavior with a new one which does nothing, forever.
  public final static Effect Die = Become(new Behavior() {
    public Effect apply(Object msg) {
      return Stay;
    }
  });

  public static Address create(final Function<Address, Behavior> initial, final Executor e) {
    // variable `a` is an address pointing to an actor
    final Address a = new AtomicRunnableAddress() {
      private final ConcurrentLinkedQueue<Object> mailbox = new ConcurrentLinkedQueue<>();
      private Behavior behavior = new Behavior() {
        public Effect apply(Object msg) {
          return (msg instanceof Address) ? Become(initial.apply((Address) msg)) : Stay;
        }
      };

      public Address tell(Object msg) {
        if (mailbox.offer(msg)) {
          async();
        }
        return this;
      }

      public void run() {
        if (on.get() == 1) {
          try {
            final Object m = mailbox.poll();
            if (m != null) {
              // set the behavior for the next message
              behavior = behavior.apply(m).apply(behavior);
            }
          } finally {
            on.set(0);
            async();
          }
        }
      }

      private void async() {
        if (!mailbox.isEmpty() && on.getAndSet(1) == 0) {
          try {
            e.execute(this);
          } catch (RuntimeException re) {
            on.set(0);
            throw re;
          }
        }
      }
    };
    return a.tell(a); // Make self-aware
  }
}