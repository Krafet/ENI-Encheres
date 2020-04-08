package fr.eni.encheres.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
	//Date vient de utuls.date, le format peut se traduire comme : dd/MM/yyyy
	public String getDateFormate(Date date, String format) {
		Instant dateInstant = date.toInstant();
		LocalDateTime ldt = dateInstant.atOffset(ZoneOffset.UTC).toLocalDateTime();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(format);
		
		return ldt.format(formatDate);
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
	
}
