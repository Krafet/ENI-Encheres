package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.utils.Utils;

/**
 * Servlet implementation class ServletRemportEnchere
 */
@WebServlet("/ServletRemportEnchere")
public class ServletRemportEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRemportEnchere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
		
		//Initialisation des objets
		Utilisateur unUtilisateurVendeur = new Utilisateur();
		Utilisateur unUtilisateurAcheteur = new Utilisateur();
		ArticleVendu unArticleVendu =new ArticleVendu();
		Retrait unRetrait = new Retrait();
		
		//Récupération des paramètres (id acheteur, vendeur et article)
		int idVendeur = Integer.parseInt((request.getParameter("idVendeur")));
		int idAcheteur = Integer.parseInt((request.getParameter("idAcheteur")));
		int idArticle = Integer.parseInt((request.getParameter("idArticle")));
		
		//Si aucun utilisateur n'est conneccté => erreur (mais cas qui ne devrait pas être envisageable car le détail est accessibles aux users connectés)
		if(userSession == null)
		{
			listeCodesErreur.add(CodesResultatServlets.USER_NON_CONNECTER);

			RequestDispatcher rd = request.getRequestDispatcher("/ServletConnexion");
			rd.forward(request, response);		
		}
		
		try 
		{
			unUtilisateurVendeur = userManager.getUtilisateurById(idVendeur); 
			unUtilisateurAcheteur = userManager.getUtilisateurById(idAcheteur); 	
			unArticleVendu = articleManager.getArticleById(idArticle);

			//Formattage de la date
			String dateFin = Utils.getDateFormate(unArticleVendu.getDateFinEncheres(), "dd/MM/YYYY");
			request.setAttribute("dateFin", dateFin);
			
			unRetrait = retraitManager.selectById(idArticle); //Retrait associé à l'article
			unArticleVendu.setRetrait(unRetrait);

			request.setAttribute("userSession", userSession);
			request.setAttribute("unArticleVendu", unArticleVendu);
			request.setAttribute("utilisateurVendeur", unUtilisateurVendeur);
			request.setAttribute("meilleurEncherisseur", unUtilisateurAcheteur);
			
		
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
			
			
			
			//Crediter le vendeur si c'est la premiere fois
			
			if(unArticleVendu.getMiseAPrix() != -1)
			{
				unUtilisateurVendeur.setCredit(unUtilisateurVendeur.getCredit() + unArticleVendu.getPrixVente());
				unArticleVendu.setMiseAPrix(-1);
				
				userManager.updateUtilisateur(unUtilisateurVendeur);
				articleManager.updateArticle(unArticleVendu);
			}
			
			

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/RemportEnchere.jsp");
			rd.forward(request, response);
		}
		catch(BusinessException e)
		{
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			rd.forward(request, response);
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
