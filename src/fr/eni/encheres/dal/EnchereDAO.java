package fr.eni.encheres.dal;

import java.util.ArrayList;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	boolean insert(Enchere uneEnchere) throws BusinessException;
	boolean delete(int idUtilisateur, int idArticle) throws BusinessException;
	boolean update(Enchere uneEnchere) throws BusinessException;
	Enchere selectById(int idUtilisateur, int idArticle) throws BusinessException;
	ArrayList<Enchere> selectAll() throws BusinessException;
}
