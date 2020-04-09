package fr.eni.encheres.messages;

import java.util.ResourceBundle;
/**
 * 
 * Classe en charge de lire le contenu du fichier messages_erreur.properties
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class LecteurMessage {
	
	private static ResourceBundle rb;
	
	static
	{
		try
		{
			rb = ResourceBundle.getBundle("fr.eni.encheres.messages.messages_erreurs");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Constructeur
	 */
	private LecteurMessage()
	{
		
	}
	/**
	 * 
	 * Méthode en charge de récupérer le message d'erreur associé au code renseigné en paramètre
	 * @param code
	 * @return
	 */
	public static  String getMessageErreur(int code)
	{
		String message="";
		try
		{
			if(rb!=null)
			{
				message = rb.getString(String.valueOf(code));
			}
			else
			{
				message="Problème à la lecture du fichier contenant les messages";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message="Une erreur inconnue est survenue";
		}
		System.out.println("message="+message);
		return message;
	}
}