package tn.esprit.spring.wecare.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.wecare.entities.Favoris;
import tn.esprit.spring.wecare.entities.namedto;

@Repository
public interface FavorisRepository extends CrudRepository<Favoris, Long> {
	
	@Query(value="SELECT activitie_name AS name FROM `activities_user` "
			+ "LEFT JOIN user on activities_user.user_user_id=user.user_id "
			+ "LEFT JOIN activities ON activities_user.activities_activitie_id=activities.activitie_id"
			+ " WHERE activities.favorie=true "
			+ "AND user.user_id=:id",nativeQuery = true)
	namedto Actfavoris(@Param("id") long id);
	
	
	@Query(value="SELECT activitie_name FROM activities;",nativeQuery = true)
	Favoris favoriteact(long id);

}
