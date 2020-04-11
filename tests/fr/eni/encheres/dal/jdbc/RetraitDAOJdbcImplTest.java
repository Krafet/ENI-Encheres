/**
 * 
 */
package fr.eni.encheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.utils.Utils;

/**
 * Classe en charge de tester la dal touchant au retrait
 * @author Jeremy Albert
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
class RetraitDAOJdbcImplTest {
	
	ArticleVenduDAO articleVenduDAO = DAOFactory.getArticleVenduDAO();
	CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
	
	int id;
	
	
	/**
	 * Méthode en charge de réinitialiser la base avant le lancement des tests
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {

		// On reset la base de données avant chaque test
		try {
			Utils.executeQuery("db/reset.sql");
			Utils.executeQuery("db/jeu_essai_test_categories.sql");
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
	
	
	

	/**
	 * Test method for {@link fr.eni.encheres.dal.RetraitDAO#insert(fr.eni.encheres.bo.ArticleVendu)}.
	 * @throws BusinessException 
	 */
	@Test
	void testInsert() throws BusinessException {
		
		Retrait unRetrait = retraitBuilder();		
		ArticleVendu unArticle = articleBuilder();	
		unArticle.setRetrait(unRetrait);	
		ArticleVendu art = articleVenduDAO.insert(unArticle);

		
		Retrait retraitInserer = retraitDAO.insert(art);		
		Retrait retraitRecup = retraitDAO.selectById(art.getNoArticle());
		
		assertEquals(retraitInserer, retraitInserer);
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.RetraitDAO#update(fr.eni.encheres.bo.ArticleVendu)}.
	 * @throws BusinessException 
	 */
	@Test
	void testUpdate() throws BusinessException {
		
		Retrait unRetrait = retraitBuilder();		
		ArticleVendu unArticle = articleBuilder();	
		unArticle.setRetrait(unRetrait);	
		ArticleVendu art = articleVenduDAO.insert(unArticle);
		
		Retrait retraitInserer = retraitDAO.insert(art);
		retraitInserer.setVille("CHANGEMENT");
		art.setRetrait(retraitInserer);
				
		boolean result = retraitDAO.update(art);
				
		assertEquals(true, result);
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.RetraitDAO#delete(fr.eni.encheres.bo.ArticleVendu)}.
	 * @throws BusinessException 
	 */
	@Test
	void testDelete() throws BusinessException {
		
		Retrait unRetrait = retraitBuilder();		
		ArticleVendu unArticle = articleBuilder();	
		unArticle.setRetrait(unRetrait);	
		ArticleVendu art = articleVenduDAO.insert(unArticle);
		Retrait retraitInserer = retraitDAO.insert(art);

		
		boolean result = retraitDAO.delete(art);
		
		assertEquals(true, result);
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.RetraitDAO#selectAll()}.
	 * @throws BusinessException 
	 */
	@Test
	void testSelectAll() throws BusinessException {
		
		Retrait unRetrait = retraitBuilder();		
		ArticleVendu unArticle = articleBuilder();	
		unArticle.setRetrait(unRetrait);	
		ArticleVendu art = articleVenduDAO.insert(unArticle);
		retraitDAO.insert(art);
		
		List<Retrait> listRetraits = retraitDAO.selectAll();
		
		
		assertEquals(3, listRetraits.size());
	}
	
	
	/**
	 * 
	 * Méthode en charge de construire un article pour nos tests
	 * 
	 * @return ArticleVendu
	 * @throws BusinessException
	 */
	public ArticleVendu articleBuilder() throws BusinessException {

		// Récupération d'une catégorie et d'un utilisateur
		Categorie categorie = categorieDAO.selectById(1);
		Utilisateur utilisateur = utilisateurDAO.getUtilisateurById(2);

		// Création de l'article
		ArticleVendu article = new ArticleVendu();
		article.setNomArticle("NewArticle");
		article.setDescription("Description");
		article.setDateDebutEncheres(Date.valueOf("2020-04-25"));
		article.setDateFinEncheres(Date.valueOf("2020-04-25"));
		article.setMiseAPrix(15);
		article.setPrixVente(30);
		article.setCategorie(categorie);
		article.setUtilisateur(utilisateur);

		return article;
	}
	
	
	/**
	 * 
	 * Méthode en charge de construire un retrait pour nos tests
	 * @return Retrait
	 * @throws BusinessException
	 */
	public Retrait retraitBuilder() throws BusinessException
	{
		Retrait retrait = new Retrait();		
		retrait.setCodePostal("35000");
		retrait.setRue("4 rue des chevres");
		retrait.setVille("Ville de Test");
		
		return retrait;
	}

}
