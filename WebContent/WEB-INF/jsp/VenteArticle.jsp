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
		<div class="divConnetionTitle"><h6 class="text-center venteTitle">Nouvelle vente</h6></div>
		<div class="card cardVente">
		<div class="card-body">
			<div class="row">
				<div class="col-sm-3">
					<div id="imageVente">
						<img id="imageArticle"class="imgArticle" id="" src="${pageContext.request.contextPath}/img/close2.png" alt="image" />
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-8">		
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
						<form class="formArticle" action="${pageContext.request.contextPath}/ServletVenteArticles" method="post">
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Article :<br></label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input type="text" name="nomArticle" class="form-control" required value="test">
							</div>
						</div>
							<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Description :<br></label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<textarea name="description" class="form-control noResize" required>test</textarea>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Categorie : </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<select name="categorie" class="form-control">
									<c:forEach items="${categories}" var="categorie">
										<option value="${categorie.noCategorie}">${categorie.libelle}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Photo de l'article</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input type="file" onchange="changePicture(this)" name="file" id="file"/>
 								<!-- <label class="custom-file-label" for="inputGroupFile01">Choose file</label> -->
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Mise à prix : </label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input class="form-control" value="1" type="number" id="prix" name="prix" min="1" required >	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Début de l'enchère :</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input class="form-control"  type="date" name="debutEnchere" onkeydown="return false" id="start" name="trip-start" value="${today}" min="${today}" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-md-3 col-lg-3">Fin de l'enchère :</label>
							<div class="col-sm-6 col-md-9 col-lg-8">
								<input class="form-control"  type="date" name="finEnchere" onkeydown="return false" id="start" name="trip-start" value="" min="${today}" required>
							</div>
						</div>
						<fieldset class="border p-2">
							<legend class="w-auto">Retrait</legend>						
							<div class="form-group row">
								<label class="col-sm-3">Rue :</label>
								<div class="col-sm-9 col-md-9 col-lg-9">
									<input type="text" value="${sessionScope.user.rue}" name="rue" id="rue" class="form-control" required>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-3">Code postal :</label>
								<div class="col-sm-9 col-md-9 col-lg-9">
									<input type="text" value="${sessionScope.user.codePostal}" name="codePostal" id="codePostal" class="form-control" required >
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-3">Ville :</label>
								<div class="col-sm-9 col-md-9 col-lg-9">
									<input type="text" value="${sessionScope.user.ville}" name="ville" id="ville" class="form-control" required>
								</div>
							</div>							
						</fieldset>
						<div class="row divbtnVente profileButtons">
							<div class="col-sm-4">
								<a class="btn btn-lg" ><button type="submit" class="btn btn-outline-secondary btnCustom">Enregistrer</button></a>
							</div>
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
   <script type="text/javascript" async>
 
   		//Fonction JS permettant d'actualiser la photo choisie
		function changePicture(input) {	
			$("#imageVente").show();	
			console.log(input);
			console.log(input.files);
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();		
		        reader.onload = function (e) {
		            $('#imageArticle').attr('src', e.target.result);
		            $('#imageArticle').attr('class', "imgUploaded");
		        };		        
		        reader.readAsDataURL(input.files[0]);
		    }
		}
	</script>
	<%@ include file="../includes/footer.html"%>
</body>
</html>