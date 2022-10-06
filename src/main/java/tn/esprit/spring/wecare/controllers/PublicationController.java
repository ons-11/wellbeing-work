package tn.esprit.spring.wecare.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

import antlr.StringUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tn.esprit.spring.wecare.entities.ActiveEmployeee;
import tn.esprit.spring.wecare.entities.BestAndWorstPub;
import tn.esprit.spring.wecare.entities.MyLanguage;
import tn.esprit.spring.wecare.entities.Publication;
import tn.esprit.spring.wecare.entities.Theme;
import tn.esprit.spring.wecare.entities.User;
import tn.esprit.spring.wecare.entities.myresponsedto;
import tn.esprit.spring.wecare.iservices.PublicationIservice;
import tn.esprit.spring.wecare.repositories.PublicationRepository;

@RestController
@RequestMapping("/actuality")
@CrossOrigin(origins="http://localhost:4200")
public class PublicationController {
	@Autowired
	PublicationIservice PublicationIservice;
	@Autowired
	PublicationRepository pubrepo;

	
	@PostMapping("/create-pub/{id-User}") 
	public Publication createPubs(@RequestBody Publication pub,@PathVariable("id-User") Long idUser)
	 {
		
		return PublicationIservice.createPubs(pub, idUser);
	}
	
	//http://localhost:8089/wecare/actuality/get-allpubsdate}
	
	@PostMapping("/create-pub") 
	public Publication createPub(@RequestBody Publication pub)
	 {
		
		return PublicationIservice.createPub(pub);
	}
	
	@GetMapping("/get-allpubsdate")
	public List<Publication> findAllBypubTime(){
		List<Publication> listPubs = PublicationIservice.findAllBypubTime();
		return listPubs;
	}
	@GetMapping("/get-pub/{pub-id}")
	public Publication getpubByid(@PathVariable("pub-id")Long id) {
		
		return PublicationIservice.getpubByid(id);
	}
	
	@DeleteMapping("/remove-pub/{pub-id}")
	public void deletePub(@PathVariable("pub-id")Long id) {
		PublicationIservice.deletePub(id);
	}
	
	
	
	@PutMapping("/update-pub/{pub-id}") 
	public Publication updatePub(@RequestBody Publication pub, @PathVariable("pub-id")Long id )
	 {
		
		return PublicationIservice.updatePub(pub, id);
	}
	
	@PostMapping("/likepost/{idPost}/{idUser}")
	public void likeAPub(@PathVariable("idPost")Long id, @PathVariable("idUser")Long idUser)
	{	
		PublicationIservice.likeAPub(id, idUser);
		
	}
	
	@PostMapping("/Dislikepost/{idPost}/{idUser}")
	public void DislikeAPub(@PathVariable("idPost")Long id, @PathVariable("idUser")Long idUser)
	{	
		PublicationIservice.DislikeAPub(id, idUser);
		
	}
	@GetMapping("/searchbyname/{name}")
	@ResponseBody
	public List<Publication> GetPublicationsByName(@PathVariable("name") String name) {
	
		return 	PublicationIservice.GetPublicationsByName(name);
	}


	@GetMapping("/searchbytheme/{theme}")
	@ResponseBody
	public List<Publication> GetPublicationsByTheme(@PathVariable("theme")Theme theme){
		 return PublicationIservice.GetPublicationsByTheme(theme);
	
	}
	
	
	@GetMapping("/likeslist/{id-post}")
	public  List<User> getpublikes (@PathVariable ("id-post") Long idpub) {
		return ( List<User>) PublicationIservice.getpublikes(idpub);
	}
	
	@GetMapping("/bestpubbymonth")
	public List<BestAndWorstPub> BestPubeachMonth()  {
		return PublicationIservice.BestPubeachMonth();
	}
	
	@GetMapping("/worstpubbymonth")
	public List<BestAndWorstPub> WorstPubeachMonth() {
		return PublicationIservice.WorstPubeachMonth();
	}
	
	@GetMapping("/activeUser")
	public  List<ActiveEmployeee> activeusereachMonth() {
		return PublicationIservice.activeusereachMonth();
	}
	@GetMapping("/translate/{las}/{lad}/{idpub}")
	public myresponsedto translatemyText(@PathVariable("las") MyLanguage las , @PathVariable("lad") MyLanguage lad  ,@PathVariable("idpub") Long idpub) throws IOException
	{	Publication pub = pubrepo.findById(idpub).get();
		String mytxt = pub.getPublicationTitle();
		String txt = pub.getPublicationDescription();
		
	
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "source_language="+las+"&target_language="+lad+"&text="+mytxt+txt);
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
		return trans;
	}
	
	
	
	
	
}

