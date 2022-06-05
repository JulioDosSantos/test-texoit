package com.texoit.test.services;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.texoit.test.entities.WorstMovie;
import com.texoit.test.repositories.WorstMovieRepository;

@Service
public class WorstMovieLoadService implements ApplicationRunner {

	@Autowired
	private WorstMovieRepository repository;
	
	@Value("${com.texoit.test.path-csv}")
	private String pathCSV;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Files.lines(Paths.get(pathCSV))
	    	.skip(1)
	    	.map(list -> list.split(";"))
	    	.map(str -> new WorstMovie(null, Integer.valueOf(str[0]), str[1], str[2], str[3], str.length == 5 ? str[4] : null))
	    	.forEach(repository::save);

	}

}
