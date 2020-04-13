package fr.eni.encheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération des catégories pour le select
		CategorieManager categorieManager;
		try {
			categorieManager = CategorieManager.getCategorieManager();
			List<Categorie> categories = categorieManager.getAllCategories();
			request.setAttribute("categories", categories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		//Récupération date du jour
	      String today =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	      request.setAttribute("today", today);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/VenteArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Utilisateur currentUser = (Utilisateur) session.getAttribute("user");
		
		//Récupération des champs du formulaire 
		String nom = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		int categorieId = Integer.parseInt(request.getParameter("categorie")); //TODO*** Rajouter champs en base pour gérer la photo
		String photo = request.getParameter("file");
		int prixInitial = Integer.parseInt(request.getParameter("prix"));
		String dateDebut = request.getParameter("debutEnchere");
		String dateFin = request.getParameter("finEnchere");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		//Construction de l'article
		ArticleVendu article = new ArticleVendu();
		article.setNomArticle(nom);
		article.setDescription(description);
		article.setMiseAPrix(prixInitial);
		article.setUtilisateur(currentUser);
		
		//Gestion des dates
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Date dateDebutParsed = formatter.parse(dateDebut);
			Date dateFinParsed = formatter.parse(dateFin);
		    java.sql.Date dateDebutSql = new java.sql.Date(dateDebutParsed.getTime());
		    java.sql.Date dateFinSql = new java.sql.Date(dateFinParsed.getTime());
			article.setDateDebutEncheres(dateDebutSql);
			article.setDateFinEncheres(dateFinSql);
			
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		//On récupère la catégorie de l'article à partir de son id
		try {
			CategorieManager categorieManager = CategorieManager.getCategorieManager();
			Categorie categorie = categorieManager.getByID(categorieId);		
			article.setCategorie(categorie);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}

		//Mise en place du lieu de retrait pour l'article concerné
		Retrait retrait = new Retrait();
		retrait.setRue(rue);
		retrait.setCodePostal(codePostal);
		retrait.setVille(ville);
		article.setRetrait(retrait);
		
		//Insertion article
		ArticlesManager articleManager = ArticlesManager.getInstance();
		try {
			articleManager.addArticle(article);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		

		//Insertion retrait si l'insertion de l'article a réussi
		RetraitManager retraitManager;
		try {
			retraitManager = RetraitManager.getInstance();
			retraitManager.addRetrait(retrait, article);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	
		
		//Redirection
		//this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/VenteArticle.jsp").forward(request, response); //TODO***Revoir page de redirection
	}

}
