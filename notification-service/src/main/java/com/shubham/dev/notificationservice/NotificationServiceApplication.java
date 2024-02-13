package com.shubham.dev.notificationservice;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

	@Autowired
	private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent) throws MessagingException {
		//send out an email notification
		log.info("Received notification for Order - {}",orderPlacedEvent.getOrderNumber());
//		sendEmail(orderPlacedEvent.getOrderNumber(), "receiverxyz@gmail.com");

	}




//	private void sendEmail(String orderNumber, String s) throws MessagingException {
//		MimeMessage message=javaMailSender.createMimeMessage();
//		MimeMessageHelper helper=new MimeMessageHelper(message,true);
//		helper.setTo(s);
//		helper.setSubject("Order Notification");
//		helper.setText("Your order with number " + orderNumber + " has been placed successfully.");
//		javaMailSender.send(message);
//		log.info("Email notification sent for Order - {}",orderNumber);
//
//
//	}
}
