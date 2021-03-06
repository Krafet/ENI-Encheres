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
			<div class="col-md-6">
			
			<div class="divConnetionTitle"><h6 class="text-center connectionTitle">Connexion</h6></div>
				
				<div class="card cardConnect">
					<article class="card-body">
				<!--  Gestion des erreurs -->
				<c:if test="${!empty listeCodesErreur}">
					<div class="alert alert-danger alertConnection text-center" role="alert">
						<ul class="ulErrors">
							<c:forEach var="code" items="${listeCodesErreur}">
								<li>${LecteurMessage.getMessageErreur(code)}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
						<form action="${pageContext.request.contextPath}/ServletConnexion"
							method="post">

							<div class="form-row">
								<label for="login">Login</label> <input id="login" type="text"
									class="form-control" placeholder="" name="login"
									value="${user.pseudo}" maxlength="30" />
							</div>
							<div class="form-row">
								<label for="pass">Mot de passe</label> <input id="pass"
									type="password" class="form-control" placeholder="" name="pass"
									value="${user.nom}">

							</div>
							<div class="row divButtonsConnection">
								<div
									class="col-sm-3 col-md-5 offset-sm-1 offset-md-6 offset-lg-0">
									<a class="" href="">
										<button type="submit" class="btn btn-outline-primary btnCustom">Connexion</button>
									</a>
								</div>
								<div class="col-sm-3 col-md-7 offset-md-7 offset-lg-0">
									<div class="row">
										<div class="col-sm-12 col-md-12 checkbox">
											<label> <input type="checkbox" name="rememberUser">&nbsp;Se souvenir de moi</label>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12 col-md-12">
											<a class="passForgot" href="#">Mot de passe oublié</a> <!-- TODO*** : traitement à faire -->
										</div>
									</div>
								</div>
							</div>
						</form>

					</article>
				</div>
				<div class="row btnCreateAccount ">
					<div class="col-sm-12">
						<a class="" href="${pageContext.request.contextPath}/ServletInscription">
							<button type="button" class="btn btn-outline-primary btnCustom"
								style="width: 100%;">Créer un compte</button>
						</a>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
<%@ include file="../includes/footer.html"%>
</html>