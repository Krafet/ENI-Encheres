/**
 * 
 */
package fr.eni.encheres.dal.jdbc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.utils.Utils;

/**
 * Classe en charge de tester les méthodes de la dal concernant les utilisateurs
 * @author Oguzhan
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class UtilisateurDAOJdbcImplTest {

	UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	
	List<ArticleVendu> lesArticlesVendus = null;
	
	
	/**
	 * Méthode en charge de réinitialiser la base avant le lancement des tests
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		//Script qui réinitialise base et insère un jeu de test
		try {
			Utils.executeQuery("db/reset.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Méthode en charge de réinitialiser la base après le lancement de tous les tests de la classe
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

		// On reset pour obtenir de nouveau le jeu d'essai de base de notre application
		try {
			Utils.executeQuery("db/jeu_essai_application.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl#getAllUtilisateur()}.
	 * @throws BusinessException 
	 */
	@Test
	public void testGetAllUtilisateur() throws BusinessException {
		
		
		Utilisateur test1 = new Utilisateur("test1", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
		Utilisateur test2 = new Utilisateur("test2", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
		Utilisateur test3 = new Utilisateur("test3", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
		Utilisateur test4 = new Utilisateur("test4", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
		
		Utilisateur utiTest1 = utilisateurDAO.insert(test1);
		Utilisateur utiTest2 = utilisateurDAO.insert(test2);
		Utilisateur utiTest3 = utilisateurDAO.insert(test3);
		Utilisateur utiTest4 = utilisateurDAO.insert(test4);
		
		List<Utilisateur> utilisateurssAttendus = new ArrayList<>();
		utilisateurssAttendus.add(utiTest1);
		utilisateurssAttendus.add(utiTest2);
		utilisateurssAttendus.add(utiTest3);
		utilisateurssAttendus.add(utiTest4);
        
        assertEquals(utilisateurssAttendus, utilisateurDAO.getAllUtilisateur());
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl#getUtilisateurById(int)}.
	 * @throws BusinessException 
	 */
	@Test
	public void testGetUtilisateurById() throws BusinessException {
		
		//Conducteur qu'on veut pouvoir récupérer grâce à cette méthode
		Utilisateur unUtilisateurAttendu = new Utilisateur("Ogu", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
        //On l'insère
		Utilisateur recuperationInsertion = utilisateurDAO.insert(unUtilisateurAttendu);
        //On le récupère
		Utilisateur unUtilisateurRecupere = utilisateurDAO.getUtilisateurById(recuperationInsertion.getNoUtilisateur());

        assertEquals(unUtilisateurAttendu, unUtilisateurRecupere);
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl#getUtilisateurByPseudoPassword(java.lang.String, java.lang.String)}.
	 * @throws BusinessException 
	 */
	@Test
	public void testGetUtilisateurByPseudoPassword() throws BusinessException {

		//Conducteur qu'on veut pouvoir récupérer grâce à cette méthode
		Utilisateur unUtilisateurAttendu = new Utilisateur("Ogu", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
		//On l'insère
		Utilisateur recuperationInsertion = utilisateurDAO.insert(unUtilisateurAttendu);
		//On le récupère
		Utilisateur unUtilisateurRecupere = utilisateurDAO.getUtilisateurByLoginPassword("Ogu", "root");

		assertEquals(unUtilisateurAttendu, unUtilisateurRecupere);
		
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl#insert(fr.eni.encheres.bo.Utilisateur)}.
	 * @throws BusinessException 
	 */
	@Test
	public void testInsert() throws BusinessException {
		List<ArticleVendu> lesArticlesVendus = null;
		Utilisateur unUtilisateurAttendu = new Utilisateur("Ogu", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
		
		Utilisateur recuperationUtilisateur = utilisateurDAO.insert(unUtilisateurAttendu);
		Utilisateur utilisateurRecupere = utilisateurDAO.getUtilisateurById(unUtilisateurAttendu.getNoUtilisateur());
		
		assertEquals(unUtilisateurAttendu, utilisateurRecupere);
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl#delete(fr.eni.encheres.bo.Utilisateur)}.
	 * @throws BusinessException 
	 */
	@Test
	public void testDelete() throws BusinessException {
		//Taille initiale
        int sizeBefore = utilisateurDAO.getAllUtilisateur().size();

        //Ajout d'un utilisateur et récupération de son id
        Utilisateur unUtilisateurAttendu = new Utilisateur("Ogu", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
        Utilisateur recuperationInsertion = utilisateurDAO.insert(unUtilisateurAttendu);

        //Suppression d'un utilisateur qu'on vient d'ajouter
        utilisateurDAO.delete(recuperationInsertion);

        //La taille doit être identique à celle d'avant puisqu'on supprime celui qu'on vient d'ajouter
        int sizeAfter = utilisateurDAO.getAllUtilisateur().size();

        assertEquals(sizeAfter, sizeBefore);
	}

	/**
	 * Test method for {@link fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl#update(fr.eni.encheres.bo.Utilisateur)}.
	 * @throws BusinessException 
	 */
	@Test
	public void testUpdate() throws BusinessException {
		//On insère un conducteur qu'on souhaite ensuite mettre à jour
		Utilisateur unUtilisateurAUpdater = new Utilisateur("Ogu", "CANBOLAT", "Oguzhan", "oguzhan.cblt@gmail.com", "0783161111", "8 allée de croatie", "35200","Rennes", "root", 100, false, lesArticlesVendus);
		Utilisateur recuperationInsertion = utilisateurDAO.insert(unUtilisateurAUpdater);

        //On change certaines de ses propriétés
        recuperationInsertion.setNom("MORENO");
        recuperationInsertion.setPrenom("Jules");

        //On le met à jour en base
        utilisateurDAO.update(recuperationInsertion);

        //On le récupère pour le comparer
        Utilisateur conducteurMisAJour= utilisateurDAO.getUtilisateurById(recuperationInsertion.getNoUtilisateur());

        assertEquals(recuperationInsertion, conducteurMisAJour);
	}

}
