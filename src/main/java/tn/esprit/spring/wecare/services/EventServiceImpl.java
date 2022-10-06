package tn.esprit.spring.wecare.services;

import java.util.Date;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.wecare.entities.Activities;
import tn.esprit.spring.wecare.entities.Emaildto;
import tn.esprit.spring.wecare.entities.Event;
import tn.esprit.spring.wecare.entities.EventDt;
import tn.esprit.spring.wecare.entities.Eventdto;
import tn.esprit.spring.wecare.entities.User;
import tn.esprit.spring.wecare.entities.UserDtoo;
import tn.esprit.spring.wecare.entities.Userdto;
import tn.esprit.spring.wecare.entities.typef;
import tn.esprit.spring.wecare.iservices.EventIService;
import tn.esprit.spring.wecare.repositories.ActivitéRepository;
import tn.esprit.spring.wecare.repositories.EventRepository;
import tn.esprit.spring.wecare.repositories.UserRepository;

@Service
public class EventServiceImpl implements EventIService {

	
	@Autowired
	EventRepository eventrepository;
	@Autowired
	UserRepository userrepository;
	@Autowired
	ActivitéRepository activiterepository;
	@Autowired
	EmailSenderService eService;

	@Override
	public List<Event> getallEvent() {

		return (List<Event>) eventrepository.findAll();
	}

	@Override
	public Event getEventById(Long id) {
		return eventrepository.findById(id).get();
	}

	@Override
	public void addEvent(Event event) {
		 

     int cmpt =0;
		for(Event evnt :  eventrepository.findAll()) {
			if(evnt.getEventName().contains(event.getEventName())) 
			{
				cmpt++;
				} 
	}
		
		if (cmpt==0)
		{
			eventrepository.save(event);}}
		
	@Override
	public void deleteEvent(Long id) {
		eventrepository.delete(eventrepository.findById(id).get());}
	

	@Override
	public void updateEvent(Event event,Long id) {
		Event evt= eventrepository.findById(id).get();
		evt.setEventName(event.getEventName());
		evt.setStartDate(event.getStartDate());
		evt.setEndDate(event.getEndDate());
		evt.setNbPerson(event.getNbPerson());
		evt.setFull(event.isFull());
		evt.setNbPlaceDisponible(event.getNbPlaceDisponible());
		evt.setImages(event.getImages());
		evt.setType(event.getType());
		evt.setNote(event.getNote());
		evt.setDepenses(event.getDepenses());
		evt.setIsAccepted(event.getIsAccepted());
		evt.setPrice(event.getPrice());
		eventrepository.save(evt);
		
		
	}

	@Override
	public Eventdto EventNoteSuperieur() {
		 
		return eventrepository.PeriodeEvent();
	}

	@Override
	public void AcceptEvent(Long idEvent, Long idUser) {
		
		User user= userrepository.findById(idUser).get();
		Event event= eventrepository.findById(idEvent).get();
		
		if (event.getIsAccepted()==true)
		{
			
		}
		
		
	}

	@Override
	public float budgetEvent(Long id) {
		
		Event e=eventrepository.findById(id).orElseGet(null);
		Float result= eventrepository.countusers(id)*e.getPrice();
		
		Float budgetnet = result-e.getDepenses();
		e.setProfit(budgetnet);
		eventrepository.save(e);
		
	    return  budgetnet;}

	@Override
	public EventDt affichermeilleurprofit() {
		
		EventDt evtDt = null;
		for (Event e:eventrepository.findAll()) {
			if (e.getProfit()==0) {
				budgetEvent(e.getEventId());
				eventrepository.save(e);}
				
		evtDt= eventrepository.affichermeilleurprofitevent();}
		
	
		return evtDt;}

	@Override
	public void deleteEventBynote() {
		
		for(Event e:eventrepository.findAll())
		{if (e.getNote()>=0 && e.getNote()<=3) {
			eventrepository.delete(e);}}
	}

	@Override
	public List<Event> eventTypique(long id) {
		
		return eventrepository.typefavorite(id);
	}

	@Override
	public typef typeplusparticipe(long id) {
		
		return eventrepository.typeleplusparticipe(id);
	}

	@Override
	public UserDtoo UserdelEvent() {
		
		return eventrepository.UserdeEvent();
	}

	@Override
	public void AffectUserToEvent(long idevent, long idUser) {
		Event e = eventrepository.findById(idevent).orElseGet(null);
		User u = userrepository.findById(idUser).orElseGet(null);
		List<User> lu = e.getUser();
		lu.add(u);
		e.setUser(lu);
		eventrepository.save(e);
		for(User user : lu){
			eService.sendSimpleEmail(user.getEmail(), "you have an event  ", "we care : events ");
		}
		
	}

	@Override
	public void AffectActToEvent(long idevent, long idAct) {
		
		Event e = eventrepository.findById(idevent).orElseGet(null);
		Activities act = activiterepository.findById(idAct).orElseGet(null);
		List<Activities> activities = e.getActivities();
		activities.add(act);
		e.setActivities(activities);
		eventrepository.save(e);
		
	}

	@Override
	public List<Emaildto> useremailevent() {
			
	return eventrepository.getusereventcurrentdate();}

	@Override
	public void rappelevent() {
		List<Emaildto> edto = eventrepository.getusereventcurrentdate();
		for(Emaildto e : edto){
			eService.sendSimpleEmail(e.getemail(), "you have an event today ", "we care : rappel events ");
		}
		
	}
	
	
	
	
	
	
	}
		
		
	
	
	
	
	
	

		
	   