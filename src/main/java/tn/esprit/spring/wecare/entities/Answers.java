package tn.esprit.spring.wecare.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Answers {
	@Id
	@GeneratedValue 
	private Long AnswerId;
	private String Answer; 
	
	
	@ManyToOne
	@JsonIgnore
	Questions questions;
	
	@ManyToOne
	User user;

	
	

}
