package tn.esprit.spring.wecare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.wecare.entities.Activities;
import tn.esprit.spring.wecare.iservices.ActiviteIService;


@RestController
public class ActivityRestController {
	
	@Autowired
	ActiviteIService activiteIservice;
	
	@GetMapping("/AfficherActivities")
	public List<Activities> getallActivities(){
		 return activiteIservice.getallActivities();}
	
	@GetMapping("/AfficherActivité/{idActivité}")
	public Activities getActivitiesById(@PathVariable("idActivité")Long id) {
		
		return activiteIservice.getActivitiesById(id);}
	
	@PostMapping("/ajoutActivité")
	public void addActivity(@RequestBody Activities act) {
		activiteIservice.addActivity(act);}
	
	@DeleteMapping("/supprimerActivité/{id}")
	public void deleteActivity(@PathVariable("id")Long id) {
		activiteIservice.deleteActivity(id);}
	
	@PostMapping("/modifierActivité/{id}")
	public void updateActivity(@RequestBody Activities act,@PathVariable("id") Long id) {
		activiteIservice.updateActivity(act, id);}
	
	
	

}
