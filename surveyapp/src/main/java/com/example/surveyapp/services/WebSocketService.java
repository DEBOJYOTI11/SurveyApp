package com.example.surveyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.surveyapp.model.ChoiceVoteCount;
import com.example.surveyapp.model.VotesCount;

@Service
public class WebSocketService {

	@Autowired
	private final SimpMessagingTemplate template;
		
	public WebSocketService(SimpMessagingTemplate template) {
		super();
		this.template = template;
	}

	public void sendPollDataSocket(ChoiceVoteCount choiceVoteCount) {
		this.template.convertAndSend("/chat", choiceVoteCount);
	}
}
