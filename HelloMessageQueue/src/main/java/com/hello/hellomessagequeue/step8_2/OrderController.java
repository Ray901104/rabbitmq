//package com.hello.hellomessagequeue.step8_2;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("/api/order")
//@RestController
//public class OrderController {
//
//    private final OrderProducer orderProducer;
//
//    public OrderController(OrderProducer orderProducer) {
//        this.orderProducer = orderProducer;
//    }
//
//    @GetMapping
//    public ResponseEntity<String> sendOrderMessage(@RequestParam("message") String message) {
//        orderProducer.sendShipping(message);
//        return ResponseEntity.ok("[Controller] Order Completed Message sent : " + message);
//    }
//}
