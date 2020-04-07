package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 * Classe en charge de gérer la connexion à la base de données
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public abstract class ConnectionProvider {
	
	private static DataSource dataSource;
	
	static
	{
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible d'accéder à la base de données");
		}
	}
	
	/**
	 * 
	 * Méthode en charge de retourner une connection opérationnelle issue du pool de connexion
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException
	{
		return ConnectionProvider.dataSource.getConnection();
	}
}
