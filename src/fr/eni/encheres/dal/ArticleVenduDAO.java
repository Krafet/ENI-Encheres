package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	/**
	 * 
	 * Méthode en charge d'insérer en base l'article renseigné en paramètre
	 * @param article
	 * @return article
	 * @throws BusinessException
	 */
	ArticleVendu insert(ArticleVendu article) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de supprimer l'article de la base
	 * @param id
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean delete(int id) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de modifier l'article en paramètre
	 * @param article
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean update(ArticleVendu article) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de sélectionner tous les articles de la base
	 * @return Liste d'articles
	 * @throws BusinessException
	 */
	List<ArticleVendu> selectAll() throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de sélectionner l'article dont l'id est en paramètre
	 * @param id
	 * @return ArticleVendu
	 * @throws BusinessException
	 */
	ArticleVendu selectById(int id) throws BusinessException;
		

}
