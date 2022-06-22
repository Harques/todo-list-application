package com.microservices.notification.controller;

import com.microservices.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping(path="/")
public class NotificationController {
    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){ this.notificationService = notificationService;}

    @CrossOrigin("*")
    @GetMapping("send")
    public void sendMail() throws MessagingException, IOException {
        notificationService.sendEmail();
    }

}
