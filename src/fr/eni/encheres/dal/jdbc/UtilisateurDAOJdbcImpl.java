package fr.eni.encheres.dal.jdbc;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDao;

public class UtilisateurDAOJdbcImpl implements UtilisateurDao{

	private static String RQT_SELECTALL = "SELECT * FROM UTILISATEURS;";
	private static String RQT_SELECTBYID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?;";
	private static String RQT_SELECTBYPSEUDOPASSWORD = "SELECT * FROM UTILISATEUR WHERE pseudo = ? AND mot_de_passe = ?;";
	private static String RQT_INSERT = "INSERT INTO UTILISATEUR VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static String RQT_UPDATE = "UPDATE UTILISATEUR SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?;";
	private static String RQT_DELETE = "DELETE FROM UTILISATEUR WHERE no_utilisateur = ?";
	@Override
	public List<Utilisateur> getAllUtilisateur() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Utilisateur getUtilisateurById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Utilisateur getUtilisateurByPseudoPassword(String pseudo, String MotDePasse) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Utilisateur insert(Utilisateur unUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete(Utilisateur unUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Utilisateur unUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
