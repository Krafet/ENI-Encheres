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
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * 
 * Classe en charge d'effectuer les traitements nécessaire pour l'affichage des profils
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 9 avr. 2020
 */
@WebServlet("/ServletAffichageProfil")
public class ServletAffichageProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAffichageProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Initialisation des erreurs
		List<Integer> listeCodesErreur=new ArrayList<>();
		if(listeCodesErreur.size()>0)
		{
			request.setAttribute("listeCodesErreur",listeCodesErreur);
		}

		
		UtilisateurManager userManager = UtilisateurManager.getInstance(); 
		
		int id = 0;
		Utilisateur user = null;
		boolean isCurrentUser = true; //Permet de savoir si on affiche le bouton de modification ou pas

		//Si pas de paramètre dans l'url on récupère l'utilisateur courant grâce à la session
		if(request.getParameter("profil") == null) {
			
				HttpSession session = request.getSession(); //Récupération de la session
			
				//OPTION 1 : on récupère tout depuis la session (mais si pas array associatif demande de tout mettre en attribut un par un
				//Ex : request.setAttribute("nom", session.getAttribute("nom"));
			
				//OPTION 2 : je récupère juste l'id et vais chercher l'user en base
			
				session.setAttribute( "id", 2 ); // JUSTE POUR TEST A ENLEVER
				id = (int) session.getAttribute("id");
			
				
			//Sinon on récupère le profil de l'utilisateur indiqué en paramètre	
		}else {		
			id = Integer.parseInt((request.getParameter("profil"))); //Récupération de l'id de l'utilisateur	
			isCurrentUser = false;
		}
		
		try {	
			user =  userManager.getUtilisateurById(id); //Récupération des infos utilisateurs
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			e.printStackTrace();
		}
		
		//On passe l'user en param
		request.setAttribute("user", user);
		request.setAttribute("isCurrentUser",isCurrentUser);
		
		//Redirection vers la page de profil
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/AffichageProfil.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
