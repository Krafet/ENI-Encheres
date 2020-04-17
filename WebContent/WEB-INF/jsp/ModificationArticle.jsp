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

		<div class="row justify-content-center">
			<div class="col-md-9">
					<div class="divConnetionTitle"><h6 class="text-center connectionTitle modifTitle">${article.nomArticle}</h6></div>
				
				<div class="card modifProfil">
					<article class="card-body">	
					<!--  Gestion des erreurs -->
				<c:if test="${!empty listeCodesErreur}">
					<div class="alert alert-danger alertModification text-center" role="alert">
						<ul class="ulErrors">
							<c:forEach var="code" items="${listeCodesErreur}">
								<li>${LecteurMessage.getMessageErreur(code)}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
						<form action="${pageContext.request.contextPath}/modifier_article?idArticle=${article.noArticle}&idVendeur=${article.utilisateur.noUtilisateur}&idAcheteur=${idAcheteur}" method="post">
							
							<div class="form-row">
								<div class="col form-group">
									<label>Nom Article </label> <input type="text" class="form-control"
										placeholder="" name="nomArticle" value="${article.nomArticle}"  maxlength="30"/>
								</div>
								<div class="col form-group">
									<label>Description</label> <input type="text" class="form-control"
										placeholder="" name="description" value="${article.description}">
								</div>
							</div>

							<div class="form-row">
								<div class="col form-group">
									<label>Date début des enchères </label> <br><span>${article.dateDebutEncheres}</span>;
								</div>
								<div class="col form-group">
									<label>Date fin des enchères</label> <br><span>${article.dateFinEncheres}</span>;
								</div>
							</div>
							
				
							<div class="form-row">
								<div class="col form-group">
									<label>Prix initiale </label> <input type="number"
										class="form-control" placeholder="" name="miseAPrix"
										value="${article.miseAPrix}">
								</div>					
								<div class="col form-group">
									<label>Prix de vente</label> <input type="number" class="form-control"
										placeholder=" " name="prixVente" value="${article.prixVente}" maxlength="50">
								</div>
						
							</div>
							
														<div class="form-row">
								<div class="col form-group">
								<label>Catégorie</label>
									<select name="categorie" class="form-control">
										<c:forEach items="${categories}" var="categorie">
      										
      										<c:choose>
											  <c:when test="${categorie.noCategorie!=article.categorie.noCategorie}">
											    <option value="${categorie.noCategorie}">${categorie.libelle}</option>
											  </c:when>
											  <c:otherwise>
											    <option value="${categorie.noCategorie}" selected>${categorie.libelle}</option>
											  </c:otherwise>
											</c:choose>
											
										</c:forEach>
									</select>
								</div>					
								<div class="col form-group">

								</div>
						
							</div>
					
				</div>
				
				<div class="row divBtn">
					<button type="submit" class="btn btn-sm btn-outline-secondary btnCustom" value="valider">Enregistrer les modifications</button>
					<button class="btn btn-outline-primary btnCustom" name="choix"><a href="${pageContext.request.contextPath}/ServletAffichageProfil">Retour</a></button>
		
					</form>
				</div>
				
			</div>
		</div>


		<%@ include file="../includes/footer.html"%>
</body>
</html>