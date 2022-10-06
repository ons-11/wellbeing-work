package tn.esprit.spring.wecare.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Quizz {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long QuizzId;
	private String QuizzTitle;
	private String QuizzDescription;
	private int QuizzResult;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="quizz")
	public List<Questions> questions;
	
	@ManyToMany
	public List<User> user;
	
}
