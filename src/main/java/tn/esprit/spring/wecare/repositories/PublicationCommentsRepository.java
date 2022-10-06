package tn.esprit.spring.wecare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.wecare.entities.CommentedPost;
import tn.esprit.spring.wecare.entities.PublicationComments;

@Repository
public interface PublicationCommentsRepository extends JpaRepository<PublicationComments, Long> {
	
	@Query("from PublicationComments pc order by pc.CommentDate desc")
	List<PublicationComments> findAllByDate(Long id);
	
	@Query(value ="SELECT publication.publication_title AS pubtitle,\r\n"
			+ "COUNT(publication_comments.comment_id) occ FROM publication_comments\r\n"
			+ "LEFT JOIN publication ON publication.publication_id = publication_comments.publication_publication_id\r\n"
			+ "GROUP BY(publication_publication_id)\r\n"
			+ "ORDER BY  occ \r\n"
			+ "DESC LIMIT 1" , nativeQuery = true)
	CommentedPost MostCommentedPost ();

}
