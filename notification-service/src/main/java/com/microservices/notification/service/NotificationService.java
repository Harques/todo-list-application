package com.microservices.notification.service;

import com.microservices.notification.dto.ToDo;
import com.microservices.notification.dto.ToDoListResponse;
import com.microservices.notification.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;
    //@Scheduled(cron="0 0 * * *")
    public void sendEmail() throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("erdemcobanoglu1635@gmail.com", "jbasirvxmotxhvml");

            }
        });
        User[] users = restTemplate.getForObject("http://localhost:9501/", User[].class);
        for(int i = 0; i<users.length; i++){
            ToDoListResponse[] toDoListResponses = restTemplate.getForObject("http://localhost:9502/list/" + users[i].getEmail(), ToDoListResponse[].class);
            for(int j = 0; j< toDoListResponses.length; j++){
                List<ToDo> filteredTodos = toDoListResponses[j].getToDos().stream().filter(t -> t.getDate() != null).collect(Collectors.toList());
                Date date = new Date();
                Date filteredDate = filteredTodos.get(0).getDate();
                filteredDate.setTime(filteredDate.getTime()-10800000);
                System.out.println(filteredDate);
                System.out.println(date);
                //filteredTodos.stream().filter(t -> (new Date() - t.getDate().getDay()) == 1).collect(Collectors.toList());
            }
        }

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("erdemcobanoglu1635@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("erdemcobanoglu1635@gmail.com"));
        msg.setSubject("Notification from To-Do");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Notification for To-Do application", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart);
        Transport.send(msg);
    }

}
