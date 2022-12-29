package com.github.hungnd11.miniactor.actors.thingsboard;

/**
 * The {@link Behavior} of an actor defines how it reacts to the messages that it receives.
 *
 * @author hungnd61
 */
public interface Behavior {

  /**
   * Process an incoming message and return the next behavior.
   *
   * @param ctx     the {@link ActorContext} context
   * @param message the incoming {@link ActorMsg} message
   * @return the next behavior.
   */
  Behavior handle(ActorContext ctx, ActorMsg message);

  static Behavior stopped() {
    // stopped: a behavior which does nothing, forever
    return new Behavior() {
      @Override
      public Behavior handle(ActorContext ctx, ActorMsg message) {
        return this;
      }
    };
  }
}
