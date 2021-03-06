package fr.eni.encheres.bll;

/**
 * 
 * Classe en charge d'associer les constantes d'erreurs à des codes associés eux-même à des messages d'erreurs de la BLL
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class CodesResultatBLL {	

	/**
	 * Le pseudo n'est pas en alphanumériques.
	 */
	public static final int PSEUDO_NON_ALPHANUMERIQUE=20000;
	
	/**
	 * Le format de l'email est incorrect. 
	 */
	public static final int EMAIL_FORMAT_INCORRECT=20001;
	
	/**
	 * Echec lors de la récupération d'un article 
	 */
	public static final int UN_CHAMP_NON_SAISI=20002;
	
	/**
	 * Le pseudo est déjà existant
	 */
	public static final int PSEUDO_EXISTANT=20003;
	
	/**
	 * L'email est déjà existant
	 */
	public static final int EMAIL_EXISTANT=20004;
	
	/**
	 * Si la mise à prix est négative
	 */
	public static final int NOMBRE_NEGATIF=20005;
	
	/**
	 * L'article existe déjà en base
	 */
	public static final int ARTICLE_EXISTANT=20006;
	
	/**
	 * Le format du téléphone est incorrect
	 */
	public static final int TELEPHONE_FORMAT_INCORRECT=20007;
	
	/**
	 * Le format du code postal est incorrect
	 */
	public static final int CP_FORMAT_INCORRECT=20008;
	
	/**
	 * La date de fin se situe avant la date du début d l'enchère
	 */
	public static final int DATES_INCOHERENTE=20009;
		
}
