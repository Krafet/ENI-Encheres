package fr.eni.encheres.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * Classe en charge de définir les caractéristiques de l'objet ArticleVendu (attributs et méthodes)
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class ArticleVendu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private boolean etatVente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Retrait retrait;
	private String picture;
	
	
	/**
	 * 
	 * Constructeur vide
	 */
	public ArticleVendu() {
		
	}
	
	/**
	 * 
	 * Constructeur
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param utilisateur
	 * @param categorie
	 * @param retrait
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, Utilisateur utilisateur,
			Categorie categorie, Retrait retrait) {
		this(nomArticle,description,dateDebutEncheres,dateFinEncheres, miseAPrix,prixVente,etatVente,utilisateur,categorie, retrait);
		this.noArticle = noArticle;

	}
	
	/**
	 * 
	 * Constructeur
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param utilisateur
	 * @param categorie
	 * @param retrait
	 */
	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int miseAPrix, int prixVente, boolean etatVente, Utilisateur utilisateur, Categorie categorie, Retrait retrait) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
	}
	
	/**
	 * 
	 * Constructeur
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param utilisateur
	 * @param categorie
	 * @param retrait
	 * @param picture
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, Utilisateur utilisateur,
			Categorie categorie, Retrait retrait, String picture) {
		this(nomArticle,description,dateDebutEncheres,dateFinEncheres, miseAPrix,prixVente,etatVente,utilisateur,categorie, retrait);
		this.noArticle = noArticle;
		this.picture = picture;

	}
	
	/**
	 * 
	 * Constructeur
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param utilisateur
	 * @param categorie
	 * @param retrait
	 * @param picture
	 */
	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, Utilisateur utilisateur,
			Categorie categorie, Retrait retrait, String picture) {
		this(nomArticle,description,dateDebutEncheres,dateFinEncheres, miseAPrix,prixVente,etatVente,utilisateur,categorie, retrait);
		this.picture = picture;

	}

	
	/**
	 * Getter pour noArticle.
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
	}

	/**
	 * Setter pour noArticle.
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 * Getter pour nomArticle.
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * Setter pour nomArticle.
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * Getter pour description.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter pour description.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter pour dateDebutEncheres.
	 * @return the dateDebutEncheres
	 */
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * Setter pour dateDebutEncheres.
	 * @param dateDebutEncheres the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	/**
	 * Getter pour dateFinEncheres.
	 * @return the dateFinEncheres
	 */
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * Setter pour dateFinEncheres.
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 * Getter pour miseAPrix.
	 * @return the miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * Setter pour miseAPrix.
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * Getter pour prixVente.
	 * @return the prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * Setter pour prixVente.
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * Getter pour etatVente.
	 * @return the etatVente
	 */
	public boolean isEtatVente() {
		return etatVente;
	}

	/**
	 * Setter pour etatVente.
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * Getter pour utilisateur.
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * Setter pour utilisateur.
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * Getter pour categorie.
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * Setter pour categorie.
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * Getter pour retrait.
	 * @return the retrait
	 */
	public Retrait getRetrait() {
		return retrait;
	}

	/**
	 * Setter pour retrait.
	 * @param retrait the retrait to set
	 */
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	/**
	 * Getter pour serialversionuid.
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	/**
	 * Getter pour picture.
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * Setter pour picture.
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}



	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVendu [noArticle=");
		builder.append(noArticle);
		builder.append(", nomArticle=");
		builder.append(nomArticle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateDebutEncheres=");
		builder.append(dateDebutEncheres);
		builder.append(", dateFinEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", miseAPrix=");
		builder.append(miseAPrix);
		builder.append(", prixVente=");
		builder.append(prixVente);
		builder.append(", etatVente=");
		builder.append(etatVente);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append(", retrait=");
		builder.append(retrait);
		builder.append(", picture=");
		builder.append(picture);
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
        ArticleVendu that = (ArticleVendu) o;
        return noArticle == that.noArticle &&
        	    Objects.equals(nomArticle, that.nomArticle) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dateDebutEncheres, that.dateDebutEncheres) &&
                Objects.equals(dateFinEncheres, that.dateFinEncheres) &&
                Objects.equals(miseAPrix, that.miseAPrix) &&
                Objects.equals(prixVente, that.prixVente) &&
                Objects.equals(etatVente, that.etatVente) &&
                Objects.equals(utilisateur, that.utilisateur) &&
                Objects.equals(categorie, that.categorie) &&
                Objects.equals(retrait, that.retrait);
	}
	
}
