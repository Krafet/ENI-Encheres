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
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.utils.Utils;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean errors = false;

		//Récup infos du formulaire
		String login = request.getParameter("login");
		String pass = Utils.toMD5(request.getParameter("pass"));

		UtilisateurManager userManager = UtilisateurManager.getInstance();

		try {
			Utilisateur user = userManager.getUtilisateurByPseudoPassword(login, pass); //On vérifie l'existence de l'utilisateur
			
			//Mise en place de la session utilisateur
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
		} catch (BusinessException e) {
			errors = true;
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		

		//S'il y a des erreurs on redirige vers le formulaire de login et on affiche le message
		if(errors) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
			
		//Sinon on redirige vers l'accueil
		}else{
			this.getServletContext().getRequestDispatcher("/Index").forward(request, response); //TODO***MODE CONNECTE ?
		}
			
	}

}
