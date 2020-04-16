package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	String choix;
	
	
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
		
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		
		List<Categorie> listCat = categorieManager.getCategories();
		List<Enchere> listEnchere = enchereManager.getEncheres();
		
		List<Enchere> processEnchere = new ArrayList<>();
	
		
		//1
		List<Enchere> mesEncheres = new ArrayList<>();
		
		//2
		List<Enchere> mesVentes = new ArrayList<>();
		
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
		{
			request.setAttribute("User", session.getAttribute("user"));
			
			
			Utilisateur myself = (Utilisateur) session.getAttribute("user");
			
			System.out.println(myself);
				
			//1
			for(int i = 0; i < listEnchere.size(); i++)
			{
				if(listEnchere.get(i).getUnUtilisateur().equals(myself))
				{
					mesEncheres.add(listEnchere.get(i));
				}
			}
			
			//2
			for(int i = 0; i < listEnchere.size(); i++)
			{
				if(listEnchere.get(i).getUnArticleVendu().getUtilisateur().equals(myself))
				{
					mesVentes.add(listEnchere.get(i));
				}
			}
			
		}
		
	
		
		//Process enchere win TEMP
		
		for(int i = 0; i < listEnchere.size(); i++)
		{
			if(listEnchere.get(i).getDateEnchere().after(Date.valueOf(LocalDate.now())))
			{
				System.out.println("Enchere fini");
			}
		}
		
		


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
		request.setAttribute("EnchereDEBUG", mesEncheres);
		
		request.setAttribute("Categorie", categorie);
		request.setAttribute("Recherche", recherche);
	
		

		
	
	
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		recherche = request.getParameter("Recherche");
		categorie = request.getParameter("Categorie");
		
		choix = request.getParameter("ChoixModeAffichage");

		
		doGet(request, response);
	}

}
