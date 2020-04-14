package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.utils.Utils;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// Initialisation des erreurs
		List<Integer> listeCodesErreur = new ArrayList<>();
		if (listeCodesErreur.size() > 0) 
		{
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		
		Utilisateur unUtilisateur = null;
		try
		{
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String tel = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String cp = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			String mdp = request.getParameter("mdp");
			String mdpConfirmation = request.getParameter("mdpConfirmation");
			
			if(validationMotsDePasse(mdp, mdpConfirmation) == false)
			{
				listeCodesErreur.add(CodesResultatServlets.PASSWORD_NON_IDENTIQUES);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp").forward(request, response); // Redirection vers le formulaire
			}
			
			mdp = Utils.toMD5(mdp);
			
			unUtilisateur = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, mdp, 0, false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
		
		try
		{
			utilisateurManager.addUtilisateur(unUtilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/Index");
			rd.forward(request, response);
		}
		catch(BusinessException e)
		{
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());	
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp").forward(request, response); // Redirection vers le formulaire
		}
	}
	
	private boolean validationMotsDePasse(String motDePasse, String motDePasseConfirmation) throws Exception
	{
		return motDePasse.equals(motDePasseConfirmation);		
	}

}
