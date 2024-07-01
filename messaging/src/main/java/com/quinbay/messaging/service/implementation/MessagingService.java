package com.quinbay.messaging.service.implementation;

import com.quinbay.messaging.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private static final Logger log = LoggerFactory.getLogger(MessagingService.class);
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmailId;

    @KafkaListener(topics = {"message.service"}, containerFactory = "kafkaListenerContainerFactory")
    void getMessages(MessageDto message) {

        log.info("getMessages : " + message.toString());
        sendEmail(message);
    }

    public void sendEmail(MessageDto message) {

        try {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(fromEmailId);
            simpleMailMessage.setTo(message.getRecipient();
            simpleMailMessage.setSubject(message.getSubject());
            simpleMailMessage.setText(message.getBody());

            javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e) {

            log.warn(e.getMessage());
        }

        log.info("Sent message");
    }
}
