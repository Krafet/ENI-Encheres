package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	Retrait insert(ArticleVendu retrait);
	boolean update(ArticleVendu retrait);
	boolean delete(ArticleVendu retrait);
	List<Retrait> selectAll();
	
}
