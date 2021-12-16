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
public class MqspringApplicationRecv {

	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MqspringApplicationRecv.class, args);
	}

	@GetMapping("recv")
	String recv(){
	    try{
			Random random = new Random();
			int randomInteger = random.nextInt(5);
			System.out.println("Waiting..."+randomInteger);
			Thread.sleep(randomInteger*1000);
			System.out.println("Read message..."+randomInteger);
			String msgRev = jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssSSS");
			String nowis = simpleDateFormat.format(new Date());

			logMessage(msgRev, nowis);
			
	        return msgRev;
	    }catch(Exception ex){
	        ex.printStackTrace();
	        return "FAIL";
	    }
	}

	private void logMessage(String msg, String timestamp){
		System.out.println("Logging a message:"+msg+" - "+ timestamp);
	}

}

