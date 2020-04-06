package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	Categorie insert(Categorie categorie);
	boolean update();
	boolean delete(Categorie categorie);
	List<Categorie> selectAll();
	
	
	
}
