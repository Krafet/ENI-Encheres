package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	Retrait insert(Retrait categorie);
	boolean update();
	boolean delete(Retrait categorie);
	List<Retrait> selectAll();
	
}
