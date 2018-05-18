package com.retinder.utils;

import java.util.Properties;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class mailHandler {

	final String email_account = "retinderTest@163.com";
	final String email_pwd = "1";
	final String to = "1@qq.com";
	final String authCode="1";

	public boolean sendHtmlMail(String content) {
		Session session = Session.getDefaultInstance(getProps());
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(email_account));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to)); // 加载收件人地址
			message.setSubject("[retinder inform]测试报告");
			Multipart multipart = new MimeMultipart();
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(content, "text/html;charset=utf-8");
			multipart.addBodyPart(contentPart);
			message.setContent(multipart);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			//transport.connect("smtp.126.com", email_account, email_pwd);
			transport.connect(email_account, authCode);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	Properties getProps() {
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.163.com");
		p.put("mail.smtp.auth", "true");
		return p;
	}
	
	public static void main(String[] args) {
		mailHandler mail=new mailHandler();
		mail.sendHtmlMail("xxxx");
	}

}
