package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;

public interface DAO<T> {
	
	/**
	 * 
	 * Méthode en charge d'insérer en base l'élement renseigné en paramètre
	 * @param element
	 * @return element
	 * @throws BusinessException
	 */
	T insert(T element) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de modifier l'élément en paramètre
	 * @param element
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean update (T element) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de supprimer l'élement de la base
	 * @param id
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean delete (int id) throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de sélectionner tous les éléments
	 * @return Liste d'élément
	 * @throws BusinessException
	 */
	List<T> selectAll() throws BusinessException;
	
	/**
	 * 
	 * Méthode en charge de sélectionner l'élement dont l'id est en paramètre
	 * @param id
	 * @return element
	 * @throws BusinessException
	 */
	T selectById(int id) throws BusinessException;

}
