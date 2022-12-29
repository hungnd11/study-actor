package com.github.hungnd11.miniactor.actors.thingsboard.helloworld;

import com.github.hungnd11.miniactor.actors.thingsboard.ActorMsg;

/**
 * @author hungnd61
 */
public class CelebrateMessage implements ActorMsg {

  private final String name;
  private final int age;

  public CelebrateMessage(final String name, final int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
