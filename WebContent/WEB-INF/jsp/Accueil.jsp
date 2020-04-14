<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/includes/nav.jsp"></jsp:include>
<%@ page import="fr.eni.encheres.bo.Categorie"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>



    <nav class="navbar navbar-expand-sm navbar-light bg-light mb-3">
	        <div class="container">
	              <a class="nav-link" href="${pageContext.request.contextPath}/ServletInscription">S'inscrire</a>
	              <a class="nav-link" href="${pageContext.request.contextPath}/ServletConnexion">Se Connecter</a>
	        </div>
	</nav>
	
	 <h6>Liste des enchères</h6>  
		Filtres :
		
		<form method="post" action="/ENI-Encheres/Index">
		
		<div class="input-group input-focus col-sm-8" >
	  		<div class="input-group-prepend">
	   		 	<span class="input-group-text bg-white"><i class="fa fa-search"></i></span>
	  		</div>
	  		<input type="search" name="Recherche" placeholder="Le nom de l'article contient" class="form-control border-left-0">
		</div>
		
		
				Resultat Recherche : 
		${Recherche}
		${Categorie}
		
				<div class="col-auto my-1">
			<label class="mr-sm-2" for="inlineFormCustomSelect">Catégorie :</label>
			<select class="custom-select mr-sm-2 col-sm-2" id="inlineFormCustomSelect" name="Categorie">
			        <c:if test="${empty Categorie}">
			        	<option selected>Toutes</option>
					</c:if>
	 				<c:if test="${not empty Categorie}">
			        	<option selected>Toutes</option>
					</c:if>
		        <c:forEach items="${Categories}" var="element">
			        <c:choose>
				        <c:when test="${Categorie == element }">
				       		<option selected>${element.libelle}</option>
						</c:when>
						<c:otherwise>
							<option>${element.libelle}</option>
						</c:otherwise>
					</c:choose> 	        
		        </c:forEach>
		     </select>
		</div>
		
		<input type="submit" value="Rechercher">
		</form>
		
		

		
		
		<c:forEach items="${Encheres}" var="element">
		<div class="card" style="width: 18rem;">
  			<img class="card-img-top" src="..." alt="Card image cap">
  			<div class="card-body">
    			<h5 class="card-title">${element.unArticleVendu.nomArticle} </h5>
   				 <p class="card-text">Prix : ${element.montantEnchere }</p>
   				 <p class="card-text">Fin de l'enchere ${element.dateEnchere }</p>
   				 <p class="card-text">Vendeur : ${element.dateEnchere }</p>
   				 
   			 <a href="#" class="btn btn-primary">Go</a>
  			</div>
</div>
		
		
		              
		        </c:forEach>
		
		
		
</body>
</html>