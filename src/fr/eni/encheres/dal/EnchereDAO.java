package fr.eni.encheres.dal;

import java.util.ArrayList;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	Enchere insert(Enchere uneEnchere);
	boolean delete(int idUtilisateur, int idArticle);
	boolean update(Enchere uneEnchere);
	Enchere selectById(int idEnchere);
	ArrayList<Enchere> selectAll();
}
