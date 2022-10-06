package tn.esprit.spring.wecare.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.wecare.entities.Activities;
import tn.esprit.spring.wecare.entities.User;

@Repository
public interface ActivitéRepository extends CrudRepository<Activities, Long> {
	
	Activities findByUser(User user);

}
