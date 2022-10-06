package tn.esprit.spring.wecare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.wecare.entities.Favoris;
import tn.esprit.spring.wecare.entities.namedto;
import tn.esprit.spring.wecare.iservices.FavorisIService;


@RestController
public class FavorisRestController {
	
	@Autowired
	FavorisIService favorisIService;

	@ResponseBody
	@GetMapping("/add-favoris-act/{idUser}")
	public namedto addFavorisActivity( long idUser) {
		return favorisIService.listefavoris(idUser);
	}
	

}

