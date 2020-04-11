/**
 * 
 */
package fr.eni.encheres.dal.jdbc;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.utils.Utils;
import junit.framework.Assert;

/**
 * Classe en charge de tester la CategorieDAO
 * @author Jeremy Albert
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class CategorieDAOJdbcImplTest {
	
	CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	int id;
	
	
	/**
	 * Méthode en charge de réinitialiser la base avant le lancement des tests	 *
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {

		// On reset la base de données avant chaque test
		try {
			Utils.executeQuery("db/reset.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Méthode en charge de réinitialiser la base après le lancement de tous les tests de la classe
	 * @throws Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {

		// On reset la base de données avant chaque test
		try {
			Utils.executeQuery("db/jeu_essai_application.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
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
		
		Categorie uneCat = new Categorie("CategorieUpdate");		
		Categorie categorieInsert = categorieDAO.insert(uneCat);	
		
		
		Categorie uneCatModif = categorieDAO.selectById(categorieInsert.getNoCategorie());
		uneCat.setLibelle("Changement");
		
		boolean result = categorieDAO.update(uneCatModif);
		assertEquals(result, true);
	}
	
	@Test
	public void selectAllTest() throws BusinessException{
		
		Categorie uneCat = new Categorie("CategorieTEST");		
		Categorie uneCat2 = new Categorie("CategorieTEST");		
		Categorie uneCat3 = new Categorie("CategorieTEST");		

		categorieDAO.insert(uneCat);
		categorieDAO.insert(uneCat2);
		categorieDAO.insert(uneCat3);
		
		List<Categorie> listeCategorie = categorieDAO.selectAll();
		
		assertEquals(3, listeCategorie.size());
	}
	
	@Test
	public void deleteTest() throws BusinessException{
		
		Categorie uneCat = new Categorie("CategorieDelete");		
		Categorie categorieDelete = categorieDAO.insert(uneCat);	
		
		
		Categorie uneCatADelete = categorieDAO.selectById(categorieDelete.getNoCategorie());
		boolean result = categorieDAO.delete(uneCatADelete);
		
		assertEquals(result, true);
	}
}
