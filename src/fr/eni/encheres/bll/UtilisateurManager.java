/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

/**
 * Classe en charge de
 * @author Oguzhan
 * @version ENI-Encheres - v1.0
 * @date 9 avr. 2020
 */
public class UtilisateurManager 
{
	private static UtilisateurManager instance;
	private UtilisateurDAO utilisateurDAO;
	
	/**
	 * Constructeur
	 */
	private UtilisateurManager()
	{
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	/**
	 * Méthode en charge de récuperer l'instance
	 * @return UtilisateurManager
	 */
	public static UtilisateurManager getInstance()
	{
		if(instance == null)
		{
			return new UtilisateurManager();
		}
		return instance;
	}
	
	public List<Utilisateur> getAllUtilisateurs() throws BusinessException
	{
		List<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
		try
		{
			lesUtilisateurs = utilisateurDAO.getAllUtilisateur();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEURS);

			throw businessException;	
		}
		
		return lesUtilisateurs;		
	}
	
	public Utilisateur getUtilisateurById(int id) throws BusinessException
	{
		Utilisateur unUtilisateur = new Utilisateur();
		try
		{
			unUtilisateur = utilisateurDAO.getUtilisateurById(id);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEURS);

			throw businessException;	
		}
		return unUtilisateur;		
	}
	
	public Utilisateur getUtilisateurByPseudoPassword(String pseudo, String MotDePasse) throws BusinessException
	{
		Utilisateur unUtilisateur = new Utilisateur();
		try
		{
			unUtilisateur = utilisateurDAO.getUtilisateurByPseudoPassword(pseudo, MotDePasse);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEURS);

			throw businessException;	
		}
		return unUtilisateur;
	}
	
	public Utilisateur addUtilisateur(Utilisateur unUtilisateur) throws BusinessException
	{
		if(unUtilisateur.getPseudo().trim().equals("") 
				|| unUtilisateur.getPrenom().trim().equals("") 
				|| unUtilisateur.getNom().trim().equals("") 
				|| unUtilisateur.getEmail().trim().equals("") 
				|| unUtilisateur.getRue().trim().equals("") 
				|| unUtilisateur.getCode_postal().trim().equals("") 
				|| unUtilisateur.getVille().trim().equals("") 
				|| unUtilisateur.getMot_de_passe().trim().equals(""))
		{
			//todo
		}
		
		//check si l'utilisateur existe todo
		
		try
		{
			utilisateurDAO.insert(unUtilisateur);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEURS);

			throw businessException;	
		}
		return unUtilisateur;
	}
	
	public boolean deleteUtilisateur(Utilisateur unUtilisateur) throws BusinessException
	{
		try
		{
			utilisateurDAO.delete(unUtilisateur);
			return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEURS);

			throw businessException;			
		}
	}
	
	public boolean updateUtilisateur(Utilisateur unUtilisateur) throws BusinessException
	{
		if(unUtilisateur.getPseudo().trim().equals("") 
				|| unUtilisateur.getPrenom().trim().equals("") 
				|| unUtilisateur.getNom().trim().equals("") 
				|| unUtilisateur.getEmail().trim().equals("") 
				|| unUtilisateur.getRue().trim().equals("") 
				|| unUtilisateur.getCode_postal().trim().equals("") 
				|| unUtilisateur.getVille().trim().equals("") 
				|| unUtilisateur.getMot_de_passe().trim().equals(""))
		{
			//todo
		}
		
		try
		{
			utilisateurDAO.update(unUtilisateur);
			return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEURS);

			throw businessException;			
		}
	}
}
