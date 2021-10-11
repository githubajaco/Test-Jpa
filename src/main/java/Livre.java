package main.java;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LIVRE")
public class Livre {
		
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="TITRE")
	private String titre;
	
	@Column(name="AUTEUR")
	private String auteur;

	public Livre() {
		
	}

	protected Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	protected String getTitre() {
		return titre;
	}

	protected void setTitre(String titre) {
		this.titre = titre;
	}

	protected String getAuteur() {
		return auteur;
	}

	protected void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	
	
	
}
