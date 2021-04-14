package com.example.paul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
//@RequestScope
@SessionScope
public class Thing1 {
  final Thing2 thing2;
  private final ApplicationContext applicationContext;
  int count = 0;

  public Thing1(Thing2 thing2, ApplicationContext applicationContext) {
    this.thing2 = thing2;
    this.applicationContext = applicationContext;
  }

  public String getSomething() {
    Object bean = applicationContext.getBean("Bert");
    System.out.printf("The Bert bean is %s\n", bean);
    return String.format("%d: %s - %s", count++, thing2.getSomething(), bean);
  }
}
