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


		<!--  Gestion des erreurs -->
		<c:choose>
			<c:when test="${!empty listeCodesErreur}">
				<div class="alert alert-danger" role="alert">
				<strong>Erreur!</strong>
					<ul class="ulErrors">
						<c:forEach var="code" items="${listeCodesErreur}">
							<li>${LecteurMessage.getMessageErreur(code)}</li>
						</c:forEach>
					</ul>
				</div>
			</c:when>
			<c:otherwise>


				<!-- Affichage des infos utilisateur -->
				<div class="row mainDivProfile justify-content-center">
				<div class="col-md-10">
					<div class="card">
						<article class="card-body">
							<div class="col-sm-12 col-md-8 col-lg-6 offset-md-2 offset-lg-4">
								<div class="row ">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Pseudo :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${sessionScope.user.pseudo}</div>
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Nom :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${user.nom}</div>
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Prénom :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${user.prenom}</div>
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Email :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${user.email}</div>
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Téléphone :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${user.telephone}</div>
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Rue :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${user.rue}</div>
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Code Postal :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${user.codePostal}</div>
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Ville :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${user.ville}</div>
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-4 col-lg-4 labelProfil">
										<label>Crédit :</label>
									</div>
									<div class="col-sm-6 col-md-8 col-lg-6">${user.credit}</div>
								</div>
								<div class="row profileButtons">
									<c:if test="${isCurrentUser}">
										<div class="col-sm-6 col-md-6 col-lg-4 labelProfil">
											<a class="btn btn-lg"
												href="${pageContext.request.contextPath}/ServletModificationProfil"><button
													type="button" class="btn btn-outline-secondary">Modifier</button></a>
										</div>
									</c:if>
									<c:if test="${!isCurrentUser}">
										<div class="col-sm-2 col-md-2 col-lg-2"></div>
									</c:if>
									<div class="col-sm-6 col-md-6 col-lg-4">
										<a class="btn btn-lg"
											href="${pageContext.request.contextPath}/ServletAccueil"><button
												type="button" class="btn btn-outline-secondary">Retour</button></a>
									</div>
								</div>
							</div>
						</article>
					</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="../includes/footer.html"%>
</body>
</html>