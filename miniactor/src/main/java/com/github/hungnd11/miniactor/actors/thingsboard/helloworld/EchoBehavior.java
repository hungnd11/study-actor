package com.github.hungnd11.miniactor.actors.thingsboard.helloworld;

import com.github.hungnd11.miniactor.actors.thingsboard.ActorContext;
import com.github.hungnd11.miniactor.actors.thingsboard.ActorMsg;
import com.github.hungnd11.miniactor.actors.thingsboard.Behavior;

/**
 * @author hungnd61
 */
public class EchoBehavior implements Behavior {
  private int times;

  public EchoBehavior(int times) {
    if (times < 1) {
      throw new IllegalArgumentException("times must be positive, found " + times);
    }
    this.times = times;
  }

  @Override
  public Behavior handle(ActorContext ctx, ActorMsg message) {
    if (message instanceof StringMessage) {
      if (times > 0) {
        times--;
        StringMessage sm = (StringMessage) message;
        System.out.printf("ECHO [time=%d]: %s\n", times, sm.getContent());
        sm.getSource().tell(sm);
        return this;
      } else {
        return Behavior.stopped();
      }
    }
    return this;
  }
}
