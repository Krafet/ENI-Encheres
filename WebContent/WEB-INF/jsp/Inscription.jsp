<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/includes/nav.jsp"></jsp:include>

<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>

<body class="container">
	<div class="container">

		<div class="form-style-10">
			<h1>
				S'inscrire maintenant !<span>Inscrivez vous et profitez de
					toutes les fonctionalités de notre site</span>
			</h1>

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

			<form method="post" action="${pageContext.request.contextPath}/ServletInscription">
			    <div class="section"><span>1</span>Informations personnelles</div>
			    <div class="inner-wrap">
			        <label>Pseudo <input type="text" id="pseudo" name="pseudo" value="test" required/></label>
			        <label>Nom  <input type="text" id="nom" name="nom" value="test" required/></label>
			        <label>Prénom  <input type="text" id="prenom" name="prenom" value="test" required/></label>
			        <label>Email  <input type="text" id="email" name="email"  value="test@t.com"required/></label>
			        <label>Téléphone  <input type="text" id="telephone" name="telephone" value="0613523078" pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}"/></label>
			        <label>Rue  <input type="text" id="rue" name="rue" value="test" required/></label>
			        <label>Code postal  <input type="text" id="codePostal" name="codePostal" value="35000" max="99999" pattern="[0-9]{5}" required/></label>
			        <label>Ville  <input type="text" id="ville" name="ville" value="test" required/></label>
			    </div>
			
			    <div class="section"><span>2</span>Sécurité</div>
			        <div class="inner-wrap">
			        <label>Mot de passe <input type="password" id="mdp" name="mdp" value="test" required/></label>
			        <label>Confirmation <input type="password" id="mdpConfirmation"value="test"  name="mdpConfirmation" required/></label>
			    </div>
			    <div class="button-section">
				     <input type="submit" Value="Valider"/>
				     <span class="privacy-policy">
				    	 <input type="checkbox" name="field7">You agree to our Terms and Policy. 
				     </span>
			    </div>
			</form>
		</div>
	</div>
	<%@ include file="../includes/footer.html"%>
</body>
</html>