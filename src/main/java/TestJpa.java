package main.java;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestJpa {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_essai");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		//*****************************
		
		//Récup d'un livre
		Livre lv = em.find(Livre.class, 3);
		if(lv != null) {
			System.out.println("Titre du livre d'Id 3 :"+lv.getTitre());
			System.out.println("Auteur du livre d'Id 3 : "+lv.getAuteur()); 		
		}
		
		//Création d'un nouveau livre (id doit être "AUTO_INCREMENTAL")
		Livre nouveauLivre = new Livre();
		nouveauLivre.setTitre("Tintin au congo");
		nouveauLivre.setAuteur("Hergé");
		em.persist(nouveauLivre);
		
		//Modif titre du livre d'id 5
		String str = "Du plaisir dans la cuisine";
		Livre lvmod = em.find(Livre.class, 5);
		lvmod.setTitre(str);
		System.out.println("Le livre d'id 5 a eu son titre modifié (anciennement \"1001 recettes de Cuisine\") par le nouveau titre : \""+str+"\"...");
		
		//Requête pour extraire le livre du titre précédent		
		TypedQuery<Livre> requete = em.createQuery("select l from Livre l where l.titre='Du plaisir dans la cuisine'", Livre.class);   
		Livre res = requete.getSingleResult();
		System.out.println("le livre dont le titre est "+str+" a pour id "+res.getId());
		
		//Requete pour extraire un livre en fonction de son auteur
		TypedQuery<Livre> req = em.createQuery("select l from Livre l where l.auteur='Emile Zola'", Livre.class);
		Livre result = req.getSingleResult();
		System.out.println("Le livre dont l'auteur est : \"Emile Zola\" a pour id : "+result.getId());
		
		//Supprimer le livre d'id 18
		Livre livSuppr = em.find(Livre.class, 18);
		if(livSuppr != null) { em.remove(livSuppr); }
		
		//lister tous les livres de la base
		TypedQuery<Livre> rq  = em.createQuery("select l from Livre l", Livre.class);
		List<Livre> resultat = null;
		resultat = rq.getResultList();
		for(Livre item:resultat) {
			System.out.println("Titre : "+item.getTitre());
			System.out.println("Auteur : "+item.getAuteur());
		}
		
		//******************************
		et.commit();
		em.close();

	}

}
