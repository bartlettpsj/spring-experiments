package com.example.paul;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@RestController
public class Paul {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello From Java";
    }

    @Autowired
    CustomerRepository repository;

    @GetMapping("/get")
    public String dogGet() {
        MyData myData = new MyData();

        var dataString = ReflectionToStringBuilder.toString(myData);
        String s = GetUtil.get(myData, "a.b.s");
        Integer i = GetUtil.get(myData, "a.b.i");

        String r = repository.toString();
        repository.save(new Customer("Paul", "Bartlett"));

        for (Customer customer : repository.findAll()) {
            System.out.printf("Data: %s", customer.toString());
        }

        return String.format("Value: %s Integer: %d Hashcode: %d Object: %s Repository %s", s, i, hashCode(), dataString, r);
    }

    @Bean
    String bert(CustomerRepository repository) {
        System.out.println("This is BERT");
        return "BERT";
    }

}
