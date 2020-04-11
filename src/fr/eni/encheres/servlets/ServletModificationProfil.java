package fr.eni.encheres.servlets;

import java.io.IOException;
import java.net.HttpRetryException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import fr.eni.encheres.utils.Utils;

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

		
		/*******************************
		 * RECUP INFOS FORM + CONTROLES
		 ********************************/
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
		int credit = Integer.parseInt(request.getParameter("credit"));
		
		// Si changement de mot de passe
		if (!newMotDePasse.equals("") && !confirmation.equals("")) {
			
			// Si les mots de passe sont identiques
			if (newMotDePasse.equals(confirmation)) {	
				motDePasse = Utils.toMD5(newMotDePasse);
			}else {
			
				listeCodesErreur.add(CodesResultatServlets.PASSWORD_NON_IDENTIQUES);			
			}
		}

		
		/******************************
		 * ACTION DE MODIFICATION
		 ***************************/
		
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
		newInfosUser.setCredit(credit);
	
		
		//Update utilisateur
		UtilisateurManager userManager = UtilisateurManager.getInstance();
		
		Utilisateur currentUser = (Utilisateur) request.getSession().getAttribute("user");
		newInfosUser.setNoUtilisateur(currentUser.getNoUtilisateur());
		
		try {
			userManager.updateUtilisateur(newInfosUser);
		} catch (BusinessException e) {
			e.printStackTrace();
		}


		//Si les mots de passe ne sont pas identiques on redirige sur la même page avec le message d'erreur
		if(listeCodesErreur.contains(CodesResultatServlets.PASSWORD_NON_IDENTIQUES)) {
			
			request.setAttribute("listeCodesErreur",listeCodesErreur);	
			request.setAttribute("user", currentUser);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ModificationProfil.jsp").forward(request, response); // Redirection vers le formulaire		
		
			//Sinon on redirige vers le profil avec les modifs effectuées
		}else{
			request.getSession().setAttribute("user", newInfosUser); //On met à jour la session
			this.getServletContext().getRequestDispatcher("/ServletAffichageProfil").forward(request, response);
		}
	}

}
