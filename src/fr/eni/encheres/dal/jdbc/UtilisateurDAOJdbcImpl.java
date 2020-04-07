package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.UtilisateurDAO;

/**
 * Classe en charge de
 * @author Oguzhan
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static String RQT_SELECTALL = "SELECT * FROM UTILISATEURS;";
	private static String RQT_SELECTBYID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?;";
	private static String RQT_SELECTBYPSEUDOPASSWORD = "SELECT * FROM UTILISATEUR WHERE pseudo = ? AND mot_de_passe = ?;";
	private static String RQT_INSERT = "INSERT INTO UTILISATEUR VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static String RQT_UPDATE = "UPDATE UTILISATEUR SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?;";
	private static String RQT_DELETE = "DELETE FROM UTILISATEUR WHERE no_utilisateur = ?";
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#getAllUtilisateur()
	 */
	@Override
	public List<Utilisateur> getAllUtilisateur() throws BusinessException {
		List<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
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
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#getUtilisateurById(int)
	 */
	@Override
	public Utilisateur getUtilisateurById(int id) throws BusinessException
	{
		Utilisateur unUtilisateur = new Utilisateur();
		
		try(Connection cnx = ConnectionProvider.getConnection())
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
			businessException.ajouterErreur(CodesResultatDAL.SELECTION_UTILISATEUR_ERREUR);

			throw businessException;
						
		}
		
		return unUtilisateur;
	}
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.UtilisateurDAO#getUtilisateurByPseudoPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Utilisateur getUtilisateurByPseudoPassword(String pseudo, String motDePasse) throws BusinessException 
	{
		Utilisateur unUtilisateur = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_SELECTBYPSEUDOPASSWORD);
            stm.setString(1, pseudo);
            stm.setString(2, motDePasse);
            ResultSet rs = stm.executeQuery();

            while(rs.next())
            {
            	unUtilisateur = getUtilisateurById(rs.getInt("id"));
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECTION_UTILISATEUR_ERREUR);

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
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_INSERT);
			stm.setString(1, unUtilisateur.getPseudo());
			stm.setString(2, unUtilisateur.getNom());
			stm.setString(3, unUtilisateur.getPrenom());
			stm.setString(4, unUtilisateur.getEmail());
			stm.setString(5, unUtilisateur.getTelephone());
			stm.setString(6, unUtilisateur.getRue());
			stm.setString(7, unUtilisateur.getCode_postal());
			stm.setString(8, unUtilisateur.getVille());
			stm.setString(9, unUtilisateur.getMot_de_passe());
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
		
		try(Connection cnx = ConnectionProvider.getConnection())
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
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement stm = cnx.prepareStatement(RQT_UPDATE);
			stm.setString(1, unUtilisateur.getPseudo());
			stm.setString(2, unUtilisateur.getNom());
			stm.setString(3, unUtilisateur.getPrenom());
			stm.setString(4, unUtilisateur.getEmail());
			stm.setString(5, unUtilisateur.getTelephone());
			stm.setString(6, unUtilisateur.getRue());
			stm.setString(7, unUtilisateur.getCode_postal());
			stm.setString(8, unUtilisateur.getVille());
			stm.setString(9, unUtilisateur.getMot_de_passe());
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
			businessException.ajouterErreur(CodesResultatDAL.MODIFICATION_UTILISATEUR);
			throw businessException;
		}
		return nbLignesModifiees > 0;
	}
	
	/**
	 * Méthode en charge de
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Utilisateur itemBuilder(ResultSet rs) throws SQLException{
		
		Utilisateur resultat = new Utilisateur(rs.getInt("id"), rs.getString("pseudo"),
				rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
				rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
				rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), 
				rs.getBoolean("administrateur"));
		
		return resultat;
	}
}
