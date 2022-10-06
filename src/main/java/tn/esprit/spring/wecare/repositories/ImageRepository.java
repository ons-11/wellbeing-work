package tn.esprit.spring.wecare.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.wecare.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);

}
