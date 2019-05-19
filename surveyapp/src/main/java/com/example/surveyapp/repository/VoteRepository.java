package com.example.surveyapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.surveyapp.model.VotesCount;
import com.example.surveyapp.model.Vote;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    

	/**
	 * 
	 * @param pollId
	 * @return
	 *  returns list of choice-votes pairs
	 */
	@Modifying
    @Query("SELECT NEW com.example.surveyapp.model.VotesCount(v.choice.text, v.votesCount) FROM Vote v WHERE v.poll.id = :pollId")
    List<VotesCount> countByPollId(@Param("pollId") Long pollId);

	
	/**
	 * 
	 * @param pollId
	 * @return
	 *  returns total votes count
	 */
	@Modifying
    @Query("SELECT SUM(v.votesCount) FROM Vote v WHERE v.poll.id = :pollId")
    List<Long> countTotalVotesCount(@Param("pollId") Long pollId);
    
    /**
     * 
     * @param choiceId
     * @param Long pollId 
     * Updates vote
     */
    @Modifying
    @Query("Update Vote v set v.votesCount = v.votesCount + 1 where v.poll.id=:pollId and v.choice.id = :choiceId")
    void updateVotesCount(@Param("pollId") Long pollId, @Param("choiceId") long choiceId);
    
//    
//    /**
//     * 
//     * @param pollIds
//     * @return
//     * returns list of vote objects
//     */
//    @Query("SELECT v FROM Vote v where v.poll.id in :pollIds")
//    List<Vote> findByPollIdIn(@Param("pollIds") List<Long> pollIds);
//
//    /**
//     * 
//     * @param pollId
//     * @return
//     */
//    @Query("SELECT v FROM Vote v where v.poll.id = :pollId")
//    Vote findByPollId(@Param("pollId") Long pollId);
}

