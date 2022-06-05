package com.texoit.test.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.test.dtos.PrizeBreakDTO;
import com.texoit.test.dtos.WorstFilmsAwardsIntervalDTO;
import com.texoit.test.entities.WorstMovie;
import com.texoit.test.enums.Ranges;
import com.texoit.test.services.WorstMovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1/worst-movies")
@Api(value="API REST for test company TexoIT.")
@CrossOrigin(origins = "*")
public class WorstMovieResource {
	
	@Autowired
	private WorstMovieService service;
	
	@ApiOperation(value="Returns all the worst movies")
	@GetMapping
	public ResponseEntity<List<WorstMovie>> findAll() {
		List<WorstMovie> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@ApiOperation(value="Returns the producer with the longest gap between two consecutive awards and the producer with the fastest two awards.")
	@GetMapping(value = "/minimum-maximum-ranges")
	public ResponseEntity<WorstFilmsAwardsIntervalDTO> findMinimumAndMaximumRanges() {
		return ResponseEntity.ok(service.findMinimumAndMaximumRanges());
	}
	
	@ApiOperation(value="Returns the producer with the longest interval between two consecutive awards or the one with the fastest two awards, depending on the parameter passed in the request.")
	@GetMapping(value = "/range/{range}")
	public ResponseEntity<List<PrizeBreakDTO>> returnRange(@PathVariable Ranges range) {
		return ResponseEntity.ok(service.findRange(range));
	}

}
