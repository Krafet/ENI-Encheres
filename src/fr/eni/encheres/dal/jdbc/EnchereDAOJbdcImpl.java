package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.JdbcTools;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.utils.Utils;

/**
 * 
 * Classe en charge d'effectuer les requêtes vers la table ENCHERES
 * @author Camille
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 8 avr. 2020
 */
public class EnchereDAOJbdcImpl implements EnchereDAO {

	private static final String SELECT_ALL = "SELECT * FROM Encheres";
	private static final String SELECT_BY_ID = "SELECT * FROM Encheres WHERE no_utilisateur=? AND no_article=?";
	private static final String UPDATE = "UPDATE Encheres SET date_enchere=?, montant_enchere=? WHERE no_utilisateur=? AND no_article=?";
	private static final String INSERT = "INSERT INTO Encheres(montant_enchere, date_enchere, no_utilisateur, no_article) VALUES (?, ?, ?, ?)";
	private static final String DELETE = "DELETE from Encheres WHERE no_utilisateur=? AND no_article=?";
	private static final String DELETE_BY_USER_OR_ARTICLE = "DELETE ENCHERES WHERE no_utilisateur=? OR no_article=?";


	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.EnchereDAO#insert(fr.eni.encheres.bo.Enchere)
	 */
	public Enchere insert(Enchere uneEnchere) throws BusinessException {

		try {
			if (uneEnchere == null) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
				throw businessException;
			}
		} catch (Exception e) {
			// TODO En effet
			e.printStackTrace();
		}

		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setInt(1, uneEnchere.getMontantEnchere());
			
			java.util.Date date_util =  uneEnchere.getDateEnchere();
			java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
			stm.setDate(2, date_sql);
			
			stm.setInt(3, uneEnchere.getUnUtilisateur().getNoUtilisateur());
			stm.setInt(4, uneEnchere.getUnArticleVendu().getNoArticle());

			stm.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);

			throw businessException;
		}

		return uneEnchere;
	}

	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.EnchereDAO#selectById(int, int)
	 */
	public Enchere selectById(int idUtilsateur, int idArticle) throws BusinessException {

		Enchere uneEnchere = null;
		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, idUtilsateur);
			pstmt.setInt(2, idArticle);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				uneEnchere = enchereBuilder(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECTION_ENCHERE_ERREUR);
			throw businessException;
		}

		return uneEnchere;
	}

	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.EnchereDAO#selectAll()
	 */
	public ArrayList<Enchere> selectAll() throws BusinessException {
		ArrayList<Enchere> listeEncheres = new ArrayList<>();
		try (Connection cnx = Utils.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			Enchere uneEnchere = new Enchere();
			while (rs.next()) {
				uneEnchere = enchereBuilder(rs);
				listeEncheres.add(uneEnchere);

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_DES_ENCHERES);
			throw businessException;
		}
		return listeEncheres;
	}

	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.EnchereDAO#delete(int, int)
	 */
	public boolean delete(int idUtilisateur, int idArticle) throws BusinessException {
		int nbLignesSuppr = 0;

		try (Connection cnx = Utils.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, idUtilisateur);
			pstmt.setInt(2, idArticle);
			nbLignesSuppr = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_DES_ENCHERES);
			throw businessException;
		}
		return nbLignesSuppr > 0;
	}
	
	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.EnchereDAO#delete(int, int)
	 */
	public boolean deleteByUserOrArticle(int idUtilisateur, int idArticle) throws BusinessException {
		int nbLignesSuppr = 0;

		try (Connection cnx = Utils.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE_BY_USER_OR_ARTICLE);
			pstmt.setInt(1, idUtilisateur);
			pstmt.setInt(2, idArticle);
			nbLignesSuppr = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_SUPPRESSION_ENCHERES);
			throw businessException;
		}
		return nbLignesSuppr > 0;
	}


	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.EnchereDAO#update(fr.eni.encheres.bo.Enchere)
	 */
	public boolean update(Enchere uneEnchere) throws BusinessException {
		int nbLignesSuppr = 0;

		try (Connection cnx = Utils.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			java.util.Date date_util =  uneEnchere.getDateEnchere();
			java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
			pstmt.setDate(1, date_sql);
			pstmt.setInt(2, uneEnchere.getMontantEnchere());		
			pstmt.setInt(3, uneEnchere.getUnUtilisateur().getNoUtilisateur());
			pstmt.setInt(4, uneEnchere.getUnArticleVendu().getNoArticle());

			pstmt.executeUpdate();

			nbLignesSuppr = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.MODIFICATION_ENCHERE_ERREUR);
			throw businessException;
		}
		return nbLignesSuppr > 0;
	}

	/**
	 * 
	 * Méthode en charge de construire une enchère
	 * @param rs
	 * @return Enchere
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public Enchere enchereBuilder(ResultSet rs) throws SQLException, BusinessException {

		ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		Enchere uneEnchere = new Enchere();

		uneEnchere.setMontantEnchere(rs.getInt("montant_enchere"));
		uneEnchere.setDateEnchere(rs.getDate("date_enchere"));
		uneEnchere.setUnArticleVendu(articleDAO.selectById(rs.getInt("no_article")));
		uneEnchere.setUnUtilisateur(utilisateurDAO.getUtilisateurById(rs.getInt("no_utilisateur")));

		return uneEnchere;

	}
}
