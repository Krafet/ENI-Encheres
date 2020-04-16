package fr.eni.encheres.servlets;

import java.io.IOException;
import java.text.DateFormat;
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
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEncherirVente
 */
@WebServlet("/ServletEncherirVente")
public class ServletEncherirVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEncherirVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Initialisation des erreurs
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
		ArticlesManager articlesManager = ArticlesManager.getInstance();
		EnchereManager enchereManager = EnchereManager.getEnchereManager();
		
		Utilisateur unUtilisateur = new Utilisateur();
		
		//Récupération de l'utilisateur session
		HttpSession session = request.getSession();		
		Utilisateur userSession = (Utilisateur) session.getAttribute("user");
		
		if(userSession == null)
		{
			listeCodesErreur.add(CodesResultatServlets.USER_NON_CONNECTER);
			this.getServletContext().getRequestDispatcher("/ServletConnexion").forward(request, response);
		}
		
		try 
		{
			int proposition = Integer.parseInt(request.getParameter("proposition"));
			int meilleureOffre = Integer.parseInt(request.getParameter("meilleureOffre"));
			int idArticle = Integer.parseInt(request.getParameter("idArticle"));

			if(proposition > meilleureOffre)
			{
				listeCodesErreur.add(CodesResultatServlets.PROPOSITION_INFERIEURE_A_MEILLEURE_OFFRE);
				this.getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
			}
						
			if(userSession.getCredit() < proposition)
			{
				listeCodesErreur.add(CodesResultatServlets.CREDIT_INSUFFISANT);
				this.getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
			}
			
			ArticleVendu unArticle = articlesManager.getArticleById(idArticle);
			unArticle.setMiseAPrix(proposition);
			
			articlesManager.updateArticle(unArticle);
			
			Date date = new Date();

			System.out.println(proposition + idArticle + meilleureOffre + date.toString());
			Enchere uneEnchere = new Enchere(proposition, date ,userSession, unArticle);
			
			enchereManager.insert(uneEnchere);
		}
		catch(BusinessException e) 
		{
			e.printStackTrace();			
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());	
			this.getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
		}
	}

}
