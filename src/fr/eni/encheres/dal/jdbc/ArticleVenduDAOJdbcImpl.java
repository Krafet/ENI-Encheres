package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,"
			+ "prix_initial,prix_vente,no_utilisateur,no_categorie) " + "values (?,?,?,?,?,?,?,?)";
	private static final String DELETE = "DELETE FROM ARTICLES_VENDUS where no_article = ?";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?,description=?, date_debut_encheres=?,date_fin_encheres=?,"
			+ "prix_initial=?,prix_vente=?,no_utilisateur=?,no_categorie=? WHERE no_article=?)";
	private static final String SELECT = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	@Override
	public ArticleVendu insert(ArticleVendu article) throws BusinessException {

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
			// businessException.ajouterErreur(CodesResultatDAL.ERREUR_INSERTION_ARTICLE);

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
			// businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
			throw businessException;
		}
		return nbLignesModifiees > 0;

	}

	@Override
	public boolean update(ArticleVendu article) throws BusinessException {
		int nbLignesModifiees = 0;

		//try (Connection cnx = JdbcTools.getConnection()) {
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
			// businessException.ajouterErreur(CodesResultatDAL.MODIFICATION_LISTE_ERREUR);
			throw businessException;
		}
		return nbLignesModifiees > 0;

	}

	@Override
	public List<ArticleVendu> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu selectById(int noArticle) {
		
		return null;
	}
	
	/**
	 * Création d'un article vendu
	 * @param rs
	 * @return ArticleVendu
	 * @throws SQLException 
	 */
	public ArticleVendu articleBuilder(ResultSet rs) throws SQLException {
		
		Utilisateur utilisateurArticle = null; //Utiliser la fonction ci-dessous ensuite
		Categorie categorieArticle = null; //Utiliser la fonction ci-dessous ensuite
		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setNoArticle(rs.getInt("id"));
		articleVendu.setNomArticle(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
		articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));
		articleVendu.setUtilisateur(utilisateurArticle);
		articleVendu.setCategorie(categorieArticle);

		return articleVendu;
		
	}
	
	 /***
     * Fonction interne pour récupérer l'utilisateur
     * @param conducteurId Id du conducteur qu'on retrouve en base associée à la voiture
     * @return Conducteur Données de ce conducteur
     */
    private Utilisateur getUserArticle(int userId) {
    	
    	return null;
       /* ConducteurDAO conducteurDAO = DAOFactory.getConducteurDAO();
        Conducteur conducteurVoiture = null;
        try {
            conducteurVoiture = conducteurDAO.selectById(conducteurId);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return conducteurVoiture;*/
    }

}
