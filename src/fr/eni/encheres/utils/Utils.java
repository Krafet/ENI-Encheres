package fr.eni.encheres.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {
	
	//Date vient de utuls.date, le format peut se traduire comme : dd/MM/yyyy
	public String getDateFormate(Date date, String format) {
		Instant dateInstant = date.toInstant();
		LocalDateTime ldt = dateInstant.atOffset(ZoneOffset.UTC).toLocalDateTime();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(format);
		
		return ldt.format(formatDate);
	}
	
}
