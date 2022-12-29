package com.github.hungnd11.miniactor.actors.thingsboard;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hungnd61
 */
public class Actor implements Runnable, ActorRef {

  private final ConcurrentLinkedQueue<ActorMsg> mailbox = new ConcurrentLinkedQueue<>();
  private final AtomicInteger on = new AtomicInteger(0);
  private Behavior behavior;
  private final ActorContext ctx;
  private final Executor executor;

  private Actor(final ActorContext ctx, final Behavior initial, final Executor executor) {
    this.ctx = ctx;
    this.behavior = Objects.requireNonNull(initial);
    this.executor = Objects.requireNonNull(executor);
  }

  public static ActorRef create(final ActorContext ctx, final Behavior initial, final Executor executor) {
    return new Actor(ctx, initial, executor);
  }

  @Override
  public void tell(ActorMsg msg) {
    if (mailbox.offer(msg)) {
      async();
    }
  }

  @Override
  public void run() {
    if (on.get() == 1) {
      try {
        final ActorMsg m = mailbox.poll();
        if (m != null) {
          // set the behavior for the next message
          behavior = behavior.handle(ctx, m);
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
        executor.execute(this);
      } catch (RuntimeException re) {
        on.set(0);
        throw re;
      }
    }
  }
}
