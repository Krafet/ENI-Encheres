package fr.eni.encheres.bo;

import java.util.Date;
import java.util.Objects;

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
	private ArticleVendu unArticleVendu; 
	
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

	

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [montantEnchere=");
		builder.append(montantEnchere);
		builder.append(", dateEnchere=");
		builder.append(dateEnchere);
		builder.append(", unUtilisateur=");
		builder.append(unUtilisateur);
		builder.append(", unArticleVendu=");
		builder.append(unArticleVendu);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		  if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Enchere that = (Enchere) o;
	        return montantEnchere == that.montantEnchere &&
	        	    Objects.equals(dateEnchere, that.dateEnchere) &&
	                Objects.equals(unUtilisateur.getNoUtilisateur(), that.unUtilisateur.getNoUtilisateur()) &&
	                Objects.equals(unArticleVendu, that.unArticleVendu);
	}
	
	
	
}
