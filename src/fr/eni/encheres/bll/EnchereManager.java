/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

/**
 * Classe en charge de
 * @author José Luis FERREIRA DA SILVA
 * @version ENI-Encheres - v1.0
 * @date 10 avr. 2020
 */
public class EnchereManager {
	
	private static EnchereManager instance;
	
	
	private EnchereDAO enchereDAO;

	private List<Enchere> listeEncheres = new ArrayList<>();
	
	private EnchereManager() throws BusinessException {
		
		
		this.enchereDAO=DAOFactory.getEnchereDAO();
		bindDatas();
	}
	
	/**
	 * Méthode en charge de donner accès aux enchères, et faire un singletoon
	 * @author José Luis FERREIRA DA SILVA
	 * @return
	 * @throws BusinessException
	 */
	public static EnchereManager getEnchereManager()
	{
		if(instance == null)
		{
			try {
				instance = new EnchereManager();				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return instance;
	}
	
	
	public List<Enchere> getEncheres()
	{
		try {
			return enchereDAO.selectAll();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	/**
	 * Récupère tous les enchères existants en base
	 * @author José Luis FERREIRA DA SILVA
	 * @return
	 * @throws BusinessException
	 */
	
	private List<Enchere> selectAll() throws BusinessException
	{
		return enchereDAO.selectAll();
	}
	
	/**
	 * Méthode en charge d'ajouter une enchère
	 * @author José Luis FERREIRA DA SILVA
	 * @param uneEnchere
	 * @throws BusinessException
	 */
	public Enchere insert(Enchere uneEnchere) throws BusinessException
	{
		return enchereDAO.insert(uneEnchere);
	}
	
	/**
	 * Méthode en charge de supprimer une enchère
	 * @author José Luis FERREIRA DA SILVA
	 * @param uneEnchere
	 * @throws BusinessException
	 */
	public boolean delete(Enchere uneEnchere) throws BusinessException
	{
		return enchereDAO.delete(uneEnchere.getUnUtilisateur().getNoUtilisateur(), uneEnchere.getUnArticleVendu().getNoArticle());
	}
	
	/**
	 * Méthode en charge de supprimer toutes les enchères liées à un utilisateur
	 * @author Camille
	 * @param uneEnchere
	 * @throws BusinessException
	 */
	public boolean deleteById(Enchere uneEnchere) throws BusinessException
	{
		return enchereDAO.deleteByUserOrArticle(uneEnchere.getUnUtilisateur().getNoUtilisateur(), uneEnchere.getUnArticleVendu().getNoArticle());
	}
	
	/**
	 * Mets à jour une enchère
	 * @author José Luis FERREIRA DA SILVA
	 * @param uneEnchere
	 * @throws BusinessException
	 */
	
	public boolean update(Enchere uneEnchere) throws BusinessException
	{
		return enchereDAO.update(uneEnchere);
	}
	
	/**
	 * Méthode récupérant une enchère spécifiquement par les deux id 
	 * @author José Luis FERREIRA DA SILVA
	 * @param uneEnchere
	 * @throws BusinessException
	 */
	public Enchere selectById(Enchere uneEnchere) throws BusinessException
	{
		return enchereDAO.selectById(uneEnchere.getUnUtilisateur().getNoUtilisateur(), uneEnchere.getUnArticleVendu().getNoArticle());
	}
	
	
	private void bindDatas() throws BusinessException
	{
		listeEncheres.clear();
		listeEncheres.addAll(enchereDAO.selectAll());
	}

}
