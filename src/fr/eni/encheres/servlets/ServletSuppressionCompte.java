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

		//Initialisation des erreurs
		List<Integer> listeCodesErreur=new ArrayList<>();
		if(listeCodesErreur.size()>0)
		{
			request.setAttribute("listeCodesErreur",listeCodesErreur);
		}
		
		UtilisateurManager userManager = UtilisateurManager.getInstance(); 
		
		//Récupération infos utilisateur courant
		HttpSession session = request.getSession(); //Récupération de la session
		session.setAttribute( "id", 2 ); // JUSTE POUR TEST A ENLEVER
		
		Utilisateur user = null;
		int id = (int) session.getAttribute("id");
		
		try {	
			user =  userManager.getUtilisateurById(id); //Récupération des infos utilisateurs
			//userManager.deleteUtilisateur(user); //Suppression
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			e.printStackTrace();
		}
	

		//TODO*** DECONNEXION
		
		//Redirection vers l'accueil
		//this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		response.getWriter().append("TODO** : SUPPRESSION");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
