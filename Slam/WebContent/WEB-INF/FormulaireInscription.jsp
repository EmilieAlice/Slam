<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Inscription au site</title>
</head>
<body>
	<h2 id="titre">Merci de remplir ce formulaire pour vous inscrire
		sur notre site</h2>
	<fieldset>
		<form method="POST" action="">
			<table>
				<tr>
					<td>*</td>
					<td>Civilit� :</td>
					<td><select name="civilite">
							<option>M.</option>
							<option>Mme</option>
							<option>Mle</option>
					</select></td>
				</tr>
				<tr>
					<td>*</td>
					<td>Pr�nom :</td>
					<td><input type="text" name="prenom"></td>
					<c:if test="${msgPrenom != null}">
						<td class="erreur">${msgPrenom}</td>
					</c:if>
				</tr>
				<tr>
					<td>*</td>
					<td>Nom :</td>
					<td><input type="text" name="nom"></td>
					<c:if test="${msgNom != null}">
						<td class="erreur">${msgNom}</td>
					</c:if>
				</tr>
				<tr>
					<td></td>
					<td>Adresse :</td>
					<td><input type="text" name="adresse"></td>
				</tr>
				<tr>
					<td></td>
					<td>Code Postal :</td>
					<td><input type="text" name="cp"></td>
				</tr>
				<tr>
					<td></td>
					<td>Ville :</td>
					<td><input type="text" name="ville"></td>
				</tr>
				<tr>
					<td></td>
					<td>T�l�phone 1 :</td>
					<td><input type="text" name="telephone"></td>
				</tr>
				<tr>
					<td></td>
					<td>T�l�phone 2 :</td>
					<td><input type="text" name="telephone2"></td>
				</tr>
				<tr>
					<td>*</td>
					<td>Email :</td>
					<td><input type="text" name="email"></td>
					<c:if test="${msgEmail != null}">
						<td class="erreur">${msgEmail}</td>
					</c:if>
				</tr>
				<tr>
					<td>*</td>
					<td>Mot de passe :</td>
					<td><input type="password" name="motDePasse"></td>
					<c:if test="${msgMotDePasse != null}">
						<td class="erreur">${msgMotDePasse}</td>
					</c:if>
				</tr>
				<tr>
					<td>*</td>
					<td>Confirmation<br />de mot de passe :
					</td>
					<td><input type="password" name="motDePasse"></td>
				</tr>
			</table>
			<br />
			<button type="submit">Validez votre inscription</button>
			<br />
			<br /> * Champ obligatoire
		</form>

	</fieldset>

</body>
</html>