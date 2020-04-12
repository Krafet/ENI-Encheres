package fr.eni.encheres.dal;

import java.util.ArrayList;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	/**
	 * 
	 * Méthode en charge d'insérer une enchère en base
	 * @param uneEnchere
	 * @return Enchere
	 * @throws BusinessException
	 */
	Enchere insert(Enchere uneEnchere) throws BusinessException;
	//boolean insert(Enchere uneEnchere) throws BusinessException;
	/**
	 * 
	 * Méthode en charge de supprimer une enchère
	 * @param idUtilisateur
	 * @param idArticle
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean delete(int idUtilisateur, int idArticle) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de modifier une enchère
	 * @param uneEnchere
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean update(Enchere uneEnchere) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de récupérer une enchère via son id
	 * @param idUtilisateur
	 * @param idArticle
	 * @return
	 * @throws BusinessException
	 */
	Enchere selectById(int idUtilisateur, int idArticle) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de récupérer l'ensemble des enchères
	 * @return
	 * @throws BusinessException
	 */
	
	ArrayList<Enchere> selectAll() throws BusinessException;
	/**
	 * Méthode en charge de supprimer l'ensemble des enchères d'un utilisateur
	 * @param idUtilisateur
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean deleteByUser(int idUtilisateur) throws BusinessException;
}
