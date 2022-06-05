package com.texoit.test.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.test.dtos.PrizeBreakDTO;
import com.texoit.test.dtos.WorstFilmsAwardsIntervalDTO;
import com.texoit.test.entities.WorstMovie;
import com.texoit.test.enums.Ranges;
import com.texoit.test.repositories.WorstMovieRepository;

@Service
public class WorstMovieService {

	@Autowired
	private WorstMovieRepository repository;

	public List<WorstMovie> findAll() {
		return repository.findAll();
	}

	public WorstFilmsAwardsIntervalDTO findMinimumAndMaximumRanges() {

		List<WorstMovie> movies = repository.findAllByWinner();
		Map<String, List<Integer>> producers = recoverWinningProducersAndTheirYears(movies);
		Map<Integer, List<String>> prizeRanges = recoverProducersByPremiumRange(producers);
		
		return WorstFilmsAwardsIntervalDTO.builder()
				.min(returnShorterInterval(prizeRanges, producers))
				.max(returnLongerRange(prizeRanges, producers))
				.build();

	}
	
	public List<PrizeBreakDTO> findRange(Ranges range) {

		List<WorstMovie> movies = repository.findAllByWinner();
		Map<String, List<Integer>> producers = recoverWinningProducersAndTheirYears(movies);
		Map<Integer, List<String>> prizeRanges = recoverProducersByPremiumRange(producers);
		
		if (Ranges.MINIMUM.equals(range)) {
			return returnShorterInterval(prizeRanges, producers);
		} 
		
		if (Ranges.MAXIMUM.equals(range)) {
			return returnLongerRange(prizeRanges, producers);
		}
		
		return new ArrayList<PrizeBreakDTO>();
	}
	
	private List<PrizeBreakDTO> returnShorterInterval(Map<Integer, List<String>> prizeRanges,
			Map<String, List<Integer>> producers) {
		
		Integer interval = new ArrayList<>(prizeRanges.keySet()).get(0);
		return createListDTO(prizeRanges, producers, interval);
		
	}
	
	private List<PrizeBreakDTO> returnLongerRange(Map<Integer, List<String>> prizeRanges,
			Map<String, List<Integer>> producers) {
		
		List<Integer> prizeRangesList = new ArrayList<>(prizeRanges.keySet());
		prizeRangesList.sort(Comparator.naturalOrder());

		Integer interval = prizeRangesList.get(prizeRanges.size() - 1);
		return createListDTO(prizeRanges, producers, interval);
		
	}

	private List<PrizeBreakDTO> createListDTO(Map<Integer, List<String>> prizeRanges,
			Map<String, List<Integer>> producers, Integer interval) {
		
		List<PrizeBreakDTO> dtos = new ArrayList<>();
		
		List<String> producersWithMinimumInterval = prizeRanges.get(interval);
		producersWithMinimumInterval.forEach(name -> {
			List<Integer> years = producers.get(name);
			PrizeBreakDTO dto = PrizeBreakDTO.builder()
				.producer(name)
				.interval(interval)
				.previousWin(years.get(0))
				.followingWin(years.get(1))
				.build();
			dtos.add(dto);
		});
		return dtos;
		
	}

	private Map<Integer, List<String>> recoverProducersByPremiumRange(Map<String, List<Integer>> producers) {
		
		Map<Integer, List<String>> prizeRanges = new HashMap<>();
		
		producers.forEach((key, value) -> {
			if (value.size() > 1) {
				int interval = value.get(1) - value.get(0);
				boolean constains = prizeRanges.containsKey(interval);
				
				if (!constains) {
					prizeRanges.put(interval, new ArrayList<>());
				}
				prizeRanges.get(interval).add(key);
			}
			
		});
			
		return prizeRanges;
		
	}

	private Map<String, List<Integer>> recoverWinningProducersAndTheirYears(List<WorstMovie> movies) {
		Map<String, List<Integer>> producers = new HashMap<>();

		movies.forEach(m -> {

			List<String> filmProducers = this.recoverProducers(m);

			filmProducers.forEach(producer -> {
				boolean constains = producers.containsKey(producer);
				if (!constains) {
					producers.put(producer, new ArrayList<>());
				}
				producers.get(producer).add(m.getYear());
			});

		});
		
		producers.forEach((key, value) -> {
			value.sort((p1, p2) -> Integer.compare(p1, p2));
		});
		
		return producers;
		
	}

	private List<String> recoverProducers(WorstMovie m) {
		
		List<String> producers = new ArrayList<>();

		Arrays.asList(m.getProducers().split(",")).forEach(p -> {
			if (p.contains("and ")) {
				Arrays.asList(p.split("and ")).forEach(prod -> producers.add(prod.trim()));
			} else {
				producers.add(p.trim());
			}

		});

		return producers;
		
	}

}
