package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	Retrait insert(ArticleVendu retrait) throws BusinessException;
	boolean update(ArticleVendu retrait) throws BusinessException;
	boolean delete(ArticleVendu retrait) throws BusinessException;
	List<Retrait> selectAll() throws BusinessException;
	
}
