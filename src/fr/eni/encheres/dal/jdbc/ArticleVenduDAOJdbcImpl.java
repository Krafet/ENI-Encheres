package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.JdbcTools;
import fr.eni.encheres.dal.UtilisateurDao;

//public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
public class ArticleVenduDAOJdbcImpl implements DAO<ArticleVendu> {

	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,"
			+ "prix_initial,prix_vente,no_utilisateur,no_categorie) " + "values (?,?,?,?,?,?,?,?)";
	private static final String DELETE = "DELETE FROM ARTICLES_VENDUS where no_article = ?";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?,description=?, date_debut_encheres=?,date_fin_encheres=?,"
			+ "prix_initial=?,prix_vente=?,no_utilisateur=?,no_categorie=? WHERE no_article=?)";
	private static final String SELECT = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";

	@Override
	public ArticleVendu insert(ArticleVendu article) throws BusinessException {

		
		if(article==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		// try (Connection cnx = JdbcTools.getConnection()) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, (Date) (article.getDateDebutEncheres()));
			pstmt.setDate(4, (Date) article.getDateFinEncheres());
			pstmt.setInt(5, article.getPrixVente());
			pstmt.setInt(6, article.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(7, article.getCategorie().getNoCategorie());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);

			throw businessException;

		}
		return article;
	}

	@Override
	public boolean delete(int id) throws BusinessException {

		int nbLignesModifiees = 0;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			nbLignesModifiees = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE_ERREUR);
			throw businessException;
		}
		return nbLignesModifiees > 0;

	}

	@Override
	public boolean update(ArticleVendu article) throws BusinessException {
		int nbLignesModifiees = 0;

		// try (Connection cnx = JdbcTools.getConnection()) {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, (Date) (article.getDateDebutEncheres()));
			pstmt.setDate(4, (Date) article.getDateFinEncheres());
			pstmt.setInt(5, article.getPrixVente());
			pstmt.setInt(6, article.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(7, article.getCategorie().getNoCategorie());
			pstmt.setInt(8, article.getNoArticle());
			pstmt.executeUpdate();
			nbLignesModifiees = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.MODIFICATION_ARTICLE_ERREUR);
			throw businessException;
		}
		return nbLignesModifiees > 0;

	}

	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		 List<ArticleVendu> listeArticles = new ArrayList<>();
		 //try (Connection cnx = JdbcTools.getConnection()) {
		 try (Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();
				ArticleVendu article = new ArticleVendu();
				while (rs.next()) {
					article = articleBuilder(rs);
					listeArticles.add(article);

				}
			} catch (Exception e) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.ERREUR_RECUPERATION_ARTICLES_VENDUS);
				throw businessException;

			}
			return listeArticles;
	}

	@Override
	public ArticleVendu selectById(int noArticle) throws BusinessException {

		ArticleVendu article = null;

        try (Connection cnx = JdbcTools.getConnection()) {
		//try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(SELECT);
            requete.setInt(1, noArticle);
            ResultSet rs = requete.executeQuery();

            if (rs.next()) {
            	article = articleBuilder(rs);
            }
        } catch (Exception e) {
        	e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECTION_ARTICLE_ERREUR);
			throw businessException;
        }
        return article;
	}

	/**
	 * Création d'un article vendu
	 * 
	 * @param rs
	 * @return ArticleVendu
	 * @throws SQLException
	 */
	public ArticleVendu articleBuilder(ResultSet rs) throws SQLException {

		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setNoArticle(rs.getInt("id"));
		articleVendu.setNomArticle(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
		articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));
		articleVendu.setUtilisateur(this.getUserArticle(rs.getInt("no_utilisateur")));
		articleVendu.setCategorie(this.getCategoryArticle(rs.getInt("no_categorie")));

		return articleVendu;

	}

	/***
	 * Fonction interne pour récupérer l'utilisateur de l'article
	 * 
	 * @param userId Id de l'utilisateur qu'on retrouve en base associée à l'article vendu
	 * @return Utilisateur
	 */
	private Utilisateur getUserArticle(int userId) {

		Utilisateur utilisateurArticle = null;
		UtilisateurDao utilisateurDao = DAOFactory.getUtilisateurDAO();
		//On récupère l'utilisateur à partir de son id
		utilisateurArticle = utilisateurDao.getUtilisateurById(userId);

		return utilisateurArticle;
	}

	/***
	 * Fonction interne pour récupérer la catégorie de l'article
	 * 
	 * @param categoryId Id de la catégorie qu'on retrouve en base associée à    l'article vendu
	 * @return Categorie
	 */
	private Categorie getCategoryArticle(int categoryId) {

		Categorie categoryArticle = null;
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		//On récupère la catégorie à partir de son id
		//categoryArticle = categorieDAO.selectById(categoryId);

		return categoryArticle;
	}

}
