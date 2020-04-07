package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	
	/**
	 * Méthode en charge de
	 * @author Jeremy Albert
	 * @param categorie
	 * @return
	 * @throws BusinessException
	 */
	Categorie insert(Categorie categorie) throws BusinessException;
	
	/**
	 * Méthode en charge de
	 * @author Jeremy Albert
	 * @param categorie
	 * @return
	 * @throws BusinessException
	 */
	boolean update(Categorie categorie) throws BusinessException;
	
	/**
	 * Méthode en charge de
	 * @author Jeremy Albert
	 * @param categorie
	 * @return
	 * @throws BusinessException
	 */
	boolean delete(Categorie categorie) throws BusinessException;
	
	/**
	 * Méthode en charge de
	 * @author Jeremy Albert
	 * @return
	 * @throws BusinessException
	 */
	List<Categorie> selectAll() throws BusinessException;
	
	
	
}
