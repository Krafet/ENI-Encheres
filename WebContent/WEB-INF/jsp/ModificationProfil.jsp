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

		<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
				<strong>Erreur!</strong>
				<ul>
					<c:forEach var="code" items="${listeCodesErreur}">
						<li>${LecteurMessage.getMessageErreur(code)}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<div class="row justify-content-center">
			<div class="col-md-9">
				<h4 class="text-center">Mon profil</h4>
				<div class="card modifProfil">
					<article class="card-body">
						<form
							action="${pageContext.request.contextPath}/ServletModificationProfil"
							method="post">
							<div class="form-row">
								<div class="col form-group">
									<label>Pseudo </label> <input type="text" class="form-control"
										placeholder="" name="pseudo" value="${user.pseudo}"
										minlength="2" maxlength="30">
								</div>
								<!-- form-group end.// -->
								<div class="col form-group">
									<label>Nom</label> <input type="text" class="form-control"
										placeholder=" " name="nom" value="${user.nom}"
										pattern="[^0-9]{3,30}">
								</div>
								<!-- form-group end.// -->
							</div>
							<!-- form-row end.// -->
							<div class="form-row">
								<div class="col form-group">
									<label>Prenom </label> <input type="text" class="form-control"
										placeholder="" name="prenom" value="${user.prenom}"
										pattern="[^0-9]{3,30}">
								</div>
								<!-- form-group end.// -->
								<div class="col form-group">
									<label>Email</label> <input type="email" class="form-control"
										placeholder="" name="email" required value="${user.email}"
										maxlength="50">
								</div>
								<!-- form-group end.// -->
							</div>
							<!-- form-row end.// -->
							<div class="form-row">
								<div class="col form-group">
									<label>Téléphone </label> <input type="text"
										class="form-control" placeholder="" name="tel"
										value="${user.telephone}"
										pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}">
								</div>
								<!-- form-group end.// -->
								<div class="col form-group">
									<label>Rue</label> <input type="text" class="form-control"
										placeholder=" " name="rue" value="${user.rue}" maxlength="50">
								</div>
								<!-- form-group end.// -->
							</div>
							<!-- form-row end.// -->

							<div class="form-row">
								<div class="col form-group">
									<label>Code Postal </label> <input type="text"
										class="form-control" placeholder="" name="codePostal"
										value="${user.codePostal}" max="99999" pattern="[0-9]{5}">
								</div>
								<!-- form-group end.// -->
								<div class="col form-group">
									<label>Ville</label> <input type="text" class="form-control"
										placeholder="" name="ville" value="${user.ville}">

								</div>
								<!-- form-group end.// -->
							</div>
							<!-- form-row end.// -->

							<div class="form-row">
								<div class="col form-group">
									<label>Mot de passe actuel</label> <input class="form-control"
										type="password" name="actual_pass" maxlength="30"
										value="${user.motDePasse}">
								</div>

							</div>
							<!-- form-row end.// -->
							<div class="form-row">
								<div class="col form-group">
									<label>Nouveau mot de passe</label> <input class="form-control"
										type="password" name="new_pass" maxlength="30" value="">
								</div>
								<div class="col form-group">
									<label>Confirmation</label> <input class="form-control"
										type="password" name="confirm_pass" maxlength="30" value="">
								</div>
							</div>
				</div>
				<!-- <div class="row divBtn">
					<button type="submit"
						class="btn btn-sm btn-outline-secondary " value="valider"
						name="choix">Enregistrer les modifications</button>
						
						
					</form>
					<form
						action="${pageContext.request.contextPath}/ServletSuppressionCompte"
						method="post">
						<input type="submit"
							class="btn btn-sm btn-outline-secondary "
							value="Supprimer mon compte" />
					</form>
					</article>

						<a
							href="${pageContext.request.contextPath}/ServletAffichageProfil"><button
								class="btn btn-sm btn-outline-secondary "
								value="retour" name="choix">Retour</button> </a>
					
				</div> -->
				
				
				<div class="row divBtn">
					<button type="submit"
						class="btn  btn-outline-secondary" value="valider"
						name="choix">Enregistrer les modifications</button>
							<button 
						class="btn btn-outline-secondary " 
						name="choix"><a href="${pageContext.request.contextPath}/ServletSuppressionCompte">Supprimer mon compte</a></button>
							<button 
						class="btn  btn-outline-secondary" 
						name="choix"><a href="${pageContext.request.contextPath}/ServletAffichageProfil">Retour</a></button>

								
					</form>
				</div>
				
			</div>
		</div>


		<%@ include file="../includes/footer.html"%>
</body>