//package com.hello.hellomessagequeue.step1;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/messages")
//public class MessageController {
//
//    private final Sender sender;
//
//    public MessageController(Sender sender) {
//        this.sender = sender;
//    }
//
//    @PostMapping("/send")
//    public String sendMessage(@RequestBody String message) {
//        sender.send(message);
//        return "[#} Message sent successfully! " + message;
//    }
//}
