package tn.esprit.spring.wecare.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.wecare.entities.Answers;
import tn.esprit.spring.wecare.entities.User;

@Repository
public interface AnswerRepository extends CrudRepository<Answers, Long> {
	Answers findByUser(User user);

}
