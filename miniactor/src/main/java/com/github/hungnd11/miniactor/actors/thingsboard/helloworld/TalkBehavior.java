package com.github.hungnd11.miniactor.actors.thingsboard.helloworld;

import com.github.hungnd11.miniactor.actors.thingsboard.ActorContext;
import com.github.hungnd11.miniactor.actors.thingsboard.ActorMsg;
import com.github.hungnd11.miniactor.actors.thingsboard.Behavior;

/**
 * @author hungnd61
 */
public class TalkBehavior implements Behavior {

  public TalkBehavior() {
  }

  @Override
  public Behavior handle(ActorContext ctx, ActorMsg message) {
    if (message instanceof GreetMessage) {
      GreetMessage sm = (GreetMessage) message;
      System.out.printf("Hello %s!\n", sm.getName());
    } else if (message instanceof PraiseMessage) {
      PraiseMessage pm = (PraiseMessage) message;
      System.out.printf("%s, you're amazing!\n", pm.getName());
    } else if (message instanceof CelebrateMessage) {
      CelebrateMessage cm = (CelebrateMessage) message;
      System.out.printf("Here's to another %d years, %s!\n", cm.getAge(), cm.getName());
    }

    return this;
  }

}
