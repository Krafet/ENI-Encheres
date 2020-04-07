package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	
	/**
	 * Méthode en charge d'insérer en BDD une catégorie
	 * @author Jeremy Albert
	 * @param Categorie
	 * @return Categorie avec ID
	 * @throws BusinessException
	 */
	Categorie insert(Categorie categorie) throws BusinessException;
	
	/**
	 * Méthode en charge de modifier en BDD une catégorie
	 * @author Jeremy Albert
	 * @param categorie
	 * @return
	 * @throws BusinessException
	 */
	boolean update(Categorie categorie) throws BusinessException;
	
	/**
	 * Méthode en charge de supprimer en BDD une catégorie
	 * @author Jeremy Albert
	 * @param categorie
	 * @return
	 * @throws BusinessException
	 */
	boolean delete(Categorie categorie) throws BusinessException;
	
	/**
	 * Méthode en charge de sélectionner en BDD les catégorie
	 * @author Jeremy Albert
	 * @return
	 * @throws BusinessException
	 */
	List<Categorie> selectAll() throws BusinessException;
	
	
	/**
	 * Méthode en charge de selectionner une catégorie par id
	 * @author Jeremy Albert
	 * @param id
	 * @return Categorie
	 * @throws BusinessException
	 */
	Categorie selectById(int id) throws BusinessException;
}
