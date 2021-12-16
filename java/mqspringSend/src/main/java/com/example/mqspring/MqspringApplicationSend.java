package com.example.mqspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
@EnableJms
public class MqspringApplicationSend {

	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MqspringApplicationSend.class, args);
	}

	@GetMapping("send")
	String send(){
	    try{
			String randomStr = getRandomString();
	        jmsTemplate.convertAndSend("DEV.QUEUE.1", randomStr);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssSSS");
			String nowis = simpleDateFormat.format(new Date());
			logMessage(randomStr,nowis);
	        return "OK";
	    }catch(Exception ex){
	        ex.printStackTrace();
	        return "FAIL";
	    }
	}

	private void logMessage(String msg, String timestamp){
		System.out.println("Logging a message:"+msg+" - "+ timestamp);
	}

	private String getRandomString() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
	
		String generatedString = random.ints(leftLimit, rightLimit + 1)
		  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		  .limit(targetStringLength)
		  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		  .toString();
		
		return generatedString;

	}
}

