package tn.esprit.spring.wecare.services;



import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.wecare.entities.Activities;
import tn.esprit.spring.wecare.entities.Answers;
import tn.esprit.spring.wecare.entities.Event;
import tn.esprit.spring.wecare.entities.Questions;
import tn.esprit.spring.wecare.entities.Quizdt;
import tn.esprit.spring.wecare.entities.Quizz;
import tn.esprit.spring.wecare.entities.Quizzdto;
import tn.esprit.spring.wecare.entities.User;
import tn.esprit.spring.wecare.entities.Userdto;
import tn.esprit.spring.wecare.iservices.QuizIService;
import tn.esprit.spring.wecare.repositories.AnswerRepository;
import tn.esprit.spring.wecare.repositories.QuestionRepository;
import tn.esprit.spring.wecare.repositories.QuizRepository;
import tn.esprit.spring.wecare.repositories.UserRepository;

@Service
@Slf4j
public class QuizServiceImpl implements QuizIService {

	@Autowired
	QuizRepository quizzrepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerrepository;
	@Autowired
	UserRepository userrepo;
	
	
	@Override
	public List<Quizz> getallQuizz() {
		return (List<Quizz>) quizzrepository.findAll();}

	@Override
	public Quizz getQuizzById(Long id) {
		return quizzrepository.findById(id).get();}

	@Override
	public void addQuizz(Quizz quizz) {
    quizzrepository.save(quizz);}

	@Override
	public void deleteQuizz(Long id) {
		quizzrepository.delete(quizzrepository.findById(id).get());
    		
	}

	@Override
	public void updateQuizz(Quizz quizz,Long id) {
		Quizz qz= quizzrepository.findById(id).get();
		qz.setQuizzTitle(quizz.getQuizzTitle());
		qz.setQuizzDescription(quizz.getQuizzDescription());
		qz.setQuizzResult(quizz.getQuizzResult());
		quizzrepository.save(qz);
		
	}
	public Userdto Bestscoreforuser() {
		return quizzrepository.Bestscoreforuser();
	}

	@Override
	public int findAnswerIdCorrect( long idUser,long quizzId) {
		
		return quizzrepository.getquizzanswerbyuser(idUser, quizzId);
			
	}

	@Override
	public List<Quizdt>  AleatoireQuestions(long id) {
	return quizzrepository.quizzaleatoire(id);
	}

	@Override
	public void AffectquesToQuizz(long idQuizz, long idques) {
		
		Quizz quizz = quizzrepository.findById(idQuizz).orElseGet(null);
		Questions question = questionRepository.findById(idques).orElseGet(null);
		question.setQuizz(quizz);
		questionRepository.save(question);}

	@Override
	public void AffectUserToQuizz(long idQuizz, long idUser) {

		Quizz q = quizzrepository.findById(idQuizz).orElseGet(null);
		User user = userrepo.findById(idUser).orElseGet(null);
		List<User> users = q.getUser();
		users.add(user);
		q.setUser(users);
		quizzrepository.save(q);
		
	}

}
