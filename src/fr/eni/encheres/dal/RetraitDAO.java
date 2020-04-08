package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	/**
	 * Méthode en charge de
	 * @author Jeremy Albert
	 * @param retrait
	 * @return
	 * @throws BusinessException
	 */
	Retrait insert(ArticleVendu retrait) throws BusinessException;
	
	/**
	 * Méthode en charge de
	 * @author Jeremy Albert
	 * @param retrait
	 * @return
	 * @throws BusinessException
	 */
	boolean update(ArticleVendu retrait) throws BusinessException;
	
	/**
	 * Méthode en charge de
	 * @author Jeremy Albert
	 * @param retrait
	 * @return
	 * @throws BusinessException
	 */
	boolean delete(ArticleVendu retrait) throws BusinessException;
	
	/**
	 * Méthode en charge de
	 * @author Jeremy Albert
	 * @return
	 * @throws BusinessException
	 */
	List<Retrait> selectAll() throws BusinessException;
	
	
	
	/**
	 * Méthode en charge de 
	 * @author Jeremy Albert
	 * @param id
	 * @return Retrait
	 * @throws BusinessException
	 */
	Retrait selectById(int id) throws BusinessException;
	
}
