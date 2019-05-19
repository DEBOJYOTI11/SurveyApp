package com.example.surveyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.surveyapp.model.ChoiceVoteCount;
import com.example.surveyapp.model.VotesCount;
import com.example.surveyapp.repository.VoteRepository;

@Service
public class PollService {

		@Autowired
		VoteRepository voteRepository;
		
		public ChoiceVoteCount getPollResults(long pollId) {
			
			 List<VotesCount> voteCount = voteRepository.countByPollId(pollId);
			 
			 List<Long> totalNumberOfVotes = voteRepository.countTotalVotesCount(pollId);
			 
			 Long t = totalNumberOfVotes.get(0);
			 ChoiceVoteCount choiceVoteCount = new ChoiceVoteCount();
			 choiceVoteCount.setVoteCount(voteCount);
			 choiceVoteCount.setTotalNumberOfVotes(t);
			 
			 return choiceVoteCount;
		}
}
