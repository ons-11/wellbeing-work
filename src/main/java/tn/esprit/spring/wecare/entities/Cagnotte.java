package tn.esprit.spring.wecare.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cagnotte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long CagnotteId;
	private String CagnotteTitle;
	private String CagnotteDescription;
	private boolean IsActive;
	private Double MoneyCollected;
	
	@Enumerated(EnumType.STRING)
	private TypeCagnotte typeCagnotte;
	private String Image;

	@JsonIgnore
	@OneToMany(mappedBy ="cagnotte", cascade = CascadeType.ALL)
	public Set<Donation> donations;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cagnotte")
	public List<WithDrawal> drawals;
}
