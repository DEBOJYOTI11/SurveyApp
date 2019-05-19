package com.example.surveyapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.surveyapp.model.VotesCount;

@Controller
public class WebSocketController {

		@Autowired
		private final SimpMessagingTemplate template;

		public WebSocketController(SimpMessagingTemplate template) {
			super();
			this.template = template;
		}
		
		@CrossOrigin
		@MessageMapping("/send/message")
		@SendTo("/chat")
		public void onRecievedMessage(String message) {
			this.template.convertAndSend("/chat",String.format("%s - %s",new SimpleDateFormat("HH:mm:ss").format(new Date()),message));
		}
		
		
		
		
}
