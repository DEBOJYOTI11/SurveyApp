package com.example.surveyapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.surveyapp.model.Poll;

import java.util.List;
import java.util.Optional;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {
    Optional<Poll> findById(Long pollId);

    List<Poll> findAll();


}
