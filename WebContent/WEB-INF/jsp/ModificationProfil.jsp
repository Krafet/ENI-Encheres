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
					<div class="divConnetionTitle"><h6 class="text-center connectionTitle modifTitle">Mon profil</h6></div>
				
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
						<form action="${pageContext.request.contextPath}/ServletModificationProfil" method="post">
							
							<div class="form-row">
								<div class="col form-group">
									<label>Pseudo </label> <input type="text" class="form-control"
										placeholder="" name="pseudo" value="${sessionScope.user.pseudo}"  maxlength="30"/>
								</div>
								<div class="col form-group">
									<label>Nom</label> <input type="text" class="form-control"
										placeholder="" name="nom" value="${sessionScope.user.nom}">
								</div>
							</div>

							<div class="form-row">
								<div class="col form-group">
									<label>Prenom </label> <input type="text" class="form-control"
										placeholder="" name="prenom" value="${sessionScope.user.prenom}">
								</div>
								<div class="col form-group">
									<label>Email</label> <input type="email" class="form-control"
										placeholder="" name="email" required value="${sessionScope.user.email}"
										maxlength="50" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
								</div>
							</div>
							
				
							<div class="form-row">
								<div class="col form-group">
									<label>Téléphone </label> <input type="text"
										class="form-control" placeholder="" name="tel"
										value="${sessionScope.user.telephone}"
										pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}">
								</div>					
								<div class="col form-group">
									<label>Rue</label> <input type="text" class="form-control"
										placeholder=" " name="rue" value="${sessionScope.user.rue}" maxlength="50">
								</div>
						
							</div>
					

							<div class="form-row">
								<div class="col form-group">
									<label>Code Postal </label> <input type="text"
										class="form-control" placeholder="" name="codePostal"
										value="${user.codePostal}" max="99999" pattern="[0-9]{5}">
								</div>
								<div class="col form-group">
									<label>Ville</label> <input type="text" class="form-control"
										placeholder="" name="ville" value="${sessionScope.user.ville}">
								</div>
							</div>

							<div class="form-row">
								<div class="col form-group">
									<label>Mot de passe actuel</label> <input class="form-control inputActualPass"
										type="password" name="actual_pass" maxlength="30" placeholder="Laissez-vide pour garder le même"
										value="">
								</div>

							</div>

							<div class="form-row">
								<div class="col form-group">
									<label>Nouveau mot de passe</label> <input class="form-control"
										type="password" name="new_pass" maxlength="30" value="">
								</div>
								<div class="col form-group">
									<label>Confirmation</label> <input class="form-control"
										type="password" name="confirm_pass" maxlength="30" value="">
								</div>
								<input type="hidden" name="credit" value="${user.credit}" />
							</div>
				</div>
				
				<div class="row divBtn">
					<button type="submit" class="btn btn-sm btn-outline-secondary btnCustom" value="valider">Enregistrer les modifications</button>
					<button class="btn btn-outline-secondary btnCustom" >
					 <a href="${pageContext.request.contextPath}/ServletSuppressionCompte"  onclick="if (!confirm('Etes-vous sûr(e) de supprimer votre compte ? Cela entrainement la suppression de toutes vos enchères et articles en vente.')) return false;">Supprimer mon compte</a></button>
					<button class="btn btn-outline-primary btnCustom" name="choix"><a href="${pageContext.request.contextPath}/ServletAffichageProfil">Retour</a></button>
		
					</form>
				</div>
				
			</div>
		</div>


		<%@ include file="../includes/footer.html"%>
</body>