//package com.hello.hellomessagequeue.step4;
//
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class NewsController {
//
//    private final NewsPublisher newsPublisher;
//
//    public NewsController(NewsPublisher newsPublisher) {
//        this.newsPublisher = newsPublisher;
//    }
//
//    @MessageMapping("/subscribe")
//    public void handleSubscription(@Header("newsType") String newsType) {
//        System.out.println("[#] newsType = " + newsType);
//
//        String newsMessage = newsPublisher.publish(newsType);
//        System.out.println("[#] newsMessage = " + newsMessage);
//    }
//}
