package com.hello.hellomessagequeue.step5;

import org.springframework.stereotype.Component;

@Component
public class CustomExceptionHandler {

    private final LogPublisher logPublisher;

    public CustomExceptionHandler(LogPublisher logPublisher) {
        this.logPublisher = logPublisher;
    }

    public void handleException(Exception e) {
        String message = e.getMessage();
        String routingKey;

        if (e instanceof NullPointerException) {
            routingKey = "error";
        } else if (e instanceof IllegalArgumentException) {
            routingKey = "warn";
        } else {
            routingKey = "error";
        }

        logPublisher.publish(routingKey, "Exception : " + message);
    }

    public void handleMessage(String message) {
        String routingKey = "info";
        logPublisher.publish(routingKey, "Info Log : " + message);
    }
}
