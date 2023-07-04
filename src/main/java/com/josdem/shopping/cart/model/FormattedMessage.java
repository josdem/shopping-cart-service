package com.josdem.shopping.cart.model;

public class FormattedMessage {
  private String name;
  private String message;

  public FormattedMessage() {
    this.name = "Default";
    this.message = "Hello World!";
  }

  public FormattedMessage(String name) {
    this.name = name;
    this.message = "Hello " + this.name + "!";
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
