package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDao {

	List<Utilisateur> getAllUtilisateur();
	Utilisateur getUtilisateurById(int id);
	Utilisateur getUtilisateurByPseudoPassword(String pseudo, String MotDePasse);
	Utilisateur insert(Utilisateur unUtilisateur);
	boolean delete(Utilisateur unUtilisateur);
	boolean update(Utilisateur unUtilisateur);
}
