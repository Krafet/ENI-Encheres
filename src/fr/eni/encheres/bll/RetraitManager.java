/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

/**
 * Classe BLL Retrait Manager
 * @author Jeremy Albert
 * @version ENI-Encheres - v1.0
 * @date 9 avr. 2020
 */
public class RetraitManager {
	
	
	private static RetraitManager instance;
	
	
	private RetraitDAO retraitDAO;

	private List<Retrait> listeRetraits = new ArrayList<>();
	

	//Constructor
	private RetraitManager() throws BusinessException {
		
		this.retraitDAO =DAOFactory.getRetraitDAO();
		bindDatas();
	}
	
	public static RetraitManager getCategorieManager() throws BusinessException
	{
		if(instance == null)
		{
			instance = new RetraitManager();
		}

		return instance;		
	}
	
	
	public void addRetrait(Retrait retrait, ArticleVendu articleVendu) throws BusinessException
	{
		articleVendu.setRetrait(retrait);
		retraitDAO.insert(articleVendu);
		listeRetraits.add(retrait);
	}
	
	public void removeRetrait(ArticleVendu articleVendu) throws BusinessException
	{
		retraitDAO.delete(articleVendu);
		listeRetraits.remove(articleVendu.getRetrait());
		articleVendu.setRetrait(null);
	}
	
	
	private void bindDatas() throws BusinessException
	{
		listeRetraits.clear();
		listeRetraits.addAll(retraitDAO.selectAll());
	}
	
}
