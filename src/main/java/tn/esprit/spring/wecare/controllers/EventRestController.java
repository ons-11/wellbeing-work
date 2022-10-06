package tn.esprit.spring.wecare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.wecare.entities.Emaildto;
import tn.esprit.spring.wecare.entities.Event;
import tn.esprit.spring.wecare.entities.EventDt;
import tn.esprit.spring.wecare.entities.Eventdto;
import tn.esprit.spring.wecare.entities.User;
import tn.esprit.spring.wecare.entities.UserDtoo;
import tn.esprit.spring.wecare.entities.Userdto;
import tn.esprit.spring.wecare.entities.typef;
import tn.esprit.spring.wecare.iservices.EventIService;


@RestController
public class EventRestController {
	@Autowired
	EventIService eventIservice;
	
	
	@GetMapping("/Events")
	public List<Event> getallEvent(){
		return eventIservice.getallEvent();}
	
	@GetMapping("/Event/{id}")
	public Event getEventById(@PathVariable("id") Long id) {
		return eventIservice.getEventById(id);}
	
	@PostMapping("/ajoutEvent")
	public void addEvent(@RequestBody Event event) {
		eventIservice.addEvent(event);}
	
	@DeleteMapping("/supprimerEvent/{id}")
	public void deleteEvent(@PathVariable("id") Long id) {
		eventIservice.deleteEvent(id);
	}
	@PostMapping("/modifierEvent/{id}")
	public void updateEvent(@RequestBody Event event,@PathVariable("id") Long id) {
		eventIservice.updateEvent(event, id);
	}
	@GetMapping("/event-superieur-note")
	public Eventdto EventNoteSuperieur(){
		return eventIservice.EventNoteSuperieur();}
	
	@GetMapping("/budget/{id}")
	public float budgetEvent(@PathVariable("id") Long id) {
		return   eventIservice.budgetEvent(id);
	}
	
	@GetMapping("/meilleur_event_profit")
	public EventDt affichermeilleurprofit() {
		return eventIservice.affichermeilleurprofit();
	}
	@DeleteMapping("/deletebynote")
	public void deleteEvent() {
		eventIservice.deleteEventBynote();}
	
	@GetMapping("/events_meme_centreInteret")
	public List<Event> eventTypique(long id){
		return eventIservice.eventTypique(id);
	}
	@GetMapping("/type-event-plusparticipe")
	public typef typeplusparticipe(long id) {
		return eventIservice.typeplusparticipe(id);
	}
	@GetMapping("/user_de_event")
	public UserDtoo UserdelEvent() {
		return eventIservice.UserdelEvent();
	}
	
	@PutMapping("affect/{idevent}/{iduser}")
	public void AffectUserToEvent(@PathVariable("idevent") long idevent ,@PathVariable("iduser") long idUser)
	{
			eventIservice.AffectUserToEvent(idevent, idUser);}
	
	@PutMapping("affectact/{idevent}/{idact}")
	public void AffectActToEvent(@PathVariable("idevent") long idevent, @PathVariable("idact")  long idAct) {
		eventIservice.AffectActToEvent(idevent, idAct);
	}
	@GetMapping("user_email_event")
	public List<Emaildto> useremailevent(){
		return eventIservice.useremailevent();
	}
	
	@GetMapping("rappel user")
	public void rappelevent() {
		eventIservice.rappelevent();
		
	}

	

}
