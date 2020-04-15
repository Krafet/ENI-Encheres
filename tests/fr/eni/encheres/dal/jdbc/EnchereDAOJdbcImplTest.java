package fr.eni.encheres.dal.jdbc;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
 * 
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 8 avr. 2020
 */
public class EnchereDAOJdbcImplTest {

	UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();

	Utilisateur utilisateur;
	ArticleVendu article;

	/**
	 * Méthode en charge de réinitialiser la base avant le lancement des tests
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		// Script qui réinitialise base et insère un jeu de test
		try {
			Utils.executeQuery("db/reset.sql");
			Utils.executeQuery("db/jeu_essai_test_categories.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Méthode en charge de réinitialiser la base après le lancement de tous les
	 * tests de la classe
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

		// On reset pour obtenir de nouveau le jeu d'essai de base de notre application
		try {
			Utils.executeQuery("db/jeu_essai_application.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	public void tearDownAfterEach() throws Exception {
		// On reset pour obtenir de nouveau le jeu d'essai de base de notre application
		try {
			Utils.executeQuery("db/jeu_essai_application.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectByIdTest() throws BusinessException {

		Enchere enchereAttendu = enchereBuilder();
		Enchere enchereInsertion = enchereDAO.insert(enchereAttendu);

		Enchere enchereRecupere = enchereDAO.selectById(enchereInsertion.getUnUtilisateur().getNoUtilisateur(),
				enchereInsertion.getUnArticleVendu().getNoArticle());

		assertEquals(enchereAttendu, enchereRecupere);
		// assertEquals(enchereAttendu.getMontantEnchere(),
		// enchereRecupere.getMontantEnchere());
	}

	@Test
	public void insertTest() throws BusinessException {
		int avantInsertion = enchereDAO.selectAll().size();

		Enchere uneEnchere = enchereBuilder();
		enchereDAO.insert(uneEnchere);

		int apresInsertion = enchereDAO.selectAll().size();

		assertEquals(apresInsertion, avantInsertion + 1);
	}

	@Test
	public void deleteTest() throws BusinessException {

		Enchere enchereTest = enchereBuilder();
		enchereDAO.insert(enchereTest);
		boolean result = enchereDAO.delete(enchereTest.getUnUtilisateur().getNoUtilisateur(),
				enchereTest.getUnArticleVendu().getNoArticle());
		assertEquals(result, true);
	}

	@Test
	public void updateTest() throws BusinessException {

		Enchere enchereAModifier = enchereBuilder();
		Enchere recuperationInsertion = enchereDAO.insert(enchereAModifier);

		// On change certaines de ses propriétés
		recuperationInsertion.setMontantEnchere(10);

		// On le met à jour en base
		enchereDAO.update(recuperationInsertion);

		// On le récupère pour le comparer
		Enchere enchereMiseAJour = enchereDAO.selectById(recuperationInsertion.getUnUtilisateur().getNoUtilisateur(),
				recuperationInsertion.getUnArticleVendu().getNoArticle());

		assertEquals(recuperationInsertion, enchereMiseAJour);
		// assertEquals(recuperationInsertion.getMontantEnchere(),
		// enchereMiseAJour.getMontantEnchere());

	}

	@Test
	public void selectAllTest() throws Exception {

		List<Enchere> listeEnchere = enchereDAO.selectAll();
		assertEquals(3, listeEnchere.size());
	}

	/**
	 * 
	 * Méthode en charge de créer une enchère pour nos tests
	 * 
	 * @return
	 * @throws BusinessException
	 */
	private Enchere enchereBuilder() throws BusinessException {

		// Récupération d'un article et d'un utilisateur
		Utilisateur utilisateur = utilisateurDAO.getUtilisateurById(1);
		ArticleVendu article = articleDAO.selectById(3);

		Enchere uneEnchere = new Enchere();
		uneEnchere.setUnUtilisateur(utilisateur);
		uneEnchere.setUnArticleVendu(article);
		uneEnchere.setMontantEnchere(500);
		try {
			uneEnchere.setDateEnchere(Utils.stringVersUtil("2020-12-04 "));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uneEnchere;
	}

}
