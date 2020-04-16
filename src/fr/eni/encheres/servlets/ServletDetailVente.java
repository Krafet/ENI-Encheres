package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/ServletDetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetailVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Initialisation des erreurs
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		UtilisateurManager userManager = UtilisateurManager.getInstance(); 
		EnchereManager enchereManager = EnchereManager.getEnchereManager();
		ArticlesManager articleManager = ArticlesManager.getInstance();
		RetraitManager retraitManager = RetraitManager.getInstance();
		
		//Récupération de l'utilisateur session
		HttpSession session = request.getSession();		
		Utilisateur userSession = (Utilisateur) session.getAttribute("user");
		
		
		Enchere uneEnchere = new Enchere();
		Utilisateur unUtilisateur = new Utilisateur();
		ArticleVendu unArticleVendu = new ArticleVendu();
		Retrait unRetrait = new Retrait();
		
		//Récupération des paramètres idUser et idArticle
		int idUser = Integer.parseInt((request.getParameter("idUser")));
		int idArticle = Integer.parseInt((request.getParameter("idArticle")));
		
		if(userSession == null)
		{
			listeCodesErreur.add(CodesResultatServlets.USER_NON_CONNECTER);
			this.getServletContext().getRequestDispatcher("/ServletConnexion").forward(request, response);
		}
		
		try 
		{
			unUtilisateur = userManager.getUtilisateurById(idUser);
			unArticleVendu = articleManager.getArticleById(idArticle);
			
			unRetrait = retraitManager.selectById(idArticle);
			
			
			unArticleVendu.setRetrait(unRetrait);
			
			uneEnchere.setUnUtilisateur(unUtilisateur);
			uneEnchere.setUnArticleVendu(unArticleVendu);				
			
			uneEnchere = enchereManager.selectById(uneEnchere);
			
			request.setAttribute("userSession", userSession);
			request.setAttribute("uneEnchere", uneEnchere);
			request.setAttribute("unRetrait", unRetrait);
			
			BusinessException e = new BusinessException();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/DetailVente.jsp");
			rd.forward(request, response);
		}
		catch(BusinessException e)
		{
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
