package com.example.paul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
public class Thing2 {
//  final Bert bert;
//
//  Thing2(Bert bert) {
//    this.bert = bert;
//  }
  String something = "Hello World";
}
