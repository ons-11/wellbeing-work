package tn.esprit.spring.wecare.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Posts {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long PostId;
	private String TitlePost;
	private String DescriptionPost;
	private int NbComment;
	private Date DatePost;
	private String ImagePost;

	@ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
	private User user; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="posts")
	@JsonIgnore
	public List<CommentPost> commentposts ; 
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JsonIgnore
	public List<Departement> Departements;
	
	@ManyToMany
	@JsonIgnore
	Set<User> userLikes;
	
	@ManyToMany
	
	Set<User> userDislikes;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	private Dictionary dictionary;
	
	/*@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	private CommentPost commentPost;*/

	
}
