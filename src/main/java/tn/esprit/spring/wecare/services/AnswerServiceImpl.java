package tn.esprit.spring.wecare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.wecare.entities.Answers;
import tn.esprit.spring.wecare.entities.Questions;
import tn.esprit.spring.wecare.entities.User;
import tn.esprit.spring.wecare.iservices.AnswerIService;
import tn.esprit.spring.wecare.repositories.AnswerRepository;
import tn.esprit.spring.wecare.repositories.QuestionRepository;
import tn.esprit.spring.wecare.repositories.UserRepository;

@Service
public class AnswerServiceImpl implements AnswerIService {
	
	@Autowired
	AnswerRepository answerRepo;
	@Autowired
	QuestionRepository questionrepository;
	@Autowired
	UserRepository userrepository;

	public List<Answers> getallAnswers(){
		return (List<Answers>) answerRepo.findAll();}
	
	public Answers getAnswerById(Long id) {
		return answerRepo.findById(id).get();}
	
	public void addAnswer(Answers answer) {
		answerRepo.save(answer);
	}
	
	public void deleteAnswer(Long id) {
		answerRepo.delete(answerRepo.findById(id).get());
	}
	public void updateAnswer(Answers answer,Long id) {
		Answers ans= answerRepo.findById(id).get();
		ans.setAnswer(answer.getAnswer());
		answerRepo.save(ans);
	}

	@Override
	public void AffectAnswerToquestion(long idquestion, long idanswer) {
		
		Questions question = questionrepository.findById(idquestion).orElseGet(null);
		Answers answer = answerRepo.findById(idanswer).orElseGet(null);
		answer.setQuestions(question);
		answerRepo.save(answer);
		
	}

	@Override
	public void AffectUserToAnswer(long idanswer, long iduser) {
		
		Answers answer = answerRepo.findById(idanswer).orElseGet(null);
	User user=userrepository.findById(iduser).orElse(null);
		answer.setUser(user);;
		answerRepo.save(answer);
		
	}}