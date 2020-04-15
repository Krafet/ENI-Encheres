<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/includes/nav.jsp"></jsp:include>
<%@ page import="fr.eni.encheres.messages.LecteurMessage"%>
<%@ page import="fr.eni.encheres.bo.Categorie"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>

<!--  Gestion des erreurs -->
<c:if test="${!empty listeCodesErreur}">
	<div class="alert alert-danger alertConnection text-center"
		role="alert">
		<ul class="ulErrors">
			<c:forEach var="code" items="${listeCodesErreur}">
				<li>${LecteurMessage.getMessageErreur(code)}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<body class="container">

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-10">
				Filtres :

				<form method="post" action="/ENI-Encheres/Index">

					<div class="input-group input-focus col-sm-8">
						<div class="input-group-prepend">
							<span class="input-group-text bg-white"><i
								class="fa fa-search"></i></span>
						</div>
						<input type="search" name="Recherche"
							placeholder="Le nom de l'article contient" value="${Recherche}"
							class="form-control border-left-0">
					</div>


					<div class="col-auto my-1">
						<label class="mr-sm-2" for="inlineFormCustomSelect">Catégorie
							:</label> <select class="custom-select mr-sm-2 col-sm-2"
							id="inlineFormCustomSelect" name="Categorie">
								<c:choose>
									<c:when test="${Categorie == 'Toutes' }">
										<option selected>Toutes</option>
									</c:when>
									<c:otherwise>
										<option>Toutes</option>
									</c:otherwise>
								</c:choose>
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

			</div>
		</div>
	</div>


	<div class="container text-center">
					<h3>Liste des enchères</h3>
		<div class="row justify-content-center">
	<c:choose>
<c:when test="${fn:length(Encheres) gt 0}">


<c:forEach items="${Encheres}" var="element">
				<div class="card" style="width: 18rem;">

					<div class="card-body">
						<img src="./img/${element.unArticleVendu.picture }" width="300" height="150">
						<h5 class="card-title">${element.unArticleVendu.nomArticle}</h5>
						<p class="card-text">Prix : ${element.montantEnchere }</p>
						<p class="card-text">Fin de l'enchere ${element.dateEnchere }</p>
						<a
							href="${pageContext.request.contextPath}/ServletAffichageProfil?profil=${element.unUtilisateur.noUtilisateur}">
							<p class="card-text">Vendeur : ${element.unUtilisateur.pseudo }</p>
						</a> <a href="${pageContext.request.contextPath}/ServletDetailVente?idUser=${element.unUtilisateur.noUtilisateur}&idArticle=${element.unArticleVendu.noArticle}" class="btn btn-primary">Go</a>
						
					</div>
				</div>
			</c:forEach>
</c:when>
<c:otherwise>
<h6> Aucunes encheres disponible pour la selection</h6>
</c:otherwise>
</c:choose>

			

		</div>
	</div>


</body>
</html>