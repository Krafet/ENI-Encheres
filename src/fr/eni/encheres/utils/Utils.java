package fr.eni.encheres.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
	
}
