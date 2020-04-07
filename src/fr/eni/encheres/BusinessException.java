package fr.eni.encheres;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe en charge de gérer les exceptions du projet
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private List<Integer> listeCodesErreur;
	
	/**
	 * 
	 * Constructeur
	 */
	public BusinessException() {
		super();
		this.listeCodesErreur=new ArrayList<>();
	}
	
	/**
	 * 
	 * Méthode en charge d'ajouter une erreur à la liste des erreurs
	 * @param code
	 */
	public void ajouterErreur(int code)
	{
		if(!this.listeCodesErreur.contains(code))
		{
			this.listeCodesErreur.add(code);
		}
	}
	
	/**
	 * 
	 * Méthode en charge de détecter s'il y a des erreurs
	 * @return boolean
	 */
	public boolean hasErreurs()
	{
		return this.listeCodesErreur.size()>0;
	}
	
	/**
	 * 
	 * Méthode en charge de récupérer la liste des erreurs
	 * @return List<Integer> 
	 */
	public List<Integer> getListeCodesErreur()
	{
		return this.listeCodesErreur;
	}

}