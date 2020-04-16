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
	<div class="divConnetionTitle"><h6 class="text-center venteTitle">Détail de la vente</h6></div>
		<div class="card cardVente">
		<div class="card-body">
			<div class="row">
				<div class="col-sm-3">
					<div id="imageVente">
						<img id="image"class="imgUploaded" id="" src="./img/${unArticleVendu.picture}" alt="image" />
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-8">
					
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
					<form action="${pageContext.request.contextPath}/ServletEncherirVente?idArticle=${unArticleVendu.noArticle}" method="post">
						<div class="form-group row">							
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${unArticleVendu.nomArticle}</label>
							</div>
						</div>
							<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Description :<br></label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control noResize">${unArticleVendu.description}</label>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Categorie </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${unArticleVendu.categorie.libelle}</label>
							</div>
						</div>						
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Meilleure offre : </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${unArticleVendu.prixVente} points par ${utilisateurEnchere.pseudo}</label>	
								<input type="hidden" value="${unArticleVendu.prixVente}" name="meilleureOffre" />	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Mise à prix : </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${unArticleVendu.miseAPrix} points</label>	
							</div>
						</div>						
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Fin de l'enchère :</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<label class="form-control">${dateFin}</label>
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
								<label class="form-control">${unArticleVendu.utilisateur.pseudo}</label>
							</div>
						</div>

						<c:if test="${userSession.noUtilisateur != unArticleVendu.utilisateur.noUtilisateur}">
							<div class="form-group row">
								<label class="col-sm-3 col-md-3 col-lg-3">Ma proposition :</label>
								<div class="col-sm-6 col-md-9 col-lg-8">
									<input type="text" class="form-control" name="proposition">
								</div>
							</div>
						</c:if>
						
						<div class="row divbtnVente profileButtons">
						<c:choose>
						<c:when test="${userSession != null && userSession.noUtilisateur != unArticleVendu.utilisateur.noUtilisateur}">
							<div class="col-sm-4">
								<a class="btn btn-lg" ><button type="submit" class="btn btn-outline-secondary btnCustom">Enchérir</button></a>
							</div>							
						</c:when>
						<c:when test="${userSession.noUtilisateur == unArticleVendu.utilisateur.noUtilisateur && canUpdate}">
							<div class="col-sm-4 ">
								<a class="btn btn-lg" ><button type="submit" class="btn btn-outline-secondary btnCustom">Modifier
								</button></a>
							</div>	
						</c:when>
						</c:choose>						
							<div class="col-sm-4 offset-sm-2">
								<a class="btn btn-lg" href="${pageContext.request.contextPath}/Index"><button type="button" class="btn btn-outline-secondary btnCustom">Annuler</button></a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		</div>
</div>
	
	<%@ include file="../includes/footer.html"%>
	
</body>
</html>