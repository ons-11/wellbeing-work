package tn.esprit.spring.wecare.iservices;

import java.util.List;

import tn.esprit.spring.wecare.entities.ActiveEmployeee;
import tn.esprit.spring.wecare.entities.BestAndWorstPub;
import tn.esprit.spring.wecare.entities.Publication;
import tn.esprit.spring.wecare.entities.Theme;
import tn.esprit.spring.wecare.entities.User;

public interface PublicationIservice {
	 public Publication createPubs(Publication pub, Long idUser);
	
 public Publication createPub (Publication pub);
	 
	 public  List<Publication> findAllBypubTime();
	 
	 public  Publication  updatePub (Publication pub, Long id);
	 
	 public void deletePub( Long id);
	 
	 public void likeAPub(Long id , Long idUser);
	 
	 public void DislikeAPub(Long id , Long idUser);
	 
	public List<Publication> GetPublicationsByName(String name  );//search 
	
	public List<Publication> GetPublicationsByTheme(Theme theme);
	
	
	public List<BestAndWorstPub> BestPubeachMonth();
	
	public List<BestAndWorstPub> WorstPubeachMonth();
	
	public List<ActiveEmployeee> activeusereachMonth();
	 
	public Publication getpubByid(Long id);
	
	public List<User> getpublikes(Long idpub);

}
