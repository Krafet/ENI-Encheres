package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.JdbcTools;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.utils.Utils;

/**
 * Classe en charge de
 * @author Oguzhan
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static String RQT_SELECTALL = "SELECT * FROM UTILISATEURS;";
	private static String RQT_SELECTBYID = "SELECT * FROM UTILISATEURS U WHERE U.no_utilisateur = ?;";
	private static String RQT_SELECTBYLOGINPASSWORD = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email=?) AND mot_de_passe = ?;";
	private static String RQT_INSERT = "INSERT INTO UTILISATEURS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static String RQT_UPDATE = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?;";
	private static String RQT_DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static String RQT_PSEUDOEXISTANT = "SELECT * FROM UTILISATEURS WHERE pseudo = ? AND no_utilisateur <> ?";
	private static String RQT_EMAILEXISTANT = "SELECT * FROM UTILISATEURS WHERE email = ?  AND no_utilisateur <> ?";
	
	private static String RQT_SELECTALL_WITH_ARTICLES = "SELECT * FROM UTILISATEURS U JOIN ARTICLES_VENDUS A ON U.no_utilisateur = A.no_utilisateur;";
	private static String RQT_SELECTBYID_WITH_ARTICLES = "SELECT * FROM UTILISATEURS U JOIN ARTICLES_VENDUS A ON U.no_utilisateur = A.no_utilisateur WHERE A.no_utilisateur = ?;";
	private static String RQT_SELECTBYLOGINPASSWORD_WITH_ARTICLES = "SELECT * FROM UTILISATEURS U JOIN ARTICLES_VENDUS A ON U.no_utilisateur = A.no_utilisateur  WHERE (pseudo = ? OR email=?) AND mot_de_passe = ?;";
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#getAllUtilisateur()
	 */
	@Override
	public List<Utilisateur> getAllUtilisateur() throws BusinessException {
		List<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
		
		try (Connection cnx = Utils.getConnection()) 
		{			
			PreparedStatement stm = cnx.prepareStatement(RQT_SELECTALL);
            ResultSet rs = stm.executeQuery();

            while(rs.next())
            {
                Utilisateur item = itemBuilder(rs);

                lesUtilisateurs.add(item);
            }
		}
		catch(Exception e){
			
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEURS);

			throw businessException;
		}
		
		return lesUtilisateurs;
	}
	
	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#getUtilisateurById(int)
	 */
	public Utilisateur getUtilisateurById(int id) throws BusinessException
	{
		Utilisateur unUtilisateur = new Utilisateur();
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_SELECTBYID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next())
            {
            	unUtilisateur = itemBuilder(rs);
            }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEUR);

			throw businessException;
						
		}
		
		if(unUtilisateur.getNoUtilisateur()==0)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UTILISATEUR_INEXISTANT);
			throw businessException;
		}
		
		return unUtilisateur;
	}
	
	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#getUtilisateurByIdWithArticles(int)
	 */
	public Utilisateur getUtilisateurByIdWithArticles(int id) throws BusinessException
	{
		Utilisateur unUtilisateur = new Utilisateur();
		ArticleVendu article = new ArticleVendu();
		List<ArticleVendu> ventes = new ArrayList<>();
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_SELECTBYID_WITH_ARTICLES);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next())
            {
            	unUtilisateur = itemBuilder(rs);
            	ventes.add(articleBuilder(rs));
            }
            unUtilisateur.setVente(ventes);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEUR);

			throw businessException;
						
		}
		
		if(unUtilisateur.getNoUtilisateur()==0)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UTILISATEUR_INEXISTANT);
			throw businessException;
		}
		
		return unUtilisateur;
	}
	
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#getUtilisateurByPseudoPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Utilisateur getUtilisateurByLoginPassword(String login, String motDePasse) throws BusinessException 
	{
		Utilisateur unUtilisateur = new Utilisateur();
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_SELECTBYLOGINPASSWORD);
            stm.setString(1, login);
            stm.setString(2, login);
            stm.setString(3, motDePasse);
            ResultSet rs = stm.executeQuery();

            while(rs.next())
            {
            	unUtilisateur = getUtilisateurById(rs.getInt("no_utilisateur"));
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEUR);

			throw businessException;
		}
		
		//Si aucun utilisateur n'a été trouvé
		if(unUtilisateur.getNoUtilisateur()==0)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_IDENTIFIANTS);
			throw businessException;
		}
		
		
		return unUtilisateur;
	}
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#insert(fr.eni.encheres.bo.Utilisateur)
	 */
	@Override
	public Utilisateur insert(Utilisateur unUtilisateur) throws BusinessException 
	{
		if(unUtilisateur==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, unUtilisateur.getPseudo());
			stm.setString(2, unUtilisateur.getNom());
			stm.setString(3, unUtilisateur.getPrenom());
			stm.setString(4, unUtilisateur.getEmail());
			stm.setString(5, unUtilisateur.getTelephone());
			stm.setString(6, unUtilisateur.getRue());
			stm.setString(7, unUtilisateur.getCodePostal());
			stm.setString(8, unUtilisateur.getVille());
			stm.setString(9, unUtilisateur.getMotDePasse());
			stm.setInt(10, unUtilisateur.getCredit());
			stm.setBoolean(11, unUtilisateur.isAdministrateur());
			
			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			
			if (rs.next()) {
				unUtilisateur.setNoUtilisateur(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);

			throw businessException;
		}
		
		return unUtilisateur;
	
	}
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#delete(fr.eni.encheres.bo.Utilisateur)
	 */
	@Override
	public boolean delete(Utilisateur unUtilisateur) throws BusinessException
	{
		int nbLignesModifiees = 0;
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_DELETE);
            stm.setInt(1, unUtilisateur.getNoUtilisateur());

            stm.executeUpdate();
            
            nbLignesModifiees = stm.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_UTILISATEUR);
			throw businessException;
		}
		
		//Si aucun utilisateur n'a été trouvé
		if(unUtilisateur.getNoUtilisateur()==0)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UTILISATEUR_INEXISTANT);
			throw businessException;
		}
		
		return nbLignesModifiees > 0;

	}
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#update(fr.eni.encheres.bo.Utilisateur)
	 */
	@Override
	public boolean update(Utilisateur unUtilisateur) throws BusinessException 
	{
		int nbLignesModifiees = 0;
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_UPDATE);
			stm.setString(1, unUtilisateur.getPseudo());
			stm.setString(2, unUtilisateur.getNom());
			stm.setString(3, unUtilisateur.getPrenom());
			stm.setString(4, unUtilisateur.getEmail());
			stm.setString(5, unUtilisateur.getTelephone());
			stm.setString(6, unUtilisateur.getRue());
			stm.setString(7, unUtilisateur.getCodePostal());
			stm.setString(8, unUtilisateur.getVille());
			stm.setString(9, unUtilisateur.getMotDePasse());
			stm.setInt(10, unUtilisateur.getCredit());
			stm.setBoolean(11, unUtilisateur.isAdministrateur());
			stm.setInt(12, unUtilisateur.getNoUtilisateur());
			
			stm.executeUpdate();
			
			nbLignesModifiees = stm.executeUpdate();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_MODIFICATION_UTILISATEUR);
			throw businessException;
		}
		return nbLignesModifiees > 0;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws BusinessException 
	 * @see fr.eni.encheres.dal.UtilisateurDAO#pseudoExistant(java.lang.String)
	 */
	@Override
	/*public void pseudoExistant(String pseudo) throws BusinessException 
	{
		Utilisateur unUtilisateur = null;
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_PSEUDOEXISTANT);
            stm.setString(1, pseudo);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next())
            {
            	unUtilisateur = itemBuilder(rs);
            }
            
            if(unUtilisateur != null)
    		{
    			BusinessException businessException = new BusinessException();
    			businessException.ajouterErreur(CodesResultatDAL.PSEUDO_EXISTANT);

    			throw businessException;
    		}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UTILISATEUR_INEXISTANT);

			throw businessException;						
		}		
	}*/
	
	public boolean pseudoExistant(String pseudo, int id) throws BusinessException 
	{
		Utilisateur unUtilisateur = null;
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_PSEUDOEXISTANT);
            stm.setString(1, pseudo);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next())
            {
            	unUtilisateur = itemBuilder(rs);
            }
            
            if(unUtilisateur != null)
    		{
    			return true;
    		}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UTILISATEUR_INEXISTANT);

			throw businessException;						
		}	
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @throws BusinessException 
	 * @see fr.eni.encheres.dal.UtilisateurDAO#emailExistant(java.lang.String)
	 */
	@Override
	/*public void emailExistant(String email) throws BusinessException 
	{
		Utilisateur unUtilisateur = null;
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_EMAILEXISTANT);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next())
            {
            	unUtilisateur = itemBuilder(rs);
            }
            
            if(unUtilisateur != null)
    		{
    			BusinessException businessException = new BusinessException();
    			businessException.ajouterErreur(CodesResultatDAL.EMAIL_EXISTANT);

    			throw businessException;
    		}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UTILISATEUR_INEXISTANT);

			throw businessException;						
		}	
		
	}*/
	public boolean emailExistant(String email, int id) throws BusinessException 
	{
		Utilisateur unUtilisateur = null;
		
		try (Connection cnx = Utils.getConnection()) 
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_EMAILEXISTANT);
            stm.setString(1, email);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next())
            {
            	unUtilisateur = itemBuilder(rs);
            }
            
            if(unUtilisateur != null)
    		{
    			return true;
    		}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_UTILISATEUR);

			throw businessException;						
		}	
		return false;
		
	}
	/**
	 * Méthode en charge de construire un utilisateur
	 * @param rs
	 * @return Utilisateur
	 * @throws SQLException
	 */
	public Utilisateur itemBuilder(ResultSet rs) throws SQLException{
		
		Utilisateur resultat = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
				rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
				rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
				rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), 
				rs.getBoolean("administrateur"));
		
		return resultat;
	}

	/**
	 * 
	 * Méthode en charge de construire un objet ArticleVendu
	 * @param rs
	 * @return ArticleVendu
	 * @throws SQLException
	 * @throws BusinessException 
	 */
	public ArticleVendu articleBuilder(ResultSet rs) throws SQLException, BusinessException {

		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setNoArticle(rs.getInt("no_article"));
		articleVendu.setNomArticle(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
		articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));
		articleVendu.setMiseAPrix(rs.getInt("prix_initial"));

		return articleVendu;

	}
	
}
