package com.hello.hellomessagequeue.step5;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/logs")
@RestController
public class LogController {

    private final CustomExceptionHandler customExceptionHandler;

    public LogController(CustomExceptionHandler customExceptionHandler) {
        this.customExceptionHandler = customExceptionHandler;
    }

    @GetMapping("/error")
    public ResponseEntity<String> errorAPI() {
        try {
            String value = null;
            value.getBytes(); // null pointer exception
        } catch (Exception e) {
            customExceptionHandler.handleException(e);
        }
        return ResponseEntity.ok("Controller NullPointerException 처리");
    }

    @GetMapping("/warn")
    public ResponseEntity<String> warnAPI() {
        try {
            throw new IllegalArgumentException("invalid argument 입니다.");
        } catch (Exception e) {
            customExceptionHandler.handleException(e);
        }
        return ResponseEntity.ok("Controller IllegalArgumentException 처리");
    }

    @PostMapping("/info")
    public ResponseEntity<String> infoAPI(@RequestBody String message) {
        customExceptionHandler.handleMessage(message);
        return ResponseEntity.ok("Controller Info 처리");
    }
}
