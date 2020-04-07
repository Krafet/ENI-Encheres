package fr.eni.encheres.bo;

import java.io.Serializable;

/**
 * 
 * Classe en charge de définir les caractéristiques de l'objet Categorie (attributs et méthodes)
 * @author Camille
 * @version ENI-Encheres - v1.0
 * @date 7 avr. 2020
 */
public class Categorie implements Serializable {

	private static final long serialVersionUID = -753861761821885600L;
	
	private int noCategorie;
	private String libelle;

	
	/**
	 * 
	 * Constructeur
	 */
	public Categorie() {
		super();
	}
	
	/**
	 * 
	 * Constructeur
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	

	
	/**
	 * Getter pour noCategorie.
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}

	/**
	 * Setter pour noCategorie.
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * Getter pour libelle.
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Setter pour libelle.
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Categorie [getNoCategorie()=" + getNoCategorie() + ", getLibelle()=" + getLibelle() + "]";
	}
	
	
	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + noCategorie;
		return result;
	}
	@Override
	/**
	 * 
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (noCategorie != other.noCategorie)
			return false;
		return true;
	}
}
