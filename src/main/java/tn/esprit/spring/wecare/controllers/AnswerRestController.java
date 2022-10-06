package tn.esprit.spring.wecare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.wecare.entities.Answers;
import tn.esprit.spring.wecare.iservices.AnswerIService;


@RestController
public class AnswerRestController {

	@Autowired
	AnswerIService answerIservice;
	
	@ResponseBody
	@GetMapping("/AfficherAnswers")
	public List<Answers> getallAnswers(){
		 return answerIservice.getallAnswers();}
	
	@ResponseBody
	@GetMapping("/AfficherAnswer/{idAnswer}")
	public Answers getAnswerById(@PathVariable("idAnswer")Long id) {
		
		return answerIservice.getAnswerById(id);}
	
	@ResponseBody
	@PostMapping("/ajoutAnswer")
	public void addAnswer(@RequestBody Answers answer) {
		answerIservice.addAnswer(answer);}
	
	@ResponseBody
	@DeleteMapping("/supprimerAnswer/{id}")
	public void deleteAnswer(@PathVariable("id")Long id) {
		answerIservice.deleteAnswer(id);}
	
	@ResponseBody
	@PostMapping("/modifierAnswer/{id}")
	public void updateAnswer(@RequestBody Answers answer,@PathVariable("id") Long id) {
		answerIservice.updateAnswer(answer, id);}
	
	@PutMapping("/affect_answer_ques/{idq}/{ida}")
	public void AffectAnswerToquestion(@PathVariable("idq") long idquestion,@PathVariable("ida") long idanswer) {
		answerIservice.AffectAnswerToquestion(idquestion, idanswer);
	}
	@PutMapping("/affect-usertoanswer/{ida}/{idu}")
	public void AffectUserToAnswer(@PathVariable("ida") long idanswer,@PathVariable("idu") long iduser) {
		answerIservice.AffectUserToAnswer(idanswer, iduser);
	}
	
	
	
	
	
	
}
