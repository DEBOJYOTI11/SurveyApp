package com.example.surveyapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "polls")
public class Poll{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String question;

    public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	@JsonManagedReference
    @OneToMany(
            mappedBy = "poll",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    ) 
    private List<Choice> choices = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

//    public List<Choice> getChoices() {
//        return choices;
//    }
//
//    public void setChoices(List<Choice> choices) {
//        this.choices = choices;
//    }


//    public void addChoice(Choice choice) {
//        choices.add(choice);
//        choice.setPoll(this);
//    }
//
//    public void removeChoice(Choice choice) {
//        choices.remove(choice);
//        choice.setPoll(null);
//    }
}
//package com.example.surveyapp.model;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//
//
///**
// * 
// * @author NDH00159
// *
// */
//
//@Entity
//@Table(schema="public")
//public class QuestionModel implements Serializable {
//
//		
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	@Column
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public int id;
//	
//	@Column
//	public String questionBody;
//	
//	
////	@Column
////	@ElementCollection
////	public List<String> answerOptions;
//	
//	@OneToMany(cascade = CascadeType.ALL,
//			   fetch = FetchType.EAGER,
//			   mappedBy = "question")
//	public List<PollsModel> poll;
//	
//	
//
//	public List<PollsModel> getPoll() {
//		return poll;
//	}
//
//	public void setPoll(List<PollsModel> poll) {
//		this.poll = poll;
//	}
//
//
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getQuestionBody() {
//		return questionBody;
//	}
//
//	public void setQuestionBody(String questionBody) {
//		this.questionBody = questionBody;
//	}
//
//
//
//	
//}
