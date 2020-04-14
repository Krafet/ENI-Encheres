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
 * Servlet implementation class ServletSuppressionCompte
 */
@WebServlet("/ServletSuppressionCompte")
public class ServletSuppressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSuppressionCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Initialisation des erreurs
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		//Récupération de l'utilisateur courant
		HttpSession session = request.getSession();
		Utilisateur currentUser = (Utilisateur) session.getAttribute("user");
		
		//Suppression de l'utilisateur
		UtilisateurManager userManager = UtilisateurManager.getInstance();
		try {
			userManager.deleteUtilisateur(currentUser);
		} catch (BusinessException e) {
			listeCodesErreur.addAll( e.getListeCodesErreur());
		}
		
		//Déconnexion de l'utilisateur => on détruit la session
		session.removeAttribute("user");
		session.invalidate();

		//Redirection vers le profil si erreur
		if(listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			request.setAttribute("user", currentUser);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ModificationProfil.jsp").forward(request, response);
		
		//Sinon redirection vers l'accueil
		}else {
			this.getServletContext().getRequestDispatcher("/Index").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
