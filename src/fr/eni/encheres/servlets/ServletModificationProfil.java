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
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/ServletModificationProfil")
public class ServletModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModificationProfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Initialisation des erreurs
		List<Integer> listeCodesErreur = new ArrayList<>();
		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}

		UtilisateurManager userManager = UtilisateurManager.getInstance();

		// Récupération infos utilisateur courant
		HttpSession session = request.getSession(); // Récupération de la session
		session.setAttribute("id", 2); // JUSTE POUR TEST A ENLEVER

		Utilisateur user = null;
		int id = (int) session.getAttribute("id");

		try {
			user = userManager.getUtilisateurById(id);

		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}

		request.setAttribute("user", user);

		// Redirection vers le formulaire de modif
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ModificationProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// Initialisation des erreurs
		List<Integer> listeCodesErreur = new ArrayList<>();
		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}

		
		// Récupération des infos du formulaire
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("actual_pass");
		String newMotDePasse = request.getParameter("new_pass");
		String confirmation = request.getParameter("confirm_pass");

	
		//TODO*** GESTION DU MOT DE PASSE CRYPTE
		// Si pas de changement de mot de passe
		if (!newMotDePasse.equals("") && !confirmation.equals("")) {
			
			// Si les mots de passe sont identiques
			if (newMotDePasse.equals(confirmation)) {
				motDePasse = newMotDePasse;

			}else {
				//TODO** ajouter erreur pour l'afficher
				//listeCodesErreur.add(CodesResultatServlets.PASSWORD_NON_IDENTIQUES);
	
				//this.getServletContext().getRequestDispatcher("/ServletModificationProfil").forward(request, response); // Redirection vers le formulaire
			}
		}

		// TODO*** Les contrôles (format..)
		
		Utilisateur newInfosUser = new Utilisateur();
		
		newInfosUser.setPseudo(pseudo);
		newInfosUser.setNom(nom);
		newInfosUser.setPrenom(prenom);
		newInfosUser.setEmail(email);
		newInfosUser.setTelephone(tel);
		newInfosUser.setRue(rue);
		newInfosUser.setCodePostal(codePostal);
		newInfosUser.setVille(ville);
		newInfosUser.setMotDePasse(motDePasse);
	
		
		//Update utilisateur
		UtilisateurManager userManager = UtilisateurManager.getInstance();
		
		HttpSession session = request.getSession(); //Récupération de la session
		session.setAttribute( "id", 2 ); // JUSTE POUR TEST A ENLEVER
		int id = (int) session.getAttribute("id");
		newInfosUser.setNoUtilisateur(id);
		
		try {
			userManager.updateUtilisateur(newInfosUser);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		// Redirection vers le profil utilisateur
		this.getServletContext().getRequestDispatcher("/ServletAffichageProfil").forward(request, response);
	}

}
