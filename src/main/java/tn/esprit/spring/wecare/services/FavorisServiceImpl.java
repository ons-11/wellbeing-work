package tn.esprit.spring.wecare.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.wecare.entities.namedto;
import tn.esprit.spring.wecare.iservices.FavorisIService;
import tn.esprit.spring.wecare.repositories.ActivitéRepository;
import tn.esprit.spring.wecare.repositories.FavorisRepository;
import tn.esprit.spring.wecare.repositories.UserRepository;

@Service
public class FavorisServiceImpl implements FavorisIService {

	@Autowired
	FavorisRepository favorisrepo;
	@Autowired
	ActivitéRepository activiterepo;
	@Autowired
	UserRepository userrepo;
	
	@Override
	public namedto listefavoris(long idUser) {
		 
	 
		return favorisrepo.Actfavoris(idUser);}

	

}
