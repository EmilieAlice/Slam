<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Agriotes - Ecran de connexion</title>
    </head>
<body>
	<form method="POST" action="/Slam/login">
  <table>
  	<tr>
	    <c:if test="${msgErreur != null}">
	    	<td><label>Login ou mot de passe incorrecte !</label></td>
	    </c:if>
	</tr>
	<tr>
  		<td><label for="login_id" required>Identifiant : </label></td>
  		<td><input id="login_id" name="login" type="text" required></td>
  	</tr>
  	<tr>
  		<td><label for="pwd_id" required>Mot de passe :</label></td>
  		<td><input id="pwd_id" name="password" type="password"  required></td>
  	</tr>
  	<tr>
  		<td><button type="submit" >Se connecter</button></td>
  	</tr>
  </table>
  
  </form>
</body>
</html>