package com.example.iotapp;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;

/**
 * @author hungnd61
 */
public class IotMain {

  public static void main(String[] args) {
    ActorRef<Void> ref = ActorSystem.create(IotSupervisor.create(), "iot-system");
  }

}
