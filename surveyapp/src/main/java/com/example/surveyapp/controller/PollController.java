package com.example.surveyapp.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.surveyapp.model.Choice;
import com.example.surveyapp.model.ChoiceVoteCount;
import com.example.surveyapp.model.VotesCount;
import com.example.surveyapp.model.Poll;
import com.example.surveyapp.model.PostRequest;
import com.example.surveyapp.model.Vote;
import com.example.surveyapp.repository.ChoiceRepository;
import com.example.surveyapp.repository.PollRepository;
import com.example.surveyapp.repository.VoteRepository;
import com.example.surveyapp.services.PollService;
import com.example.surveyapp.services.WebSocketService;

@Transactional
@RestController
@RequestMapping(value = "/poll")
public class PollController {
		
	@Autowired
	PollRepository pollRepository;
	
	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	ChoiceRepository choiceRepository;
	
	@Autowired
	WebSocketService webSocketService;
	
	@Autowired
	PollService pollService;
	/**
	 * 
	 * @return
	 * Saves a poll(question) to db
	 */
	
	@GetMapping(value="/save")
	public String saveQuestion() {
		
		Poll poll = new Poll();
		poll.setQuestion("What is your favuorite color?");
		Choice choice1 = new Choice();
		choice1.setText("Yellow");
		choice1.setPoll(poll);
		
		Choice choice2 = new Choice();
		choice2.setText("Pink");
		choice2.setPoll(poll);
		
		Choice choice3 = new Choice();
		choice3.setText("Blue");
		choice3.setPoll(poll);
		
		poll.getChoices().add(choice1);
		poll.getChoices().add(choice2);
		poll.getChoices().add(choice3);
		
		pollRepository.save(poll);
		
		
		Vote vote1  = new Vote();
		
		vote1.setPoll(poll);
		vote1.setChoice(choice1);
		vote1.setVotesCount(2);
		
		Vote vote2  = new Vote();
		
		vote2.setPoll(poll);
		vote2.setChoice(choice2);
		vote2.setVotesCount(6);
		
		Vote vote3  = new Vote();
		
		vote3.setPoll(poll);
		vote3.setChoice(choice3);
		vote3.setVotesCount(12);
		
		List<Vote> voteList = new ArrayList<>();
		
		voteList.add(vote1);
		voteList.add(vote2);
		voteList.add(vote3);
		
		voteRepository.saveAll(voteList);
		
		return "success";
		
	}
	
	/**
	 * not used now
	 * @param poll
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value="/savePoll", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> savePoll(@RequestBody Poll poll){
		
		if(poll == null) {
			return new ResponseEntity<String>("Error in request body", HttpStatus.BAD_REQUEST);
		}
		
		pollRepository.save(poll);
		return new ResponseEntity<String>("success", HttpStatus.CREATED);
	}
	
	/** 
	 * 
	 * @return
	 * Returns a poll with pollId
	 */
	@CrossOrigin
	@GetMapping(value = "/getPoll/{pollId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPolls(@PathVariable("pollId") Long pollId) {
			
		Optional<Poll> poll = pollRepository.findById(pollId);
		
		if(poll.isPresent())
			return new ResponseEntity<Optional<Poll>>(poll, HttpStatus.OK);
		
		return new ResponseEntity<String>("No records found", HttpStatus.BAD_REQUEST);
	 }
	
	
	/** 
	 * 
	 * @return
	 * Get all polls from db
	 */
	@CrossOrigin
	@GetMapping(value = "/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPolls() {
			
		List<Poll> listPoll = pollRepository.findAll();
		
		
		//sending poll results using using web sockets
//		Long x = Long.parseLong("1");
//		List<VotesCount> choiceVoteCount = voteRepository.countByPollId(x);
		 
//		webSocketService.sendPollDataSocket(choiceVoteCount);
		 
		return new ResponseEntity<List<Poll>>(listPoll, HttpStatus.OK);
	 }
	
	/**
	 * 
	 * @param postRequest
	 * @return
	 * increments vote count
	 * get updated poll results from db
	 * push updated data via websockets
	 * [optional] returns updated data
	 */
	 @CrossOrigin
	 @RequestMapping(value = "/saveVote", method = RequestMethod.POST)
	 public ResponseEntity<?> createUser(@RequestBody PostRequest postRequest) {
		 
		 if(postRequest == null ) {
			 return new ResponseEntity<String>("invalid request body", HttpStatus.NO_CONTENT);
		 }
		 
		 
		 //update vote count
		 voteRepository.updateVotesCount(postRequest.getPollId(), postRequest.getChoiceId());
		 
		 //get updated vote results
		 ChoiceVoteCount choiceVoteCount = pollService.getPollResults(postRequest.getPollId());

		 //sent updated results to client via socket
		 webSocketService.sendPollDataSocket(choiceVoteCount);
		 
		 //sent updated results in response body
		 return new ResponseEntity<ChoiceVoteCount>(choiceVoteCount, HttpStatus.ACCEPTED);
	 }
	 
	 /**
	  * 
	  * @param pollId
	  * @return
	  * Returns poll results of a particular poll with pollID
	  */
	@CrossOrigin
	 @RequestMapping(value = "/getPollResults/{pollId}", method = RequestMethod.GET)
	 public ResponseEntity<?> getPollResults(@PathVariable("pollId") Long pollId) {
		 
		 if(pollId == null ) {
			 return new ResponseEntity<String>("Invalid poll ID", HttpStatus.NO_CONTENT);
		 }
		
		 ChoiceVoteCount choiceVoteCount = pollService.getPollResults(pollId);
		 
		 return new ResponseEntity<ChoiceVoteCount>( choiceVoteCount, HttpStatus.ACCEPTED);
	 }
	 
	 
}



