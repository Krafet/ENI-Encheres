<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
<h1>ENI-Enchères</h1>
<h1>Mon profil</h1>

<form method="post" action="/ENI-Encheres/InscriptionServlet">

<label for="nombre">Pseudo : </label>
	<input type="text" id="pseudo" name="pseudo" required/><br><br>
	
<label for="nombre">Nom : </label>
	<input type="text" id="nom" name="nom" required/><br><br>
	
<label for="nombre">Prénom : </label>
	<input type="text" id="prenom" name="prenom" required/><br><br>
	
<label for="nombre">Email : </label>
	<input type="text" id="email" name="email" required/><br><br>
	
<label for="nombre">Téléphone : </label>
	<input type="text" id="telephone" name="telephone"/><br><br>
	
<label for="nombre">Rue : </label>
	<input type="text" id="rue" name="rue" required/><br><br>

<label for="nombre">Code postal : </label>
	<input type="text" id="codePostal" name="codePostal" required/><br><br>

<label for="nombre">Ville : </label>
	<input type="text" id="ville" name="ville" required/><br><br>

<label for="nombre">Mot de passe : </label>
	<input type="password" id="mdp" name="mdp" required/><br><br>
	
<label for="nombre">Confirmation : </label>
	<input type="password" id="mdpConfirmation" name="mdpConfirmation" required/><br><br>
	
	<input type="submit" value="Valider">
</form>
</body>
</html>