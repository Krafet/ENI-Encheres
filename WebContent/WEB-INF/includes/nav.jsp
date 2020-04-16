<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar justify-content-between">
	<a class="navbar-brand"  href="${pageContext.request.contextPath}/Index"><h3>ENI-Ench�res</h3></a>
	<c:if test="${displayNav == null}">
		<form class="form-inline bg-light">		
				<nav class="navbar navbar-expand-sm  mb-3">
					<div class="container align-self-center">
						<c:choose>
							<c:when test="${not empty User}">
								<a class="nav-link" href="#">Ench�res</a>
								<a class="nav-link" href="${pageContext.request.contextPath}/ServletVenteArticles">Vendre un article</a>
								<a class="nav-link" href="${pageContext.request.contextPath}/ServletAffichageProfil">Mon profil</a>
								<a class="nav-link btn-danger" href="${pageContext.request.contextPath}/ServletDeconnexion">D�connexion</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" href="${pageContext.request.contextPath}/ServletConnexion">S'inscrire - Se connecter</a>
							</c:otherwise>
						</c:choose>
					</div>
				</nav>
		</form>
	</c:if>	
</nav>


