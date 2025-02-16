//package com.hello.hellomessagequeue.step3;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/notifications")
//public class NotificationController {
//
//    private final NotificationPublisher notificationPublisher;
//
//    public NotificationController(NotificationPublisher notificationPublisher) {
//        this.notificationPublisher = notificationPublisher;
//    }
//
//    @PostMapping
//    public String sendNotification(@RequestBody String message) {
//        notificationPublisher.publish(message);
//        return "[#] Notification Sent: " + message + "\n";
//    }
//}
