package fr.eni.encheres.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import fr.eni.encheres.bll.CodesResultatBLL;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.utils.Utils;

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
		if (listeCodesErreur.size() > 0) 
		{
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		
		UtilisateurManager userManager = UtilisateurManager.getInstance(); 
		EnchereManager enchereManager = EnchereManager.getEnchereManager();
		ArticlesManager articleManager = ArticlesManager.getInstance();
		RetraitManager retraitManager = RetraitManager.getInstance();
		
		//Récupération de l'utilisateur session
		HttpSession session = request.getSession();		
		Utilisateur userSession = (Utilisateur) session.getAttribute("user");
		
		
		Enchere uneEnchere = new Enchere();
		Utilisateur unUtilisateurVendeur = new Utilisateur();
		Utilisateur unUtilisateurAcheteur = new Utilisateur();
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
			unUtilisateurVendeur = userManager.getUtilisateurById(idUser); //idUser = id du vendeur
			unUtilisateurAcheteur = userManager.getUtilisateurById(userSession.getNoUtilisateur()); //userSession peut aussi être le vendeur
			
			unArticleVendu = articleManager.getArticleById(idArticle);
			
			System.out.println(unUtilisateurVendeur);
			
			System.out.println(unUtilisateurAcheteur);
			
			//Formattage de la date
			String dateFin = Utils.getDateFormate(unArticleVendu.getDateFinEncheres(), "dd/MM/YYYY");
			request.setAttribute("dateFin", dateFin);
			
			unRetrait = retraitManager.selectById(idArticle);
			
			
			unArticleVendu.setRetrait(unRetrait);
			
			uneEnchere.setUnUtilisateur(unUtilisateurAcheteur);
			uneEnchere.setUnArticleVendu(unArticleVendu);				
			
			uneEnchere = enchereManager.selectById(uneEnchere);
			
			request.setAttribute("userSession", userSession);
			request.setAttribute("uneEnchere", uneEnchere);
			request.setAttribute("unRetrait", unRetrait);
			request.setAttribute("unArticleVendu", unArticleVendu);
			request.setAttribute("utilisateurVendeur", unUtilisateurVendeur);
			request.setAttribute("utilisateurEnchere", unUtilisateurAcheteur);
			
		
			request.setAttribute("displayNav", false); //On ne veux pas afficher de menu sur cette page
			request.setAttribute("canUpdate", true); //Apparition du bouton modifier ou non
	
			//Local date instance
	        LocalDate localDate = LocalDate.now();
	         
	        //Get LocalDate from SQL date
	        java.sql.Date today = java.sql.Date.valueOf( localDate );
	
			//On  vérifie que l'enchère n'a pas commencé (si commencée, le vendeur ne peut plus la modifier)
			if(today.getTime() > Utils.dateUtilVersSQL(unArticleVendu.getDateDebutEncheres()).getTime()) {
				request.setAttribute("canUpdate", false);
			}

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
