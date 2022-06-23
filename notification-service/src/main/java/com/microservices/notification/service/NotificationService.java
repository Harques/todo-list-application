package com.microservices.notification.service;

import com.microservices.notification.dto.NotificationResponse;
import com.microservices.notification.dto.ToDo;
import com.microservices.notification.dto.ToDoListResponse;
import com.microservices.notification.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.ArrayList;
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
                return new PasswordAuthentication("ysnnsl51@gmail.com", "mreprhkotuideuat");

            }
        });
        User[] users = restTemplate.getForObject("http://user-service:9501/", User[].class);
        int counter = 0;
        for(int i = 0; i<users.length; i++){
            ToDoListResponse[] toDoListResponses = restTemplate.getForObject("http://to-do-service:9502/list/" + users[i].getEmail(), ToDoListResponse[].class);
            List<NotificationResponse> notificationResponses = new ArrayList<>();
            for(int j = 0; j< toDoListResponses.length; j++){
                List<ToDo> filteredTodos = toDoListResponses[j].getToDos().stream().filter(t -> t.getDate() != null).collect(Collectors.toList());
                Date date = new Date();
                boolean first = true;
                for(int z = 0; z<filteredTodos.size(); z++){
                    Date filteredDate = filteredTodos.get(z).getDate();
                    filteredDate.setTime(filteredDate.getTime()-10800000);
                    if(filteredDate.getTime() - date.getTime() <= 86400000)
                    {
                        if(first){
                            counter++;
                            NotificationResponse temp = new NotificationResponse();
                            temp.setName(toDoListResponses[j].getName());
                            temp.getToDos().add(filteredTodos.get(z).getText());
                            notificationResponses.add(temp);
                            first = false;
                        }
                        else{
                            notificationResponses.get(counter-1).getToDos().add(filteredTodos.get(z).getText());
                        }
                    }
                }
            }
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("erdemcobanoglu1635@gmail.com", false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(users[i].getEmail()));
            msg.setSubject("Notification from To-Do Application");
            msg.setSentDate(new Date());


            String notifyMessage = "";
            for(int q = 0; q<notificationResponses.size(); q++){
                notifyMessage += "To-Do List Name: " + notificationResponses.get(q).getName();
                String todos = notificationResponses.get(q).getToDos().toString();
                notifyMessage += "- To-Do(s): " + todos.substring(1, todos.length()-1) + "\r\n";
            }
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("Your following to-dos are about to reach their deadlines:\r\n" + notifyMessage, "text/plain");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            msg.setContent(multipart);
            Transport.send(msg);

        }

    }

}
