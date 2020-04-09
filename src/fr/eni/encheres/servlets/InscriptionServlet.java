package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
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
		String pseudo;
		String nom;
		String prenom;
		String email;
		String tel;
		String rue;
		String cp;
		String ville;
		String mdp;
		String mdpConfirmation;
		
		Utilisateur unUtilisateur = null;
		try
		{
			pseudo = request.getParameter("pseudo");
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			email = request.getParameter("email");
			tel = request.getParameter("telephone");
			rue = request.getParameter("rue");
			cp = request.getParameter("codePostal");
			ville = request.getParameter("ville");
			mdp = request.getParameter("mdp");
			mdpConfirmation = request.getParameter("mdpConfirmation");
			
			validationMotsDePasse(mdp, mdpConfirmation);
			
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
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void validationMotsDePasse(String motDePasse, String motDePasseConfirmation) throws Exception
	{
		if(!motDePasse.equals(motDePasseConfirmation))
		{
			throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");	    
		}
	}

}
