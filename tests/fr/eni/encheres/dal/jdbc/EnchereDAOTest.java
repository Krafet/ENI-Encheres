package fr.eni.encheres.dal.jdbc;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
	
	EnchereDAO enchereDAO;
	
	ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
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
		
		enchereDAO = DAOFactory.getEnchereDAO();
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
		int avantInsertion = enchereDAO.selectAll().size();

		Enchere uneEnchere = creationEnchere();
		
		enchereDAO.delete(uneEnchere.getUnUtilisateur().getNoUtilisateur(), uneEnchere.getUnArticleVendu().getNoArticle());
		enchereDAO.insert(uneEnchere);
		
		int apresInsertion = enchereDAO.selectAll().size();

		assertEquals(apresInsertion, avantInsertion + 1);
	}
	
	
	
	@Test

	public void updateTest() throws BusinessException{
		
		Enchere enchereTest = creationEnchere();
		boolean result = enchereDAO.update(enchereTest);
		assertEquals(result, true);
	}
	
	@Test
	public void selectAllTest() throws BusinessException{
		
		List<Enchere> listeEnchere = enchereDAO.selectAll();
		
		assertEquals(3, listeEnchere.size());
	}
	
	@Test
	public void deleteTest() throws BusinessException{
		
		Enchere enchereTest = creationEnchere();
		enchereDAO.insert(enchereTest);
		boolean result = enchereDAO.delete(enchereTest.getUnUtilisateur().getNoUtilisateur(), enchereTest.getUnArticleVendu().getNoArticle());
		assertEquals(result, true);
	}
	
	@Test
	public void selectByIdTest() throws BusinessException{
		
		Enchere enchereAttendu = creationEnchere();
		Enchere enchereInsertion = enchereDAO.insert(enchereAttendu);

		Enchere enchereRecupere = articleVenduDAO.selectById(enchereInsertion.getUnUtilisateur().getNoUtilisateur(), enchereInsertion.getUnArticleVendu().getNoArticle());

		assertEquals(enchereAttendu, enchereRecupere); 
	}
	
	private Enchere creationEnchere()
	{
		ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		
		//INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(1, 2, '2020-04-12', 500);
		Enchere uneEnchere = new Enchere();
		
		uneEnchere.setUnUtilisateur(utilisateurDAO.getUtilisateurById(1));
		uneEnchere.setUnArticleVendu(articleDAO.selectById(2));
		uneEnchere.setMontantEnchere(500);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "12/04/2020";
		Date date = sdf.parse(dateInString);
		
		uneEnchere.setDateEnchere(date);
	}
}
