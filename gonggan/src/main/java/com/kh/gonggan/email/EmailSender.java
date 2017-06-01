package com.kh.gonggan.email;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSender {
	@Autowired
	protected JavaMailSender emailSender;
	public void SendEmail(Email email) throws Exception{
		MimeMessage msg = emailSender.createMimeMessage();
		try {
			msg.setSubject(email.getSubject());
			msg.setFrom();
			msg.setText(email.getContent());
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
		} catch (MessagingException e) {
			e.printStackTrace();
		}try{
			emailSender.send(msg);
		}catch(MailException e){
			e.printStackTrace();
		}
	}

}
