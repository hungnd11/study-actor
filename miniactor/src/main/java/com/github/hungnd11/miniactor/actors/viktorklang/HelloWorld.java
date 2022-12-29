package com.github.hungnd11.miniactor.actors.viktorklang;

import com.github.hungnd11.miniactor.actors.viktorklang.Actor.Address;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author hungnd61
 */
public class HelloWorld {

  public static void main(String[] args) {
    Executor executor = Executors.newCachedThreadPool();

    Address actor = Actor.create(self -> msg -> {
      System.out.println("self: " + self + " got msg " + msg);
      return Actor.Stay;
    }, executor);

    actor.tell("hello");
    actor.tell("world");
  }

}
