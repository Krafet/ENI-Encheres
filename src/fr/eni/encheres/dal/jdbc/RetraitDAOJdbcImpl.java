package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	

	private static final String SELECT_ALL = "SELECT rue, code_postal, ville  FROM Retraits";
	private static final String UPDATE = "UPDATE Retraits SET rue=?, code_postal=?, ville=? WHERE no_article=?";
	private static final String INSERT = "INSERT INTO Retraits(no_article, rue, code_postal, ville values(?, ?, ?, ?)";
	private static final String DELETE = "DELETE from Retraits WHERE no_article=?";
	
	// Constructor
	public RetraitDAOJdbcImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.RetraitDAO#insert(fr.eni.encheres.bo.ArticleVendu)
	 */
	@Override
	public Retrait insert(ArticleVendu articleVendu) throws BusinessException {

		
		if(articleVendu == null || articleVendu.getRetrait() == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, articleVendu.getNoArticle());
			pstmt.setString(2, articleVendu.getRetrait().getRue());
			pstmt.setString(3, articleVendu.getRetrait().getCodePostal());
			pstmt.setString(4, articleVendu.getRetrait().getVille());
			
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
	
		}
		return articleVendu.getRetrait();
		
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.RetraitDAO#update(fr.eni.encheres.bo.ArticleVendu)
	 */
	@Override
	public boolean update(ArticleVendu articleVendu) throws BusinessException {
			
		int i  = 0;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			
			pstmt.setString(1, articleVendu.getRetrait().getRue());
			pstmt.setString(2, articleVendu.getRetrait().getCodePostal());
			pstmt.setString(3, articleVendu.getRetrait().getVille());
						
			pstmt.setInt(4, articleVendu.getNoArticle());
			
			i = pstmt.executeUpdate();

			
		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.MODIFICATION_RETRAIT_ERREUR);
			throw businessException;
		}
		
		return i > 0;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.RetraitDAO#delete(fr.eni.encheres.bo.ArticleVendu)
	 */
	@Override
	public boolean delete(ArticleVendu retrait) throws BusinessException {
			int i = 0;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, retrait.getNoArticle());
			
			i = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_RETRAIT_ERREUR);
			throw businessException;
		}
				
			return i > 0;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.RetraitDAO#selectAll()
	 */
	@Override
	public List<Retrait> selectAll() throws BusinessException {
		
		List<Retrait> listeRetrait = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeRetrait.add(retraitBuilder(rs));
			}
		}
		catch (Exception e)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECTION_RETRAIT_ERREUR);
			throw businessException;		
		}
		return listeRetrait;
	}
	
	
	private Retrait retraitBuilder(ResultSet rs) throws SQLException{
		
		Retrait retrait = new Retrait();
		retrait.setRue(rs.getString(1));
		retrait.setCodePostal(rs.getString(2));
		retrait.setVille(rs.getString(3));
		
		return retrait;
		
	}

}
