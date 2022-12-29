package com.github.hungnd11.miniactor.actors.thingsboard;

/**
 * An {@link ActorRef} is the address of an Actor.
 *
 * @author hungnd61
 */
public interface ActorRef {

  /**
   * Send a message to the Actor referenced by this {@link ActorRef}.
   *
   * @param msg the {@link ActorMsg} message instance
   */
  void tell(ActorMsg msg);
}
