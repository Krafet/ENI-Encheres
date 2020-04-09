package fr.eni.encheres.dal.jdbc;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.utils.Utils;

/**
 * 
 * Classe en charge de tester les méthodes de la dal
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 8 avr. 2020
 */
public class EnchereDAOTest {
	
	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	
	UtilisateurDAO utilsateurDAO = DAOFactory.getUtilisateurDAO();
	ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
	
	Enchere enchereTest;
	Utilisateur utilisateur;
	ArticleVendu article;
	

	/**
	 * Méthode en charge de réinitialiser la base avant le lancement des tests
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		
		// On reset la base de données avant chaque test
		try {
			Utils.executeQuery("db/reset.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Date date = new Date("1995-10-10");
		
		Categorie uneCat = new Categorie("CategorieTEST");	
		
		ArticleVendu article = new ArticleVendu(1, "nomArticle", "description", date,
				date, 5, 10, true, null,
				uneCat, null);
		
		utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(1);
		
	}
	
	/**
	 * 
	 * Méthode en charge de réinitialiser la base après le lancement de tous les tests de la classe
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

		// On reset la base de données avant chaque test
		try {
			Utils.executeQuery("db/reset.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode en charge de réinitialiser la base de données après chaque test
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// On reset la base de données avant chaque test
		try {
			Utils.executeQuery("db/reset.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void insertTest() throws BusinessException{
		enchereDAO.insert(uneEnchere);
	}
	
	
	
	@Test

	public void updateTest() throws BusinessException{
		
		boolean result = enchereDAO.update(enchereTest);
		assertEquals(result, true);
	}
	
	@Test
	public void selectAllTest() throws BusinessException{
		
		List<Enchere> listeEnchere = enchereDAO.selectAll();
		
		assertEquals(1, listeEnchere.size()==1);
	}
	
	@Test
	public void deleteTest() throws BusinessException{
		
		boolean result = enchereDAO.delete(article.getNoArticle(), utilisateur.getNoUtilisateur());
		assertEquals(result, true);
	}
}
