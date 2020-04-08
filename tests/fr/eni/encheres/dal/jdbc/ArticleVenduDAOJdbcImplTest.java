/**
 * 
 */
package fr.eni.encheres.dal.jdbc;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.utils.Utils;

/**
 * Classe en charge de tester la dal pour les articles vendus
 * 
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class ArticleVenduDAOJdbcImplTest {

	// On instancie les DAO
	ArticleVenduDAO articleVenduDAO = DAOFactory.getArticleVenduDAO();
	CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();

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

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl#selectById(int)}.
	 */
	@Test
	public void testSelectById() throws BusinessException {

		// Création et insertion d'un article
		ArticleVendu articleAttendu = articleBuilder();
		ArticleVendu recuperationInsertion = articleVenduDAO.insert(articleAttendu);

		// Récupération de cet article
		ArticleVendu articleRecupere = articleVenduDAO.selectById(recuperationInsertion.getNoArticle());

		// On vérifie que les deux correspondent bien
		assertEquals(articleAttendu, articleRecupere); 

	

	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl#insert(fr.eni.encheres.bo.ArticleVendu)}.
	 */
	@Test
	public void testInsert() throws BusinessException {

		// Taille initiale
		int sizeBeforeInsertion = articleVenduDAO.selectAll().size();

		// Insertion d'un nouvel article
		ArticleVendu articleAAjouter = articleBuilder();
		articleVenduDAO.insert(articleAAjouter);

		// La taille doit avoir été augmentée de 1 après l'insertion
		int sizeAfterInsertion = articleVenduDAO.selectAll().size();

		assertEquals(sizeAfterInsertion, sizeBeforeInsertion + 1);

	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl#delete(int)}.
	 */
	@Test
	public void testDelete() throws BusinessException {

		// Taille initiale
		int sizeBefore = articleVenduDAO.selectAll().size();

		// Insertion d'un nouvel article
		ArticleVendu articleAAjouter = articleBuilder();
		ArticleVendu recuperationInsertion = articleVenduDAO.insert(articleAAjouter);

		// Suppression de l'article qu'on vient d'ajouter
		articleVenduDAO.delete(recuperationInsertion.getNoArticle());

		// La taille doit être identique à celle d'avant puisqu'on supprime celui qu'on
		// vient d'ajouter
		int sizeAfter = articleVenduDAO.selectAll().size();

		assertEquals(sizeBefore, sizeAfter);

	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl#update(fr.eni.encheres.bo.ArticleVendu)}.
	 */
	@Test
	public void testUpdate() throws BusinessException {

		// Insertion d'un nouvel article
		ArticleVendu articleAModifier = articleBuilder();
		ArticleVendu recuperationInsertion = articleVenduDAO.insert(articleAModifier);

		// On change certaines de ses propriétés
		recuperationInsertion.setNomArticle("Nom modifié !");
		recuperationInsertion.setDescription("Modif réussie !");

		// On le met à jour en base
		articleVenduDAO.update(recuperationInsertion);

		// On le récupère pour le comparer
		ArticleVendu articleMisAJour = articleVenduDAO.selectById(recuperationInsertion.getNoArticle());

		assertEquals(recuperationInsertion, articleMisAJour); 

	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.ArticleVenduDAOJdbcImpl#selectAll()}.
	 */
	@Test
	public void testSelectAll() throws BusinessException {

		//Création d'une liste d'articles
		List<ArticleVendu> articles = listeArticlesBuider();
		List<ArticleVendu> test = articleVenduDAO.selectAll();
		assertEquals(articles, articleVenduDAO.selectAll());

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
	 * Méthode en charge de construire une liste d'articles pour nos tests
	 * @return List<ArticleVendu> 
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listeArticlesBuider() throws BusinessException {

		// Récupération d'une catégorie et d'un utilisateur
		Categorie categorie1 = categorieDAO.selectById(1);
		Categorie categorie2 = categorieDAO.selectById(2);
		Categorie categorie3 = categorieDAO.selectById(3);
		Utilisateur utilisateur1 = utilisateurDAO.getUtilisateurById(1);
		Utilisateur utilisateur2 = utilisateurDAO.getUtilisateurById(2);
		
		// Création des articles
		ArticleVendu articleAAjouter = new ArticleVendu();
		articleAAjouter.setNoArticle(1);
		articleAAjouter.setNomArticle("Article1");
		articleAAjouter.setDescription("Description1");
		articleAAjouter.setDateDebutEncheres(Date.valueOf("2020-04-12"));
		articleAAjouter.setDateFinEncheres(Date.valueOf("2020-05-15"));
		articleAAjouter.setMiseAPrix(15);
		articleAAjouter.setPrixVente(50);
		articleAAjouter.setUtilisateur(utilisateur1);
		articleAAjouter.setCategorie(categorie3);

		ArticleVendu articleAAjouter2 = new ArticleVendu();
		articleAAjouter2.setNoArticle(2);
		articleAAjouter2.setNomArticle("Article2");
		articleAAjouter2.setDescription("Description2");
		articleAAjouter2.setDateDebutEncheres(Date.valueOf("2020-04-13"));
		articleAAjouter2.setDateFinEncheres(Date.valueOf("2020-05-16"));
		articleAAjouter2.setMiseAPrix(5);
		articleAAjouter2.setPrixVente(40);
		articleAAjouter2.setUtilisateur(utilisateur1);
		articleAAjouter2.setCategorie(categorie2);
		
		ArticleVendu articleAAjouter3 = new ArticleVendu();
		articleAAjouter3.setNoArticle(3);
		articleAAjouter3.setNomArticle("Article3");
		articleAAjouter3.setDescription("Description3");
		articleAAjouter3.setDateDebutEncheres(Date.valueOf("2020-05-12"));
		articleAAjouter3.setDateFinEncheres(Date.valueOf("2020-05-19"));
		articleAAjouter3.setMiseAPrix(18);
		articleAAjouter3.setPrixVente(60);
		articleAAjouter3.setUtilisateur(utilisateur2);
		articleAAjouter3.setCategorie(categorie1);


		List<ArticleVendu> articles = new ArrayList<>();
		articles.add(articleAAjouter);
		articles.add(articleAAjouter2);
		articles.add(articleAAjouter3);

		return articles;
	}

}
