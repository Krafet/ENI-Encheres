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

		UtilisateurManager userManager = UtilisateurManager.getInstance(); 

		boolean isCurrentUser = true; //Permet de savoir si on affiche le bouton de modification ou pas

		//Si paramètre dans l'url on affiche l'utilisateur avec l'id correspondant
		if(request.getParameter("profil") != null) {
			
			int id = Integer.parseInt((request.getParameter("profil"))); //Récupération de l'id de l'utilisateur	
			isCurrentUser = false;
			
			try {	
				Utilisateur user =  userManager.getUtilisateurById(id); //Récupération des infos utilisateurs
				request.setAttribute("otherUser", user);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				e.printStackTrace();
			}
			
		}
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
