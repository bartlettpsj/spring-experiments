package com.example.paul;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

@Log
@RestController
public class Paul {
    final PaulConfiguration paulConfiguration;
    final CustomerRepository repository;
    final Thing1 thing1; // injected from constructor

    // Inject the config the old fashioned way
    @Value("${mail.hostname}")
    String hostName;
    @Value("${mail.from}")
    String from;

    public Paul(CustomerRepository repository, PaulConfiguration paulConfiguration, Thing1 thing1) {
        this.repository = repository;
        this.paulConfiguration = paulConfiguration;
        this.thing1 = thing1;
    }

    @GetMapping("/hello")
    public String sayHello() {
        log.info("This is a logger");
        return "Hello From Java";
    }

    @GetMapping("/something")
    public String getSomething() {
        return thing1.getSomething();
    }

    @GetMapping("/config")
    public String getConfig(@RequestParam(defaultValue = "") String configName) {
        log.info("ConfigName: " + configName);
        return String.format("%s - %s - %s", paulConfiguration.getHostName(), hostName, from);
    }

    @GetMapping("/props")
    public String getProps(@RequestParam(defaultValue = "") String propName) {
        log.info("Running props method");
        // return from system properties
        if (propName != null) {
            log.info(System.getProperties().toString());
            String prop = System.getProperty(propName);
            log.info("Prop is: " + prop);
            return prop;
        }

        return "No prop specified";
    }

    @Transactional
    @GetMapping("/get")
    public String doGet(@RequestParam(defaultValue = "firstDefault") String firstName, @RequestParam(defaultValue = "lastDefault") String lastName) {
        MyData myData = new MyData();

        var dataString = ReflectionToStringBuilder.toString(myData);
        String s = GetUtil.get(myData, "a.b.s");
        Integer i = GetUtil.get(myData, "a.b.i");
        String r = repository.toString();

        Date date = Date.valueOf(LocalDate.of(1966, Month.AUGUST, 14));

        // Save a record and list them
        repository.save(new Customer(firstName, lastName, date));

        // try to force division by zero and therefore rollback
//        Integer x = i - 100;
//        int w = i / x;

//        repository.save(new Customer("Another Record", "And His Mate", date));

        for (Customer customer : repository.findAll()) {
            System.out.printf("Data: %s\n", customer.toString());
        }

        return String.format("Value: %s Integer: %d Hashcode: %d Object: %s Repository %s", s, i, hashCode(), dataString, r);
    }

    @Bean
    public String Bert(CustomerRepository repository) {
        System.out.println("This is BERT - " + repository.toString());
        return "BERT";
    }

    @Getter
    @Setter
    @ToString
    public static class Fred {
        String s = "fred";
        int n = 123;
    }

    @GetMapping("/randomuser")
    public Object getUser() {
        Fred f = new Fred();

        Object o = WebClient.create("https://randomuser.me/api/").get().retrieve().bodyToMono(Object.class).block(); //.bodyToFlux(Object.class);
        log.info(o.toString());
        return o;
    }


}
