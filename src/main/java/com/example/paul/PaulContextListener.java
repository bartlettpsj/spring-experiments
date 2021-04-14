package com.example.paul;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.context.support.RequestHandledEvent;

import java.util.logging.Level;

//public class PaulContextListener implements ApplicationListener<ContextRefreshedEvent> {
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent cse) {
//        System.out.println("Handling context re-freshed event. ");
//    }
//}

@Slf4j
@Component
public class PaulContextListener implements ApplicationListener<RequestHandledEvent> {
    @Override
    public void onApplicationEvent(RequestHandledEvent requestHandledEvent) {
        log.info("PAUL: Request Handled Event {}", requestHandledEvent.getDescription());
    }

    @EventListener
    public void onApplicationEvent2(ContextRefreshedEvent cse) {
        System.out.println("PAUL: Handling context refreshed event. ");
    }

    @TransactionalEventListener
    public void handleCustom(Object event) {
        System.out.println("PAUL Transaction Event.");
    }

}
