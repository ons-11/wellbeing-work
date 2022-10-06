package tn.esprit.spring.wecare.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tn.esprit.spring.wecare.entities.MyLanguage;
import tn.esprit.spring.wecare.entities.Questions;
import tn.esprit.spring.wecare.entities.Quizdt;
import tn.esprit.spring.wecare.entities.Quizz;
import tn.esprit.spring.wecare.entities.Quizzdto;
import tn.esprit.spring.wecare.entities.User;
import tn.esprit.spring.wecare.entities.Userdto;
import tn.esprit.spring.wecare.entities.myresponsedto;
import tn.esprit.spring.wecare.iservices.QuizIService;
import tn.esprit.spring.wecare.repositories.QuestionRepository;


@RestController
public class QuizzRestController {

	@Autowired
	QuizIService quizzIservice;
	@Autowired
	QuestionRepository repo ;

	@GetMapping("/AllQuizz")
	public List<Quizz> getallQuizz(){
		return quizzIservice.getallQuizz();}
	
	@GetMapping("/Quizz/{id}")
	public Quizz getQuizzById(@PathVariable("id") Long id) {
		return quizzIservice.getQuizzById(id);}
	
	@PostMapping("/ajoutQuizz")
	public void addQuizz(@RequestBody Quizz quizz) {
		quizzIservice.addQuizz(quizz);}
	
	@DeleteMapping("/supprimerQuizz/{id}")
	public void deleteQuizz(@PathVariable("id") Long id) {
		quizzIservice.deleteQuizz(id);}
	
	@PostMapping("/modifierQuizz/{id}")
	public void updateQuizz(@RequestBody Quizz quizz,@PathVariable("id") Long id) {
		quizzIservice.updateQuizz(quizz, id);}
	
	@GetMapping("/best-score")
	public Userdto Bestscoreforuser() {
		return quizzIservice.Bestscoreforuser();
		
	
		}
	
	@GetMapping("/correctanswer/{id}/{idquizz}")
	public int findAnswerIdCorrect(@PathVariable("id") long idUser, @PathVariable("idquizz")long quizzId) {
		return quizzIservice.findAnswerIdCorrect(idUser, quizzId);
	}
	@GetMapping("/translate/{las}/{lad}/{idQuestion}")
	public myresponsedto translatemyText(@PathVariable("las") MyLanguage las , @PathVariable("lad") MyLanguage lad  ,@PathVariable("idQuestion") long idQuestion) throws IOException
	{		Questions q = repo.findById(idQuestion).orElseGet(null);
		String mytxt = q.getQuestions();
	
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "source_language="+las+"&target_language="+lad+"&text="+mytxt);
		Request request = new Request.Builder()
			.url("https://text-translator2.p.rapidapi.com/translate")
			.post(body)
			.addHeader("content-type", "application/x-www-form-urlencoded")
			.addHeader("x-rapidapi-host", "text-translator2.p.rapidapi.com")
			.addHeader("x-rapidapi-key", "7c8ea86bc5msh7732aee4ee699dbp1e4b0djsn8e5f0d91eb3c")
			.build();
			Gson gson = new Gson();
		
		Response response = client.newCall(request).execute();
		myresponsedto trans = gson.fromJson(response.body().string(), myresponsedto.class);
		return trans;}
	
	@PutMapping("affectquesquiz/{idQuizz}/{idques}")
	public void AffectquesToQuizz(@PathVariable("idQuizz") long idQuizz,@PathVariable("idques") long idques) {
		quizzIservice.AffectquesToQuizz(idQuizz, idques);
	}
	@PutMapping("affectUserQuizz/{idq}/{idu}")
	public void AffectUserToQuizz(@PathVariable("idq") long idQuizz,@PathVariable("idu") long idUser) {
		quizzIservice.AffectUserToQuizz(idQuizz, idUser);
	}
	
	@GetMapping("question_aleatoire/{id}")
	public List<Quizdt> AleatoireQuestions(@PathVariable("id") long id) {
		return quizzIservice.AleatoireQuestions(id);}
	
	
	
}
