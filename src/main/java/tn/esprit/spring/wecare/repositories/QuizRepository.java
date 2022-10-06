package tn.esprit.spring.wecare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.wecare.entities.Quizdt;
import tn.esprit.spring.wecare.entities.Quizz;
import tn.esprit.spring.wecare.entities.Userdto;

@Repository
public interface QuizRepository extends CrudRepository<Quizz, Long> {
	
	@Query(	value="SELECT first_name, last_name FROM user LEFT JOIN quizz on quizz.user_user_id=user.user_id ORDER BY quizz_result DESC LIMIT 1",
			nativeQuery = true )
	public Userdto Bestscoreforuser();
	

@Query(value="SELECT COUNT(answers.answer_id)AS nbr FROM quizz_user "
		+ "LEFT JOIN user ON user.user_id=user_user_id LEFT JOIN quizz ON quizz_quizz_id=quizz.quizz_id"
		+ " LEFT JOIN questions ON questions.quizz_quizz_id=quizz_id "
		+ "LEFT JOIN answers ON answers.questions_question_id=questions.question_id  "
		+ "WHERE user.user_id=:id AND quizz.quizz_id=:idq AND "
		+ "answers.answer=questions.correct_answer"
		,nativeQuery = true)
 int getquizzanswerbyuser(@Param("id") long UserId, @Param("idq") long QuizzId);

@Query(value="SELECT questions AS ques, answers.answer AS ans FROM questions "
		+ "LEFT JOIN answers ON answers.questions_question_id=question_id"
		+ " WHERE quizz_quizz_id=:id ORDER BY RAND();", nativeQuery = true )
 List<Quizdt> quizzaleatoire(@Param("id") long id);

}
