package fr.eni.encheres.servlets;

/**
 * 
 * Classe en charge d'associer les constantes d'erreurs/success à des codes associés eux-même à des messages d'erreurs des servlets
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class CodesResultatServlets {
	
	/**************
	 * ERREURS
	 **************/
	
	/**
	 * Erreur si saisie de mot de passe non identiques
	 */
	public static final int PASSWORD_NON_IDENTIQUES=30000;
	
	/**
	 * Erreur si le mot de passe actuel saisi n'est pas le bon
	 */
	public static final int PASSWORD_ACTUEL_INCORRECT=30001;
	

	/**
	 * Si le mdp actuel est bon mais que le nouveau mdp et la confirmation ne sont pas remplis
	 */
	public static final int PASSWORDS_MANQUANT=30002;
	
	/**
	 * Si la variable session est null (Utilisateur non connecter à son compte).
	 */
	public static final int USER_NON_CONNECTER=30003;
	
	/******************
	 * SUCCESS
	 *****************/
	
	/**
	 * Article ajouté
	 */
	public static final int ARTICLE_AJOUTE = 30100;
	
	/**
	 * Inscription réussie
	 */
	public static final int INSCRIPTION_REUSSIE = 30101;
	
	/**
	 * Modification réussie
	 */
	public static final int MODIFICATION_REUSSIE = 30102;
	
	/**
	 * Suppression réussie
	 */
	public static final int SUPPRESSION_REUSSIE = 30103;
	
	

}
