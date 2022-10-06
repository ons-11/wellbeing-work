package tn.esprit.spring.wecare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.wecare.entities.Questions;
import tn.esprit.spring.wecare.iservices.QuestionsIService;

@RestController
public class QuestionRestController {
	
	@Autowired
	QuestionsIService questionIservice;

	@GetMapping("/Questions")
	public List<Questions> getallQuestions(){
		return questionIservice.getallQuestions();}
	
	@GetMapping("/Question/{id}")
	public Questions getQuestionById(@PathVariable("id") Long id) {
		return questionIservice.getQuestionById(id);}
	
	@PostMapping("/ajoutQuestion")
	public void addQuestion(@RequestBody Questions qst) {
		questionIservice.addQuestion(qst);}
	
	@DeleteMapping("/supprimerQuestion/{id}")
	public void deleteQuestion(@PathVariable("id")Long id) {
		questionIservice.deleteQuestion(id);}
	
	@PostMapping("/modifierQuestion/{id}")
	public void updateQuestion(@RequestBody Questions qst,@PathVariable("id") Long id) {
		questionIservice.updateQuestion(qst, id);
	}
}
