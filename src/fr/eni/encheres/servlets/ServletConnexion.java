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
		

		// Initialisation des erreurs
		List<Integer> listeCodesErreur = new ArrayList<>();
		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}

		
		/**
		 * TODO
		 * - Vérif si identifiants corrects (à savoir si l'user existe) => niveau DAL (voir O.)
		 * - Mise en place session => DOING
		 * - Redirection vers accueil => Attendre d'avoir le nom servlet et jsp
		 */
		
		//Récup infos du formulaire
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		
		pass = Utils.toMD5(pass);
		
		//On vérifie l'existence de l'utilisateur
		UtilisateurManager userManager = UtilisateurManager.getInstance();

		try {
			Utilisateur user = userManager.getUtilisateurByPseudoPassword(login, pass); //TODO*** Changer la fonction de O. pour permettre de choisir aussi grâce à l'email
			
			//Mise en place de la session utilisateur
			HttpSession session = request.getSession(); // Récupération de la session
			session.setAttribute("user", user);
			
		} catch (BusinessException e) {
			listeCodesErreur.add(CodesResultatDAL.UTILISATEUR_INEXISTANT);			
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}
		

		//S'il n'y a pas d'erreurs
		if(listeCodesErreur.contains(CodesResultatDAL.UTILISATEUR_INEXISTANT)) {
			
			request.setAttribute("listeCodesErreur",listeCodesErreur);	
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response); // Redirection vers le login	
		
			//Sinon on redirige vers l'accueil
		}else{
			this.getServletContext().getRequestDispatcher("/ServletAffichageProfil").forward(request, response); //TODO***Revoir le nom de la page d'accueil ou la servlet d'accueil 
			//this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response); //TODO***Revoir le nom de la page d'accueil ou la servlet d'accueil 
		}
			
	}

}
