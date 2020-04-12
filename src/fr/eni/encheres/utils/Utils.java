package fr.eni.encheres.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.JdbcTools;

/**
 * Classe en charge de contenir les méthodes utilitaires
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class Utils {
	
	/**
	 * 
	 * Méthode en charge de récupérer une date au format souhaité (mis en paramètre)
	 * @param date
	 * @param format
	 * @return
	 */
	//Date vient de utils.date, le format peut se traduire comme : dd/MM/yyyy
	public static String getDateFormate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);  
	    String strDate = formatter.format(date); 
		
		return strDate;
	}
	
	/**
	 * 
	 * Méthode en charge d'exécuter des scripts sql (utilisé notamment dans les tests)
	 * @param scriptFilePath
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void executeQuery(String scriptFilePath) throws IOException, SQLException {

		BufferedReader reader = null;
		Connection con = null;
		Statement statement = null;
		try (Connection cnx = JdbcTools.getConnection()) {
			// create statement object
			statement = cnx.createStatement();
			// initialize file reader
			reader = new BufferedReader(new FileReader(scriptFilePath));
			String line = null;
			// read script line by line
			while ((line = reader.readLine()) != null) {
				// execute query
				statement.execute(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Méthode en charge de gérer le type de connexion
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException
	{
		 Connection cnx = null;
			
		if(DebugUtils.useJdbcTools)
			{
				cnx = JdbcTools.getConnection();
			}
		else
			{
				cnx = ConnectionProvider.getConnection();
			}
			
		return cnx;
	}
	
	/**
	 * 
	 * Méthode en charge de crypter le mot de passe
	 * @param motDePasse
	 * @return String
	 */
	public static String toMD5(String motDePasse)
	{
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(motDePasse.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            motDePasse = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		
		return motDePasse;
	}
	
}
