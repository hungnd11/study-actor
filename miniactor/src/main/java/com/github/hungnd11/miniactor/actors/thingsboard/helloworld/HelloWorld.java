package com.github.hungnd11.miniactor.actors.thingsboard.helloworld;

import com.github.hungnd11.miniactor.actors.thingsboard.Actor;
import com.github.hungnd11.miniactor.actors.thingsboard.ActorRef;
import com.github.hungnd11.miniactor.actors.thingsboard.Behavior;
import java.util.concurrent.Executors;

/**
 * @author hungnd61
 */
public class HelloWorld {
  public static void main(String[] args) {
    Behavior initial = new EchoBehavior(100);
    ActorRef actor = Actor.create(null, initial, Executors.newFixedThreadPool(2));
    StringMessage msg = new StringMessage("Hello World!", actor);
    actor.tell(msg);
  }
}
