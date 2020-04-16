<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/includes/nav.jsp"></jsp:include>

<body class="container">

	<div class="container">
		<div class="card cardVente">
		<div class="card-body">
			<div class="row">
				<div class="col-sm-3">
					<div id="imageVente">
						<img id="imageArticle"class="imgArticle" id="" src="${pageContext.request.contextPath}/img/close.png" alt="image" />
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-8">
					
					<h2 class="newSellTitle">Détail vente</h2>
						<!--  Gestion des erreurs -->
						<c:if test="${!empty listeCodesErreur}">
							<div class="alert alert-danger alertInscription text-center"
								role="alert">
								<ul class="ulErrors">
									<c:forEach var="code" items="${listeCodesErreur}">
										<li>${LecteurMessage.getMessageErreur(code)}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						
						<div class="form-group row">							
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${uneEnchere.unArticleVendu.nomArticle}</label>
							</div>
						</div>
							<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Description :<br></label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control noResize">${uneEnchere.unArticleVendu.description}</label>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Categorie </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${uneEnchere.unArticleVendu.categorie.libelle}</label>
							</div>
						</div>						
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Meilleure offre : </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${uneEnchere.unArticleVendu.prixVente}</label>	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Mise à prix : </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${uneEnchere.unArticleVendu.miseAPrix}</label>	
							</div>
						</div>						
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Fin de l'enchère :</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${uneEnchere.unArticleVendu.dateFinEncheres}</label>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Retrait :</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${unRetrait.rue}, ${unRetrait.codePostal} ${unRetrait.ville}</label>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Vendeur :</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${uneEnchere.unUtilisateur.pseudo}</label>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Ma proposition :</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input type="text" class="form-control">
							</div>
						</div>
						
						<div class="row divbtnVente profileButtons">
						<c:choose>
						<c:when test="${userSession != null && userSession.noUtilisateur != uneEnchere.unUtilisateur.noUtilisateur}">
							<div class="col-sm-4 offset-sm-1">
								<a class="btn btn-lg" ><button type="submit" class="btn btn-secondary">Enchérir</button></a>
							</div>
						</c:when>
						<c:when test="${userSession.noUtilisateur == uneEnchere.unUtilisateur.noUtilisateur}">
							<div class="col-sm-4 offset-sm-1">
								<a class="btn btn-lg" ><button type="submit" class="btn btn-secondary">Modifier
								</button></a>
							</div>	
						</c:when>
						</c:choose>						
							<div class="col-sm-4 offset-sm-3">
								<a class="btn btn-lg" href="${pageContext.request.contextPath}/Index"><button type="button" class="btn btn-secondary">Annuler</button></a>
							</div>
						</div>
				</div>
			</div>
		</div>
		</div>
</div>
	
	<%@ include file="../includes/footer.html"%>
	
</body>
</html>