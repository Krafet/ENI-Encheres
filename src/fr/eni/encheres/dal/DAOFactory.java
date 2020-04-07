package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.EnchereDAOJbdcImpl;
import fr.eni.encheres.dal.jdbc.RetraitDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

/**
 * 
 * Classe en charge de récupérér les implémentations des classes Jdbc pour chaque model
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public abstract class DAOFactory {
	
	/**
	 * 
	 * Méthode en charge de récupérer l'implémentation jdb de ArticleVenduDAO
	 * @return ArticleVenduDAO
	 */
	public static ArticleVenduDAO getArticleVenduDAO()
	{
		return (ArticleVenduDAO) new ArticleVenduDAOJdbcImpl();
	}
	
	/**
	 * 
	 * Méthode en charge de récupérer l'implémentation jdb de ArticleVenduDAO
	 * @return ArticleVenduDAO
	 */
	public static CategorieDAO getCategorieDAO()
	{
		return new CategorieDAOJdbcImpl();
	}

	/**
	 * 
	 * Méthode en charge de récupérer l'implémentation jdb de EnchereDAO
	 * @return EnchereDAO
	 */
	public static EnchereDAO getEnchereDAO()
	{
		return new EnchereDAOJbdcImpl();
	}

	/**
	 * 
	 * Méthode en charge de récupérer l'implémentation jdb de RetraitDAO
	 * @return RetraitDAO
	 */
	public static RetraitDAO getRetraitDAO()
	{
		return new RetraitDAOJdbcImpl();
	}

	/**
	 * 
	 * Méthode en charge de récupérer l'implémentation jdb de UtilisateurDAO
	 * @return UtilisateurDAO
	 */
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}


}
