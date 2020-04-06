package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.EnchereDaoJbdcImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {
	
	public static ArticleVenduDAO getArticleVenduDAO()
	{
		return (ArticleVenduDAO) new ArticleVenduDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO()
	{
		return null;
		//return new CategorieDAOJdbcImpl();
	}

	public static EnchereDao getEnchereDAO()
	{
		return new EnchereDaoJbdcImpl();
	}

	public static RetraitDAO getRetraitDAO()
	{
		return null;
		//return new RetraitDAOJdbcImpl();
	}

	public static UtilisateurDao getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}


}
