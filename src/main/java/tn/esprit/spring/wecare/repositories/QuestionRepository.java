package tn.esprit.spring.wecare.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.wecare.entities.Questions;
import tn.esprit.spring.wecare.entities.Quizz;

@Repository
public interface QuestionRepository extends CrudRepository<Questions, Long> {
	List<Questions> findByQuizz(Quizz quizz);

}
