package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;

public class CategorieDAOJdbcimpl implements CategorieDAO {

	
	private static final String SELECT_ALL = "SELECT * FROM Categories";
	private static final String UPDATE = "UPDATE Categories SET libelle=?, WHERE no_categorie=?";
	private static final String INSERT = "INSERT INTO Categories(libelle) values(?)";
	private static final String DELETE = "DELETE from Categories WHERE no_categorie=?";


	
	// Constructor
	public CategorieDAOJdbcimpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	

	@Override
	public Categorie insert(Categorie categorie) {
		
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, categorie.getLibelle());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				categorie.setNoCategorie(rs.getInt(1));
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		

		}
		return categorie;
	}

	@Override
	public boolean update(Categorie categorie) {
		int i  = 0;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, categorie.getLibelle());
			pstmt.setInt(2, categorie.getNoCategorie());

			
			i = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		

		}
		
		return i > 0;
	}

	@Override
	public boolean delete(Categorie categorie) {
		
		int i = 0;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, categorie.getNoCategorie());
			
			i = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
			return i > 0;
	}

	@Override
	public List<Categorie> selectAll() {

		List<Categorie> listeCategorie = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeCategorie.add(categorieBuilder(rs));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return listeCategorie;
	}
	
	
	private Categorie categorieBuilder(ResultSet rs) throws SQLException{
		
		Categorie categorie = new Categorie();
		categorie.setNoCategorie(rs.getInt(1));
		categorie.setLibelle(rs.getString(2));
		
		return categorie;
		
	}

}
