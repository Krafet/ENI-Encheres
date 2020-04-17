package fr.eni.encheres.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		RequestDispatcher rd = null;
		
		int proposition = 0;
		int meilleureOffre = Integer.parseInt(request.getParameter("meilleureOffre"));
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		int idVendeur = Integer.parseInt(request.getParameter("idVendeur"));
		int idAcheteur = Integer.parseInt(request.getParameter("idAcheteur"));
		
		
		if(userSession == null)
		{
			listeCodesErreur.add(CodesResultatServlets.USER_NON_CONNECTER);
			this.getServletContext().getRequestDispatcher("/ServletConnexion").forward(request, response);
		}
		
		try 
		{
		
			//On  vérifie qu'une proposition a été faite
			if(!request.getParameter("proposition").equals("")) {
				 proposition = Integer.parseInt(request.getParameter("proposition"));
			}else {
				listeCodesErreur.add(CodesResultatServlets.AUCUNE_PROPOSITION);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
		
				rd = request.getRequestDispatcher("/ServletDetailVente?idVendeur=" + idVendeur + "&idArticle=" + idArticle + "&idAcheteur=" + idAcheteur);
				rd.forward(request, response);
			
			}
		
			//On vérifie que la proposition est bien supérieure a la meilleure offre
			if(proposition < meilleureOffre)
			{
				listeCodesErreur.add(CodesResultatServlets.PROPOSITION_INFERIEURE_A_MEILLEURE_OFFRE);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
				
				rd = request.getRequestDispatcher("/ServletDetailVente?idUser=" + idVendeur + "&idArticle=" + idArticle);
				rd.forward(request, response);
			}
				
			//On vérifie que l'encherisseur a suffisamment de crédit par rapport à sa proposition
			if(userSession.getCredit() < proposition)
			{
				listeCodesErreur.add(CodesResultatServlets.CREDIT_INSUFFISANT);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
				rd = request.getRequestDispatcher("/ServletDetailVente?idUser=" + idVendeur + "&idArticle=" + idArticle);
				rd.forward(request, response);
			}
			
			//Mise à jour du prix de vente de l'article avec le nouveau montant
			ArticleVendu unArticle = articlesManager.getArticleById(idArticle);
			unArticle.setPrixVente(proposition);
			articlesManager.updateArticle(unArticle);
			
			//Mise à jour de l'enchère avec les dernières informations (ou Insert ? 
			Date date = new Date();

			//DEVAIT PAS FAIRE UN UPDATE AU FINAL ? car sinon un utilisateur ne peut enchérir deux fois sur le même, conflit de clé déjà existante + on a des doublons sur l'accueil
			/*Enchere uneEnchere = new Enchere(proposition, date ,userSession, unArticle);	
			enchereManager.insert(uneEnchere); */
			
			
			//TEST avec l'update => fonctionnel
			
			Utilisateur ancienUserMeilleurOffre = new Utilisateur();
			
			Enchere enchereAUpdater = enchereManager.selectByArticle(idArticle);
			
			//Récupération de l'ancien détenteur du meilleure offre
			ancienUserMeilleurOffre = enchereAUpdater.getUnUtilisateur();
			
			enchereAUpdater.setMontantEnchere(proposition);
			enchereAUpdater.setDateEnchere(date);
			enchereAUpdater.setUnUtilisateur(userSession);

			enchereManager.updateByArticle(enchereAUpdater, userSession.getNoUtilisateur());
			
			
			//Débite les points à l'user qui vient d'enréchir		
			int creditAvantProposition = userSession.getCredit();
			int creditApresProposition = creditAvantProposition - proposition;
			userSession.setCredit(creditApresProposition);	
			
			utilisateurManager.updateUtilisateur(userSession);
						
			//Rembourse les points à l'ancien user qui détenait la meilleure offre
			
			int creditAvantRemboursement = ancienUserMeilleurOffre.getCredit();
			int creditApresRemboursement = creditAvantRemboursement + meilleureOffre;
			ancienUserMeilleurOffre.setCredit(creditApresRemboursement);
			utilisateurManager.updateUtilisateur(ancienUserMeilleurOffre);
			
			
			
			List<Integer> listeCodeSuccess = new ArrayList<>();
			listeCodeSuccess.add(CodesResultatServlets.ENCHERE_AJOUTEE);
			request.setAttribute("listeCodesSuccess",listeCodeSuccess);
			request.setAttribute("User", userSession);
			rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			rd.forward(request, response);
		}
		catch(BusinessException e) 
		{
			e.printStackTrace();			
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());	
			rd = request.getRequestDispatcher("/ServletDetailVente?idUser=" + idVendeur + "&idArticle=" + idArticle);
			rd.forward(request, response);
		}
		
	}

}
