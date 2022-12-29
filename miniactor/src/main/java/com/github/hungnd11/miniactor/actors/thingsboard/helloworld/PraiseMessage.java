package com.github.hungnd11.miniactor.actors.thingsboard.helloworld;

import com.github.hungnd11.miniactor.actors.thingsboard.ActorMsg;
import java.util.Objects;

/**
 * @author hungnd61
 */
public class PraiseMessage implements ActorMsg {

  private final String name;

  public PraiseMessage(final String name) {
    this.name = Objects.requireNonNull(name);
  }

  public String getName() {
    return this.name;
  }
}
