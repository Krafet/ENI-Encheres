package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;



/**
 * Classe BLL CategorieManager
 * @author Jeremy Albert
 * @version ENI-Encheres - v1.0
 * @date 9 avr. 2020
 */
/**
 * Classe en charge de
 * @author Jeremy Albert
 * @version ENI-Encheres - v1.0
 * @date 9 avr. 2020
 */
/**
 * Classe en charge de
 * @author Jeremy Albert
 * @version ENI-Encheres - v1.0
 * @date 9 avr. 2020
 */
/**
 * Classe en charge de
 * @author Jeremy Albert
 * @version ENI-Encheres - v1.0
 * @date 9 avr. 2020
 */
public class CategorieManager {

	private static CategorieManager instance;
	
	
	private CategorieDAO categorieDAO;

	public List<Categorie> listeCategories = new ArrayList<>();

	//Constructor
	private CategorieManager() throws BusinessException {
		this.categorieDAO=DAOFactory.getCategorieDAO();
		bindDatas();
	}
	
	/**
	 * Méthode en charge de donner acces au Categories
	 * @author Jeremy Albert
	 * @return
	 * @throws BusinessException
	 */
	public static CategorieManager getCategorieManager() throws BusinessException
	{
		if(instance == null)
		{
			instance = new CategorieManager();
			
		}

		return instance;		
	}
	
	
	
	/**
	 * Méthode en charge d'ajouter une Categorie en BLL/DAO
	 * @author Jeremy Albert
	 * @param categorie
	 * @throws BusinessException
	 */
	public void addCategorie(Categorie categorie) throws BusinessException
	{
		categorieDAO.insert(categorie);
		listeCategories.add(categorie);
	}
	
	/**
	 * Méthode en charge de retirer une Categorie en BLL/DAO
	 * @author Jeremy Albert
	 * @param categorie
	 * @throws BusinessException
	 */
	public void removeCategorie(Categorie categorie) throws BusinessException
	{
		categorieDAO.delete(categorie);
		listeCategories.remove(categorie);
	}
	
	/**
	 * Méthode en charge de retirer une Categorie en BLL/DAO
	 * @author Jeremy Albert
	 * @param index
	 * @throws BusinessException
	 */
	public void removeCategorie(int index) throws BusinessException
	{
		Categorie cat = listeCategories.get(index);
		categorieDAO.delete(cat);
		listeCategories.remove(index);
	}
	
	/**
	 * Méthode en charge de recuperer une Categorie selon son ID
	 * @author Jeremy Albert
	 * @param index
	 * @return
	 */
	public Categorie getByID(int index)
	{
		for(int i = 0; i < listeCategories.size(); i++)
		{
			if(listeCategories.get(i).getNoCategorie() == index)
			{
				return listeCategories.get(i);
			}
		}
		
	//TODO Throws error
		return null;
		
	}
	
	
	/**
	 * Méthode en charge de recuperer une Categorie selon son Libelle
	 * @author Jeremy Albert
	 * @param libelle
	 * @return
	 */
	public Categorie getCatgorieByLibelle(String libelle)
	{
		for(int i = 0; i < listeCategories.size(); i++)
		{
			if(listeCategories.get(i).getLibelle().equals(libelle))
			{
				return listeCategories.get(i);
			}
		}
		//TODO Throws error
		return null;
	}
	

	
	
	
	private void bindDatas() throws BusinessException
	{
		listeCategories.clear();
		listeCategories.addAll(categorieDAO.selectAll());
	}

	

}
