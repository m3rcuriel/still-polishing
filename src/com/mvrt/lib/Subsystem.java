package com.mvrt.lib;

public abstract class Subsystem implements StateMachine {

  protected String name;

  public Subsystem(String name) {
    this.name = name;
    SystemManager.getInstance().add(this);
  }

  @Override
  public String getName() {
    return name;
  }

  public abstract void reloadConstants();
}
