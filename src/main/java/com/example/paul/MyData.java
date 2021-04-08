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
            private int i = 100;
            private Integer j = 200;
            private double f = 2.0123;
            int k = 999;
            public int l = 786;
        }
    }
}
