import java.io.IOException;
import java.util.Date;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.utils.Utils;

// Commentaire
public class EnchereDAOTest {
	
	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	
	UtilisateurDAO utilsateurDAO = DAOFactory.getUtilisateurDAO();
	ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
	
	Enchere enchereTest;
	Utilisateur utilisateur;
	ArticleVendu article;
	
	@before
	public void setUp() throws Exception {
		
		// On reset la base de données avant chaque test
		try {
			Utils.executeQuery("db/reset.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Date date = new Date("1995-10-10");
		
		Categorie uneCat = new Categorie("CategorieTEST");	
		
		ArticleVendu article = new ArticleVendu(1, "nomArticle", "description", date,
				date, 5, 10, true, null,
				uneCat, null);
		
		utilisateur = new Utilsateur();
		utilisateur.setNoUtilisateur(1);
		

	}
	
	@Test
	public void insertTest() throws BusinessException{
		
		enchereTest = new Enchere(10, date, utilisateur, article);
		Enchere enchereInsert = enchereDAO.insert(enchereTest);		
		Enchere enchereRecuperer = enchereDAO.selectById(1, 1);

	}
	
	
	
	@Test
	public void updateTest() throws BusinessException{
		
		boolean result = enchereDAO.update(enchereTest);
		assertEquals(result, true);
	}
	
	@Test
	public void selectAllTest() throws BusinessException{
		
		List<Enchere> listeEnchere = enchereDAO.selectAll();
		
		assertEquals(1, listeEnchere.size()==1);
	}
	
	@Test
	public void deleteTest() throws BusinessException{
		
		boolean result = enchereDAO.delete(article.getNoArticle(), utilisateur.getNoUtilisateur());
		assertEquals(result, true);
	}
}
