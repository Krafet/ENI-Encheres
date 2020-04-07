package fr.eni.encheres.dal;

import java.util.ArrayList;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDao {
	Enchere insert(Enchere uneEnchere);
	boolean delete();
	boolean update();
	Enchere selectById(int idEnchere);
	ArrayList<Enchere> selectAll();
}
