package fr.eni.encheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.utils.Utils;

/**
 * Servlet implementation class ServletVenteArticles
 */
@WebServlet("/ServletVenteArticles")
public class ServletVenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletVenteArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getInfosSellForm(request);
		request.setAttribute("displayNav", false); //On ne veux pas afficher de menu sur cette page
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/VenteArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean errors = false;

		HttpSession session = request.getSession();
		Utilisateur currentUser = (Utilisateur) session.getAttribute("user");

		// Récupération des champs du formulaire
		String nom = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		int categorieId = Integer.parseInt(request.getParameter("categorie"));
		String photo = (!request.getParameter("file").equals("")) ? request.getParameter("file") : "close2.png";
		int prixInitial = Integer.parseInt(request.getParameter("prix"));

		String rue = (!request.getParameter("rue").equals("")) ? request.getParameter("rue") : currentUser.getRue();
		String codePostal = (!request.getParameter("codePostal").equals("")) ? request.getParameter("codePostal") : currentUser.getCodePostal();
		String ville = (!request.getParameter("ville").equals("")) ? request.getParameter("ville") : currentUser.getVille();

		// Construction de l'article
		ArticleVendu article = new ArticleVendu();
		article.setNomArticle(nom);
		article.setDescription(description);
		article.setMiseAPrix(prixInitial);
		article.setPicture(photo);
		article.setUtilisateur(currentUser);

		// Gestion des dates

		try {
			Date debutEnchere = Utils.stringVersUtil(request.getParameter("debutEnchere"));
			Date finEnchere = Utils.stringVersUtil(request.getParameter("finEnchere"));
			article.setDateDebutEncheres(debutEnchere);
			article.setDateFinEncheres(finEnchere);	
		
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		// On récupère la catégorie de l'article à partir de son id
		CategorieManager categorieManager = CategorieManager.getCategorieManager();
		Categorie categorie = categorieManager.getByID(categorieId);
		article.setCategorie(categorie);

		// Mise en place du lieu de retrait pour l'article concerné
		Retrait retrait = new Retrait();
		retrait.setRue(rue);
		retrait.setCodePostal(codePostal);
		retrait.setVille(ville);

		// Insertion article
		ArticlesManager articleManager = ArticlesManager.getInstance();
		ArticleVendu articleAjoute = null;
		
		

		try {
			articleAjoute = articleManager.addArticle(article);

			RetraitManager retraitManager = RetraitManager.getInstance();
			retraitManager.addRetrait(retrait, articleAjoute);
			
			//Creation enchere
			Enchere enchere = new Enchere();
			enchere.setUnArticleVendu(articleAjoute);
			enchere.setUnUtilisateur(currentUser);
			enchere.setDateEnchere(article.getDateFinEncheres());
			enchere.setMontantEnchere(prixInitial);
			
			
			EnchereManager enchereManager = EnchereManager.getEnchereManager();
			try {
				enchereManager.insert(enchere);
			} catch (BusinessException e) {
				errors = true;
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}

		} catch (BusinessException e1) {
			errors = true;
			request.setAttribute("listeCodesErreur", e1.getListeCodesErreur());
		}
				
		
		// S'il y a des erreurs on redirige vers le formulaire de login et on affiche le message
		if (errors) {
			this.getInfosSellForm(request);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/VenteArticle.jsp").forward(request, response);

			// Sinon on redirige vers l'accueil
		} else {
			List<Integer> listeCodeSuccess = new ArrayList<>();
			listeCodeSuccess.add(CodesResultatServlets.ARTICLE_AJOUTE);
			request.setAttribute("listeCodesSuccess",listeCodeSuccess);
			this.getServletContext().getRequestDispatcher("/Index").forward(request, response);
		}
	}

	/**
	 * 
	 * Méthode en charge de récupérer les informations pour le formulaire d'ajout
	 * d'article
	 * 
	 * @param request
	 */
	private void getInfosSellForm(HttpServletRequest request) {

		// Récupération des catégories pour le select
		CategorieManager categorieManager;
		try {
			categorieManager = CategorieManager.getCategorieManager();
			List<Categorie> categories = categorieManager.getAllCategories();
			request.setAttribute("categories", categories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		// Récupération date du jour
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		request.setAttribute("today", today);

	}

}
