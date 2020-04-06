package fr.eni.encheres.bo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Enchere {
	private int montantEnchere;
	private Date dateEnchere;
	private Utilisateur unUtilisateur;
	private ArticleVendu unArticleVendu; // commentaire pour modifier la class 4 fois
	
	
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public Utilisateur getUnUtilisateur() {
		return unUtilisateur;
	}
	public void setUnUtilisateur(Utilisateur unUtilisateur) {
		this.unUtilisateur = unUtilisateur;
	}
	public ArticleVendu getUnArticleVendu() {
		return unArticleVendu;
	}
	public void setUnArticleVendu(ArticleVendu unArticleVendu) {
		this.unArticleVendu = unArticleVendu;
	}

	
	@Override
	public String toString() {
		Instant dateInstant = dateEnchere.toInstant();
		LocalDateTime dateEnchereLdt = dateInstant.atOffset(ZoneOffset.UTC).toLocalDateTime();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		StringBuilder sb = new StringBuilder();
		sb.append("Enchere  : ");
		sb.append("\n");
		sb.append("montant enchere = ");
		sb.append(montantEnchere);
		sb.append("; date enchere = ");
		sb.append(dateEnchereLdt.format(formatDate));
		sb.append("; utilisateur = ");
		sb.append(unUtilisateur.getNom());
		sb.append(" ");
		sb.append(unUtilisateur.getPrenom());
		sb.append("; article vendu = N°");
		sb.append(unArticleVendu.getNoArticle());
		sb.append(unArticleVendu.getNomArticle());
		
		return sb.toString();
	}

	
	
}
