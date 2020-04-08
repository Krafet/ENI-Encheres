/**
 * 
 */
package fr.eni.encheres.dal.jdbc;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;
import junit.framework.Assert;

/**
 * Classe en charge de tester la CategorieDAO
 * @author Jeremy Albert
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class CategorieDAOTest {

	
	
	CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	
	int id;
	
	
	@Test
	public void insertTest() throws BusinessException{
		
		Categorie uneCat = new Categorie("CategorieTEST");		
		Categorie categorieInsert = categorieDAO.insert(uneCat);		
		Categorie categorieRecuperer = categorieDAO.selectById(categorieInsert.getNoCategorie());		
		
		id = categorieRecuperer.getNoCategorie();
		
		assertEquals(categorieInsert,categorieRecuperer);
	}
	
	
	@Test
	public void updateTest() throws BusinessException{
		
		Categorie uneCat = categorieDAO.selectById(id);
		uneCat.setLibelle("Changement");
		
		boolean result = categorieDAO.update(uneCat);
		assertEquals(result, true);
	}
	
	@Test
	public void selectAllTest() throws BusinessException{
		
		List<Categorie> listeCategorie = categorieDAO.selectAll();
		
		assertEquals(5, listeCategorie.size());
	}
	
	@Test
	public void deleteTest() throws BusinessException{
		
		Categorie uneCat = categorieDAO.selectById(id);
		boolean result = categorieDAO.delete(uneCat);
		
		assertEquals(result, true);
	}
}
