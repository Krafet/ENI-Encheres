package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	Categorie insert(Categorie categorie) throws BusinessException;
	boolean update(Categorie categorie) throws BusinessException;
	boolean delete(Categorie categorie) throws BusinessException;
	List<Categorie> selectAll() throws BusinessException;
	
	
	
}
