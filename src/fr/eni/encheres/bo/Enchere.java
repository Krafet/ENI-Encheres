package fr.eni.encheres.bo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * Classe en charge de définir les caractéristiques de l'objet Enchere (attributs et méthodes)
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class Enchere {
	private int montantEnchere;
	private Date dateEnchere;
	private Utilisateur unUtilisateur;
	private ArticleVendu unArticleVendu; // commentaire pour modifier la class 4 fois
	
	/**
	 * 
	 * Constructeur
	 */
	public Enchere() {
		super();
	}
	
	/**
	 * 
	 * Constructeur
	 * @param montantEnchere
	 * @param dateEnchere
	 * @param unUtilisateur
	 * @param unArticleVendu
	 */
	public Enchere(int montantEnchere, Date dateEnchere, Utilisateur unUtilisateur, ArticleVendu unArticleVendu) {
		super();
		this.montantEnchere = montantEnchere;
		this.dateEnchere = dateEnchere;
		this.unUtilisateur = unUtilisateur;
		this.unArticleVendu = unArticleVendu;
	}
	

	
	/**
	 * Getter pour montantEnchere.
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * Setter pour montantEnchere.
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * Getter pour dateEnchere.
	 * @return the dateEnchere
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * Setter pour dateEnchere.
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * Getter pour unUtilisateur.
	 * @return the unUtilisateur
	 */
	public Utilisateur getUnUtilisateur() {
		return unUtilisateur;
	}

	/**
	 * Setter pour unUtilisateur.
	 * @param unUtilisateur the unUtilisateur to set
	 */
	public void setUnUtilisateur(Utilisateur unUtilisateur) {
		this.unUtilisateur = unUtilisateur;
	}

	/**
	 * Getter pour unArticleVendu.
	 * @return the unArticleVendu
	 */
	public ArticleVendu getUnArticleVendu() {
		return unArticleVendu;
	}

	/**
	 * Setter pour unArticleVendu.
	 * @param unArticleVendu the unArticleVendu to set
	 */
	public void setUnArticleVendu(ArticleVendu unArticleVendu) {
		this.unArticleVendu = unArticleVendu;
	}

	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
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
		sb.append("; article vendu = N�");
		sb.append(unArticleVendu.getNoArticle());
		sb.append(unArticleVendu.getNomArticle());
		
		return sb.toString();
	}

	
	
}
