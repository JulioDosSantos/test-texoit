package com.texoit.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WORST_MOVIE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorstMovie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "YEARR", nullable = false)
	private Integer year;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "STUDIOS", nullable = false)
	private String studios;
	
	@Column(name = "PRODUCERS", nullable = false)
	private String producers;
	
	@Column(name = "WINNER", nullable = true)
	private String winner;


}
