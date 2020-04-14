<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<header class="">
	<div class="container mainTitle">
		<a class="nav-link" href="${pageContext.request.contextPath}/Index"><h3>ENI-Enchères</h3></a>
	        <nav class="navbar navbar-expand-sm navbar-light bg-light mb-3">
	        <div class="container">
	              <a class="nav-link" href="${pageContext.request.contextPath}/ServletInscription">S'inscrire</a>
	              <a class="nav-link" href="${pageContext.request.contextPath}/ServletConnexion">Se Connecter</a>
	              <c:if test="${not empty User }">
			  		<a class="nav-link" href="${pageContext.request.contextPath}/ServletAffichageProfil"> Profil ${User.pseudo} </a>
					<a class="nav-link" href="${pageContext.request.contextPath}/ServletVenteArticles">Vendre un article</a>	  
					<a class="nav-link" href="${pageContext.request.contextPath}/ServletDeconnexion">Déconnexion</a>			 
				</c:if>
	        </div>
		</nav>	</div>
</header>