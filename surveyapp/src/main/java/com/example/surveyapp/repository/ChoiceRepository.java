package com.example.surveyapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.surveyapp.model.Choice;
import com.example.surveyapp.model.Poll;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoiceRepository extends CrudRepository<Choice, Long> {
    Optional<Choice> findByPollId(Long pollId);

    List<Choice> findAll();

}
