
package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
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
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletModificationArticle
 */
@WebServlet("/modifier_article")
public class ServletModificationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificationArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

			UtilisateurManager userManager = UtilisateurManager.getInstance(); 
			ArticlesManager articleManager = ArticlesManager.getInstance();
			RetraitManager retraitManager = RetraitManager.getInstance();
			
			//Récupération de l'utilisateur session
			HttpSession session = request.getSession();		
			Utilisateur userSession = (Utilisateur) session.getAttribute("user");
			
			//Initialisation des objets
			Utilisateur unUtilisateurVendeur = new Utilisateur();
			Utilisateur unUtilisateurAcheteur = new Utilisateur();
			
			//Récupération des paramètres (id acheteur, vendeur et article)
			int idVendeur = Integer.parseInt((request.getParameter("idVendeur")));
			int idAcheteur = Integer.parseInt((request.getParameter("idAcheteur")));
			int idArticle = Integer.parseInt((request.getParameter("idArticle")));
			
			if(userSession.getNoUtilisateur()!=idVendeur)
			{
				//on redirige si l'user current n'est pas le propriétaire de l'article
				this.getServletContext().getRequestDispatcher("/Index").forward(request, response);
			}
			
			try 
			{
				// Initialisation des erreurs
				List<Integer> listeCodesErreur = new ArrayList<>();
				if (listeCodesErreur.size() > 0) {
					request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
				
				unUtilisateurVendeur = userManager.getUtilisateurById(idVendeur); 
				unUtilisateurAcheteur = userManager.getUtilisateurById(idAcheteur); 	
				ArticleVendu article = articleManager.getArticleById(idArticle);
				
				System.out.println("idArticle "+ idArticle);
				System.out.println("article "+ article);

				request.setAttribute("userSession", userSession);
				request.setAttribute("article", article);
				request.setAttribute("idAcheteur", idAcheteur);
			
				request.setAttribute("displayNav", false); //On ne veux pas afficher de menu sur cette page
				request.setAttribute("canUpdate", true); //Apparition du bouton modifier ou non
		        
				//On récupère les catégories
				CategorieManager categorieManager = CategorieManager.getCategorieManager();
				List<Categorie> lesCategories = categorieManager.getAllCategories();
				request.setAttribute("categories", lesCategories);
		
		        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ModificationArticle.jsp").forward(request, response);
			}
			catch (BusinessException e) 
			{
				e.printStackTrace();
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//Récupération des paramètres (id acheteur, vendeur et article)
			int idVendeur = Integer.parseInt((request.getParameter("idVendeur")));
			int idArticle = Integer.parseInt((request.getParameter("idArticle")));
			int idAcheteur = Integer.parseInt((request.getParameter("idAcheteur")));

			//Récupération de l'utilisateur session
			HttpSession session = request.getSession();		
			Utilisateur userSession = (Utilisateur) session.getAttribute("user");

			//On s'assure toujours s'il s'agit bien du propriétaire
			
			if(userSession.getNoUtilisateur()!=idVendeur)
			{
				//on redirige si l'user current n'est pas le propriétaire de l'article
				this.getServletContext().getRequestDispatcher("/Index").forward(request, response);
			}
			
			
			// Initialisation des erreurs
			List<Integer> listeCodesErreur = new ArrayList<>();
			boolean errors = false;

			// On récupure les paramètres
			
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
			int prixVente = Integer.parseInt(request.getParameter("prixVente"));
			int categorieId = Integer.parseInt(request.getParameter("categorie"));
			
			ArticlesManager articlesManager = ArticlesManager.getInstance();
			
			try 
			{
				//On récupère l'article correspondant 
				ArticleVendu unArticle = (ArticleVendu) articlesManager.getArticleById(idArticle);
				
				//On applique les changements
				unArticle.getCategorie().setNoCategorie(categorieId);
				unArticle.setNomArticle(nomArticle);
				unArticle.setDescription(description);
				unArticle.setPrixVente(prixVente);
				unArticle.setMiseAPrix(miseAPrix);
				
				//On mets à jour l'article
				articlesManager.updateArticle(unArticle);
			} 
			catch (BusinessException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		//on redirige
		this.getServletContext().getRequestDispatcher("/ServletDetailVente?idAcheteur="+idAcheteur+"&idArticle="+idArticle+"&idVendeur="+idVendeur).forward(request, response);
	}
}

