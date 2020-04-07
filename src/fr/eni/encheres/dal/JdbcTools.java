package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Classe en charge de fournir la fonction de connection à la bdd
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class JdbcTools {

	private static String urldb;
	private static String userdb;
	private static String passwordb;
	private static String driverdb;

	/**
	 * 
	 * Méthode en charge de se connecter à la base
	 * @return Objet Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		urldb = "jdbc:sqlserver://localhost\\sqlexpress;databasename=ENCHERES_TEST";
		userdb = "sa";
		passwordb = "Pa$$w0rd";
		driverdb = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

		try {
			// Récupération du driver
			Class.forName(driverdb);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(urldb, userdb, passwordb);
		} catch (Exception e) {
			System.out.println("erreur connexion");
			e.printStackTrace();
		}
		return connexion;

	}

}
