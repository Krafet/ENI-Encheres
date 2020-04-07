package fr.eni.encheres.dal;

import java.util.ArrayList;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	boolean insert(Enchere uneEnchere) throws BusinessException;
	boolean delete(int idUtilisateur, int idArticle);
	boolean update(Enchere uneEnchere);
	Enchere selectById(int idUtilisateur, int idArticle);
	ArrayList<Enchere> selectAll();
}
