package com.example.surveyapp.model;

public class PostRequest {

		private Long pollId;
		private Long choiceId;
		
		public PostRequest(Long pollId, Long choiceId) {
			super();
			this.pollId = pollId;
			this.choiceId = choiceId;
		}
		public Long getPollId() {
			return pollId;
		}
		public void setPollId(Long pollId) {
			this.pollId = pollId;
		}
		public Long getChoiceId() {
			return choiceId;
		}
		public void setChoiceId(Long choiceId) {
			this.choiceId = choiceId;
		}
}
