package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	/**
	 * Méthode en charge de récupérer tous les utilisateurs de la base
	 * @return List<Utilisateur>
	 * @throws BusinessException
	 */
	List<Utilisateur> getAllUtilisateur() throws BusinessException;
	/**
	 * Méthode en charge de récupérer un utilisateur en fonction de son id
	 * @param id
	 * @return Utilisateur
	 * @throws BusinessException
	 */
	Utilisateur getUtilisateurById(int id) throws BusinessException;
	/**
	 * Méthode en charge de récupérer un utilisateur en fonction de son pseudo et password
	 * @param pseudo
	 * @param MotDePasse
	 * @return Utilisateur
	 * @throws BusinessException
	 */
	Utilisateur getUtilisateurByLoginPassword(String login, String MotDePasse) throws BusinessException;

	/**
	 * Méthode en charge d'insérer un utilisateur en base
	 * @param unUtilisateur
	 * @return Utilisateur
	 * @throws BusinessException
	 */
	Utilisateur insert(Utilisateur unUtilisateur) throws BusinessException;
	/**
	 * Méthode en charge de supprimer un utilisateur de la base
	 * @param unUtilisateur
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean delete(Utilisateur unUtilisateur) throws BusinessException;
	/**
	 * Méthode en charge de modifier un utilisateur en base
	 * @param unUtilisateur
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean update(Utilisateur unUtilisateur) throws BusinessException;
	
	void pseudoExistant(String pseudo) throws BusinessException;
	
	void emailExistant(String email) throws BusinessException;
}
