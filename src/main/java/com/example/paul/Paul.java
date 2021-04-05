package com.example.paul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Paul {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello From Java";
    }


    @GetMapping("/get")
    public String dogGet() {
        MyData myData = new MyData();

        String s = (String) GetUtil.get(myData, "a.b.s");
        System.out.println("S is: " + s);
        return s;

//        return myData.getA().getB().getS();
    }

}
