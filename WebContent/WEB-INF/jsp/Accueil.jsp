<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/includes/nav.jsp"></jsp:include>
<%@ page import="fr.eni.encheres.messages.LecteurMessage"%>
<%@ page import="fr.eni.encheres.bo.Categorie"%>
<%@ page import="java.util.List"%>
<%@ page import="fr.eni.encheres.utils.Utils"%>
<%@ page import="fr.eni.encheres.messages.LecteurSuccess"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


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


<!--  Gestion des succes -->
<c:if test="${!empty listeCodesSuccess}">
	<div class="alert alert-success alertAccueil text-center" role="alert">
		<ul class="ulErrors">
			<c:forEach var="code" items="${listeCodesSuccess}">
				<li>${LecteurSuccess.getMessage(code)}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<c:if test="${not empty User}">
	Mes crédits : ${User.credit }
	</c:if>

<body class="container">
	<hr>
	<h3 class="text-center">Liste des enchères</h3>
	<hr>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-12 divAccueil">
			<div class="col-auto my-1s">
					<p>Filtres :</p>
				</div>

				<form method="post" action="/ENI-Encheres/Index">

					<div class="input-group input-focus col-sm-5">
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
								<c:when test="${Categorie eq 'Toutes' }">
									<option selected>Toutes</option>
								</c:when>
								<c:otherwise>
									<option>Toutes</option>
								</c:otherwise>
							</c:choose>
							<c:forEach items="${Categories}" var="element">
								<c:choose>
									<c:when test="${Categorie eq element.libelle}">
										<option selected>${element.libelle}</option>
									</c:when>
									<c:otherwise>
										<option>${element.libelle}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
								<input type="submit" class="btn btn-secondary" value="Rechercher">
					</div>
					
			</div>
			<div class="col-md-3">
				<c:if test="${not empty User}">
					<div>
						<input type="radio" id="MesAchats" name="ChoixModeAffichage"
							value="MesAchats"
							<c:choose> 
										<c:when test ="${ChoixModeAffichage eq 'MesAchats'}">
											checked
										</c:when>
									</c:choose>>
						<label for="MesAchats">Mes Enchères</label>
					</div>

					<div>
						<input type="radio" id="MesVentes" name="ChoixModeAffichage"
							value="MesVentes"
							<c:choose> 
										<c:when test ="${ChoixModeAffichage eq 'MesVentes'}">
											checked
										</c:when>
									</c:choose>>
						<label for="MesVentes">Mes Ventes</label>
					</div>

					<div>
						<input type="radio" id="Tous" name="ChoixModeAffichage"
							value="Tous"
							<c:choose> 
										<c:when test ="${ChoixModeAffichage eq 'Tous'}">
											checked
										</c:when>
									</c:choose>>
						<label for="Tous">Tous</label>
					</div>
				</c:if>
			</div>
			<div class="col-md-3">

				<c:if test="${not empty User}">
					<div>
						<input type="radio" id="EnCours" name="ChoixTime" value="EnCours"
							<c:choose> 
										<c:when test ="${ChoixTime eq 'EnCours'}">
											checked
										</c:when>
									</c:choose>>
						<label for="EnCours">En cours</label>
					</div>

					<div>
						<input type="radio" id="Termines" name="ChoixTime"
							value="Termines"
							<c:choose> 
										<c:when test ="${ChoixTime eq 'Termines'}">
											checked
										</c:when>
									</c:choose>>
						<label for="Termines">Terminées</label>
					</div>
				</c:if>
			</div>
	
			</form>

		</div>
	</div>
	</div>



	<div class="container divEncheres">
		<div class="row justify-content-center">

			<c:choose>
				<c:when test="${fn:length(Encheres) gt 0}">
					<c:forEach items="${Encheres}" var="element">

						<div class="col-lg-4 d-flex align-items-stretch">
								<c:choose>
									<c:when test="${ChoixTime eq 'EnCours'}">		
									 <a href="${pageContext.request.contextPath}/ServletDetailVente?idVendeur=${element.unArticleVendu.utilisateur.noUtilisateur}&idArticle=${element.unArticleVendu.noArticle}&idAcheteur=${element.unUtilisateur.noUtilisateur}">
								</c:when>
									<c:otherwise>
									 <a href="${pageContext.request.contextPath}/ServletRemportEnchere?idVendeur=${element.unArticleVendu.utilisateur.noUtilisateur}&idArticle=${element.unArticleVendu.noArticle}&idAcheteur=${element.unUtilisateur.noUtilisateur}">
									</c:otherwise>
								</c:choose>
								
								<div class="card" style="width: 18rem;">
									<img class="card-img-top"
										<c:choose>
											<c:when test="${not empty element.unArticleVendu.picture}">
												src="./img/${element.unArticleVendu.picture }"
											</c:when>
											<c:otherwise>
												src="./img/empty.png"
											</c:otherwise>
										</c:choose>
										height="150">
									<c:choose>
										<c:when test="${ChoixTime eq 'EnCours'}">
											<div class="card-body">
												<h5 class="card-title">${element.unArticleVendu.nomArticle}</h5>
												<p class="card-text">Prix : ${element.montantEnchere }</p>
												<p class="card-text">Meilleur encherisseur :
													${element.unUtilisateur.pseudo }
												<p>Fin de l'enchere <fmt:formatDate pattern = "dd/MM/yyyy" value="${element.dateEnchere }"/></p>
												<a
													href="${pageContext.request.contextPath}/ServletAffichageProfil?profil=${element.unArticleVendu.utilisateur.noUtilisateur}">
													<p class="card-text">Vendeur :
														${element.unArticleVendu.utilisateur.pseudo }</p>
												</a>
										</c:when>
										<c:otherwise>
											<div class="card-body">
												<h5 class="card-title">${element.unArticleVendu.nomArticle}</h5>
												<p class="card-text">Remport : ${element.montantEnchere }</p>
												<p class="card-text">Enchère gagnée par
													${element.unUtilisateur.pseudo }
												<p>Enchere fini le  <fmt:formatDate  pattern = "dd/MM/yyyy" value="${element.dateEnchere }"/></p>
												<a
													href="${pageContext.request.contextPath}/ServletAffichageProfil?profil=${element.unArticleVendu.utilisateur.noUtilisateur}">
													<p class="card-text">Vendeur :
														${element.unArticleVendu.utilisateur.pseudo }</p>
												</a>
										</c:otherwise>
									</c:choose>

								</div>
						</div>
						</a>
		</div>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<h6>Aucunes enchères disponible pour la selection</h6>
		</c:otherwise>
		</c:choose>





	</div>
	</div>


</body>
</html>