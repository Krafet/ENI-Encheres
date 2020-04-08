package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.JdbcTools;

public class EnchereDAOJbdcImpl implements EnchereDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM Encheres";
	private static final String SELECT_BY_ID = "SELECT * FROM Encheres WHERE no_utilisateur=? AND no_article=?";
	private static final String UPDATE = "UPDATE Encheres SET date_enchere=?, montant_enchere=? WHERE no_utilisateur=? AND no_article=?";
	private static final String INSERT = "INSERT INTO Encheres(montant_enchere, date_enchere, no_utilisateur, no_article) VALUES (?, ?, ?, ?)";
	private static final String DELETE = "DELETE from Encheres WHERE no_utilisateur=? AND no_article=?";

	@Override
	public boolean insert(Enchere uneEnchere) throws BusinessException {
		
		int nbLignesSuppr = 0;
		

			try 
			{
				if(uneEnchere==null)
				{
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
					throw businessException;
				}
			} 
			catch (Exception e) 
			{
				// TODO En effet
				e.printStackTrace();
			}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement stm = cnx.prepareStatement(INSERT);
			stm.setInt(1, uneEnchere.getMontantEnchere());
			stm.setDate(2, (java.sql.Date) uneEnchere.getDateEnchere());
			stm.setInt(3, uneEnchere.getUnUtilisateur().getNoUtilisateur());
			stm.setInt(4, uneEnchere.getUnArticleVendu().getNoArticle());
			
			nbLignesSuppr = stm.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);

			throw businessException;
		}
		
		return nbLignesSuppr > 0;
	}

	@Override
	public Enchere selectById(int idUtilsateur, int idArticle) {
		Enchere uneEnchere= null;

        try (Connection cnx = JdbcTools.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(SELECT_BY_ID);
            requete.setInt(1, idUtilsateur);
            requete.setInt(2, idArticle);
            ResultSet rs = requete.executeQuery();

            if (rs.next()) {
            	uneEnchere = enchereBuilder(rs);
            }
        } catch (Exception e) {


        }
        return uneEnchere;
	}

	@Override
	public ArrayList<Enchere> selectAll() {
		 ArrayList<Enchere> listeEncheres = new ArrayList<>();
		 try (Connection cnx = JdbcTools.getConnection()) {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();
				Enchere uneEnchere = new Enchere();
				while (rs.next()) {
					uneEnchere = enchereBuilder(rs);
					listeEncheres.add(uneEnchere);

				}
			} 
		 	catch (Exception e) {

			}
			return listeEncheres;
	}

	@Override
	public boolean delete(int idUtilisateur, int idArticle) {
		int nbLignesSuppr = 0;

		try (Connection cnx = JdbcTools.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, idUtilisateur);
			pstmt.setInt(2, idArticle);
			nbLignesSuppr = pstmt.executeUpdate();

		} catch (Exception e) {

		}
		return nbLignesSuppr > 0;
	}

	@Override
	public boolean update(Enchere uneEnchere) {
		int nbLignesSuppr = 0;

		 try (Connection cnx = JdbcTools.getConnection()) 
		 {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setInt(1, uneEnchere.getMontantEnchere());
			pstmt.setDate(2, (java.sql.Date) uneEnchere.getDateEnchere());
			
			pstmt.executeUpdate();
			
			nbLignesSuppr = pstmt.executeUpdate();
		} 
		catch (Exception e) 
		{

		}
		return nbLignesSuppr > 0;
	}

	public Enchere enchereBuilder(ResultSet rs) throws SQLException, BusinessException {

		Enchere uneEnchere = new Enchere();
		
		uneEnchere.setMontantEnchere(rs.getInt("montant_enchere"));
		uneEnchere.setDateEnchere(rs.getDate("date_enchere"));

		return uneEnchere;

	}
}
