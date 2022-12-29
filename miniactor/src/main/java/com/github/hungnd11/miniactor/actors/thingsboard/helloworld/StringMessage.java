package com.github.hungnd11.miniactor.actors.thingsboard.helloworld;

import com.github.hungnd11.miniactor.actors.thingsboard.ActorMsg;
import com.github.hungnd11.miniactor.actors.thingsboard.ActorRef;
import java.util.Objects;

/**
 * @author hungnd61
 */
public class StringMessage implements ActorMsg {

  private final String content;
  private final ActorRef source;

  public StringMessage(final String content, final ActorRef source) {
    this.content = Objects.requireNonNull(content);
    this.source = Objects.requireNonNull(source);
  }

  public String getContent() {
    return content;
  }

  public ActorRef getSource() {
    return source;
  }
}
