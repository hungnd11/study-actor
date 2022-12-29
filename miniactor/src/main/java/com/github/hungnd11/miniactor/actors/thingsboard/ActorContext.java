package com.github.hungnd11.miniactor.actors.thingsboard;

/**
 * An Actor is given by the combination of a {@link Behavior} and a context in which this behavior is executed. An Actor
 * can perform the following actions when processing a message:
 * <p><ul>
 * <li> send a finite number of messages to other Actors it knows (by using {@link ActorRef#tell(ActorMsg)} method)
 * <li> create a finite number of Actors (by using {@link ActorContext#createChild(Behavior, String)} method)
 * <li> designate the behavior for the next message (by using {@link Behavior#handle(ActorContext, ActorMsg)} method
 * implicitly)
 * </ul></p>
 *
 * @author hungnd61
 */
public interface ActorContext {

  /**
   * Create a child Actor from the given {@link Behavior} with the given name.
   *
   * @param behavior the {@link Behavior} of the child actor.
   * @return the {@link ActorRef} to the child actor.
   */
  ActorRef createChild(Behavior behavior, String name);
}
