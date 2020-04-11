package fr.eni.encheres.dal;

/**
 * 
 * Classe en charge d'associer les constantes d'erreurs à des codes associés eux-même à des messages d'erreurs de la DAL
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec lors de la récupération d'un article 
	 */
	public static final int SELECTION_ARTICLE_ERREUR=10002;
	
	/**
	 * Erreur à la récupération des articles
	 */
	public static final int ERREUR_RECUPERATION_ARTICLES_VENDUS = 10003;

	/**
	 * Erreur à la suppression d'un article
	 */
	public static final int SUPPRESSION_ARTICLE_ERREUR = 10005;
	
	/**
	 * Erreur à la modification d'un article
	 */
	public static final int MODIFICATION_ARTICLE_ERREUR = 10006;
	
	/**
	 * Echec lors de la récupération d'un utilisateur 
	 */
	public static final int ERREUR_RECUPERATION_UTILISATEUR=10101;
	
	/**
	 * Erreur à la récupération des utilisateurs
	 */
	public static final int ERREUR_RECUPERATION_UTILISATEURS = 10102;

	/**
	 * Erreur à la suppression d'un utilisateur
	 */
	public static final int SUPPRESSION_UTILISATEUR = 10103;
	
	/**
	 * Erreur à la modification d'un utilisateur
	 */
	public static final int MODIFICATION_UTILISATEUR = 10104;
	
	/**
	 * Erreur quand l'utilisateur est inexistant
	 */
	public static final int UTILISATEUR_INEXISTANT = 10105;
	
	/**
	 * Le pseudo est déjà existant
	 */
	public static final int PSEUDO_EXISTANT=10106;
	
	/**
	 * L'email est déjà existant
	 */
	public static final int EMAIL_EXISTANT=10107;
	
	/**
	 * L'email est déjà existant
	 */
	public static final int ERREUR_IDENTIFIANTS=10108;
	
	/**
	 * Echec lors de la récupération d'une catégorie 
	 */
	public static final int SELECTION_CATEGORIE_ERREUR=10201;
	
	/**
	 * Erreur à la récupération des catégories
	 */
	public static final int ERREUR_RECUPERATION_CATEGORIES = 10202;

	/**
	 * Erreur à la suppression d'une catégorie
	 */
	public static final int SUPPRESSION_CATEGORIE_ERREUR = 10203;
	
	/**
	 * Erreur à la modification d'une catégorie
	 */
	public static final int MODIFICATION_CATEGORIE_ERREUR = 10204;
	
	
	/**
	 * Echec lors de la récupération d'un retrait 
	 */
	public static final int SELECTION_RETRAIT_ERREUR=10301;
	
	/**
	 * Erreur à la récupération des retraits
	 */
	public static final int ERREUR_RECUPERATION_RETRAITS = 10302;

	/**
	 * Erreur à la suppression d'un retrait
	 */
	public static final int SUPPRESSION_RETRAIT_ERREUR = 10303;
	
	/**
	 * Erreur à la modification d'un retrait
	 */
	public static final int MODIFICATION_RETRAIT_ERREUR = 10304;
	
	
	/**
	 * Echec lors de la recuperation d'une enchere
	 */
	public static final int SELECTION_ENCHERE_ERREUR=10402;
	
	/**
	 * Erreur à la recuperation des encheres
	 */
	public static final int ERREUR_RECUPERATION_DES_ENCHERES = 10403;
	
	/**
	 * Erreur a�la supression d'une enchere
	 */

	public static final int SUPPRESSION_ENCHERE_ERREUR = 10404;
	
	/**
	 * Erreur à la modification d'une enchere
	 */
	public static final int MODIFICATION_ENCHERE_ERREUR = 10405;
}
