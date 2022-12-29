package com.github.hungnd11.miniactor.actors.thingsboard.helloworld;

import com.github.hungnd11.miniactor.actors.thingsboard.Actor;
import com.github.hungnd11.miniactor.actors.thingsboard.ActorRef;
import java.util.concurrent.Executors;

/**
 * @author hungnd61
 */
public class TalkerMain {

  public static void main(String[] args) {
    ActorRef talker = Actor.create(null, new TalkBehavior(), Executors.newFixedThreadPool(2));

    talker.tell(new GreetMessage("Hung"));
    talker.tell(new PraiseMessage("Hung"));
    talker.tell(new CelebrateMessage("Hung", 27));
  }
}
