package com.texoit.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.texoit.test.entities.WorstMovie;

@Repository
public interface WorstMovieRepository extends JpaRepository<WorstMovie, Long> {
	
	@Query("SELECT u FROM WorstMovie u WHERE u.winner = 'yes'")
	List<WorstMovie> findAllByWinner();

}
