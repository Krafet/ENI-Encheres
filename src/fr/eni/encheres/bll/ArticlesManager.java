/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.utils.Utils;

/**
 * Classe en charge de gérer les fonctionnalités en rapport avec les
 * ArticlesVendus
 * 
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 8 avr. 2020
 */
public class ArticlesManager {

	private ArticleVenduDAO articleVenduDAO;
	private EnchereDAO enchereDAO;
	private RetraitDAO retraitDAO;
	private static ArticlesManager instance;

	/**
	 * 
	 * Constructeur
	 */
	private ArticlesManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	/**
	 * 
	 * Méthode en charge d'éviter dans un environnement multithreadé que deux
	 * threads puissent exécuter le test simultanément et créer ainsi chacun une
	 * instance du singleton.
	 * 
	 * @return
	 */
	public static synchronized ArticlesManager getInstance() {
		if (instance == null) {
			instance = new ArticlesManager();
		}
		return instance;
	}

	/**
	 * 
	 * Méthode en charge d'ajouter un article
	 * 
	 * @param article
	 * @return ArticleVendu
	 * @throws BusinessException
	 */
	public ArticleVendu addArticle(ArticleVendu article) throws BusinessException {

		BusinessException businessException = new BusinessException();
		ArticleVendu newArticle = null;
		this.checkArticle(article, businessException);

		if (!businessException.hasErreurs()) {
			newArticle = this.articleVenduDAO.insert(article);
		} else {
			throw businessException;
		}
		;
		return newArticle;
	}

	/**
	 * 
	 * Méthode en charge de récupérer un article via son id
	 * @param id
	 * @return ArticleVendu
	 * @throws BusinessException
	 */
	public ArticleVendu getArticleById(int id) throws BusinessException {

		return this.articleVenduDAO.selectById(id);
	}

	/**
	 * 
	 * Méthode en charge de supprimer un article via son id
	 * 
	 * @param id
	 * @return boolean
	 * @throws BusinessException
	 */
	public boolean removeArticle(int id) throws BusinessException {

		/*
		 * TODO*** : En fonction de l'endroit où sera appelé la fonction faire d'abord
		 * une suppression des enchères et des retraits associés ici Sauf si ces
		 * traitements sont fait avant d'appeler cette fonction
		 */

		return this.articleVenduDAO.delete(id);
	}

	/**
	 * 
	 * Méthode en charge de supprimer un article via son id
	 * 
	 * @param id
	 * @return boolean
	 * @throws BusinessException
	 */
	public boolean removeArticleByUser(int userId) throws BusinessException {

		return this.articleVenduDAO.deleteByUser(userId);
	}

	/**
	 * 
	 * Méthode en charge de récupérer tous les articles
	 * 
	 * @param id
	 * @return boolean
	 * @throws BusinessException
	 */
	public List<ArticleVendu> getAllArticles() throws BusinessException {

		return this.articleVenduDAO.selectAll();
	}

	/**
	 * 
	 * Méthode en charge de modifier un article
	 * 
	 * @param article
	 * @return boolean
	 * @throws BusinessException
	 */
	public boolean updateArticle(ArticleVendu article) throws BusinessException {

		return this.articleVenduDAO.update(article);

	}

	public void checkArticle(ArticleVendu article, BusinessException businessException) throws BusinessException {

		// On vérifie que tous les champs sont remplis
		if (article.getNomArticle().trim().equals("") || article.getDescription().trim().equals("")
				|| article.getCategorie() == null || article.getDateDebutEncheres() == null
				|| article.getDateFinEncheres() == null) {

			businessException.ajouterErreur(CodesResultatBLL.UN_CHAMP_NON_SAISI);
			throw businessException;
		}
		// On vérifie que le nombre de point est positif
		if (article.getMiseAPrix() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.NOMBRE_NEGATIF);
			throw businessException;
		}

		// On vérifie que la date de fin est bien postérieure à la date de début (même si vérifié côté client)
		if(article.getDateFinEncheres().getTime() <= article.getDateDebutEncheres().getTime()) {
			businessException.ajouterErreur(CodesResultatBLL.DATES_INCOHERENTE);
			throw businessException;
		}

		if (articleVenduDAO.checkIfArticleAlreadyExists(article)) {
			businessException.ajouterErreur(CodesResultatBLL.ARTICLE_EXISTANT);
			throw businessException;
		}

	}


}
