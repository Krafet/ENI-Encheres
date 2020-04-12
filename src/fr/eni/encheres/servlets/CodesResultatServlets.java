package fr.eni.encheres.servlets;

/**
 * 
 * Classe en charge d'associer les constantes d'erreurs à des codes associés eux-même à des messages d'erreurs des servlets
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class CodesResultatServlets {
	
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
	

}
