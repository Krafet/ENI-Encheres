package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	ArticleVendu insert(ArticleVendu article) throws BusinessException;
	boolean delete(int id) throws BusinessException;
	boolean update(ArticleVendu article) throws BusinessException;
	List<ArticleVendu> selectAll();
	ArticleVendu selectById(int id);
		

}
