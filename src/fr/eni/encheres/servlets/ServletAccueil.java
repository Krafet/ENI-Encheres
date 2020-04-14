package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Index")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String recherche = null;
	String categorie = null;
	
	
	UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
    EnchereManager enchereManager = EnchereManager.getEnchereManager();
	CategorieManager categorieManager = CategorieManager.getCategorieManager();

		

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		
		List<Categorie> listCat = categorieManager.getCategories();
		List<Enchere> listEnchere = enchereManager.getEncheres();
		
		List<Enchere> processEnchere = new ArrayList<>();


		if(categorie != null)
		{
			
			if(categorie.equals("Toutes"))
			{
				processEnchere = listEnchere;
			}
			else
			{
				for(int i = 0; i < listEnchere.size(); i++)
				{
					if(listEnchere.get(i).getUnArticleVendu().getCategorie().getLibelle().equals(categorie))
					{
						processEnchere.add(listEnchere.get(i));
					}
				}
			}	
		}
		else
		{
			processEnchere = listEnchere;
		}
		
		
		
		if(recherche != null)
		{
			listEnchere = processEnchere;
			processEnchere = new ArrayList<>();
			for(int i = 0; i < listEnchere.size(); i++)
			{
				CharSequence c = recherche.toUpperCase();
				
				if(listEnchere.get(i).getUnArticleVendu().getNomArticle().toUpperCase().contains(c))
				{
					processEnchere.add(listEnchere.get(i));
				}
			}
		}
		

		
		
		processEnchere.forEach(c -> System.out.println(c.toString()));
		
		request.setAttribute("Categories", listCat);
		request.setAttribute("Encheres", processEnchere);
		
		request.setAttribute("Categorie", categorie);
		request.setAttribute("Recherche", recherche);
	
		
	
		
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		recherche = request.getParameter("Recherche");
		categorie = request.getParameter("Categorie");

		
		doGet(request, response);
	}

}
