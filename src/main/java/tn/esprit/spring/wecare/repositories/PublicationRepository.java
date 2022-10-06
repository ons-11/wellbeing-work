package tn.esprit.spring.wecare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.wecare.entities.ActiveEmployeee;
import tn.esprit.spring.wecare.entities.BestAndWorstPub;
import tn.esprit.spring.wecare.entities.Publication;
import tn.esprit.spring.wecare.entities.Theme;
import tn.esprit.spring.wecare.entities.User;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long>{
	
	@Query("from Publication p order by p.PublicationDate desc")
	public List<Publication> findAllByDate();
	
	@Query("from Publication p order by p.PublicationDate desc")
	public Publication  findFirstByOrderByIdDesc();
	

	
	@Query(value = "SELECT publication.publication_title, publication.publication_description, user.first_name ,user.last_name FROM publication	LEFT JOIN user ON publication.user_user_id= user.user_id WHERE  first_name LIKE '%name' OR last_name LIKE '%name'" , nativeQuery = true)
	public List<Publication> findByname(String FirstName);
	
	List<Publication> findByUser(User user);
	
	List<Publication> findByTheme(Theme theme);
	


	@Query(value = "SELECT publication.publication_title AS name  , COUNT( publication_likes.publication_publication_id) occ FROM publication_likes LEFT JOIN publication ON publication.publication_id = publication_likes.publication_publication_id GROUP BY(publication_publication_id)", nativeQuery = true )
	List<BestAndWorstPub> bestpub();



	@Query(value = "SELECT publication.publication_title AS name ,COUNT( publication_dislikes.publication_publication_id) occ FROM publication_dislikes LEFT JOIN publication ON publication.publication_id=publication_dislikes.publication_publication_id GROUP BY(publication_publication_id)", nativeQuery = true )
	List<BestAndWorstPub> worstpub();

	@Query(value = "SELECT USER.first_name as name , USER.last_name as lname , USER.image as image , COUNT(publication_id) occ FROM user LEFT JOIN publication ON user_user_id= publication.user_user_id GROUP BY(user_user_id)", nativeQuery = true )
	List<ActiveEmployeee> activePerson();
	
	

}
