package com.quinbay.messaging;

import com.quinbay.messaging.service.implementation.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessagingService messagingService;

    @GetMapping
    public String sendMsg() {

        messagingService.sendEmail("This is test message");
        return "Message sent";
    }
}
