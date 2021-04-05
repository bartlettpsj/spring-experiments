package com.example.paul;

import lombok.*;

@Data
public class MyData {
    public A a = new A();

    MyData() {
        System.out.printf("This is MyData constructor\n");
    }

    @Data
    public class A {
        private B b = new B();

        A() {
            System.out.printf("This is A constructor\n");
        }

        @Data
        public class B {
            private String s = "Hello Paul";
        }
    }
}
