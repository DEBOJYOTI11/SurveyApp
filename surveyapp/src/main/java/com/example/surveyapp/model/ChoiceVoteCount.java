package com.example.surveyapp.model;

import java.util.List;

public class ChoiceVoteCount {

		private List<VotesCount> voteCount;
		private Long totalNumberOfVotes;
		
		public List<VotesCount> getVoteCount() {
			return voteCount;
		}
		public void setVoteCount(List<VotesCount> voteCount) {
			this.voteCount = voteCount;
		}
		public Long getTotalNumberOfVotes() {
			return totalNumberOfVotes;
		}
		public void setTotalNumberOfVotes(Long totalNumberOfVotes) {
			this.totalNumberOfVotes = totalNumberOfVotes;
		}
		public ChoiceVoteCount(List<VotesCount> voteCount, Long totalNumberOfVotes) {
			super();
			this.voteCount = voteCount;
			this.totalNumberOfVotes = totalNumberOfVotes;
		}
		public ChoiceVoteCount() {
			// TODO Auto-generated constructor stub
		}
	
	
}
