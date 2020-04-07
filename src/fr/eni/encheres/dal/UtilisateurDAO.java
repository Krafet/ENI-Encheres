package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	List<Utilisateur> getAllUtilisateur() throws BusinessException;
	Utilisateur getUtilisateurById(int id) throws BusinessException;
	Utilisateur getUtilisateurByPseudoPassword(String pseudo, String MotDePasse) throws BusinessException;
	Utilisateur insert(Utilisateur unUtilisateur) throws BusinessException;
	boolean delete(Utilisateur unUtilisateur) throws BusinessException;
	boolean update(Utilisateur unUtilisateur) throws BusinessException;
}
