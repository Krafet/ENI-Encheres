package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	public Enchere insert();
	public boolean delete();
	public boolean update();
	public Enchere selectById(int idEnchere);
	public Enchere selectAll();
}
