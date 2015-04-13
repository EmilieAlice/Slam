<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Modification informations personnelles</title>
</head>
<body>
<h2 id="titre">Modification informations personnelles</h2>
	<fieldset>
		<form method="POST" action="/Slam/MettreAjourInfoUtilisateur">
			<table>
				<tr>
					<td>*</td>
					<td>Civilité :</td>
					<td><label>${utilisateur.civilite}</label></td>
				</tr>
				<tr>
					<td>*</td>
					<td>Prénom :</td>
					<td><input type="text" name="prenom" value="${utilisateur.prenom}" readonly="true"></td>
					<c:if test="${msgPrenom != null}">
						<td class="erreur">${msgPrenom}</td>
					</c:if>
				</tr>
				<tr>
					<td>*</td>
					<td>Nom :</td>
					<td><input type="text" name="nom" value="${utilisateur.nom}" readonly="true"></td>
					<c:if test="${msgNom != null}">
						<td class="erreur">${msgNom}</td>
					</c:if>
				</tr>
				<tr>
					<td></td>
					<td>Adresse :</td>
					<td><input type="text" name="adresse" value="${utilisateur.adresse}"></td>
				</tr>
				<tr>
					<td></td>
					<td>Code Postal :</td>
					<td><input type="text" name="cp" value="${utilisateur.codePostal}"></td>
				</tr>
				<tr>
					<td></td>
					<td>Ville :</td>
					<td><input type="text" name="ville" value="${utilisateur.ville}"></td>
				</tr>
				<tr>
					<td></td>
					<td>Téléphone 1 :</td>
					<td><input type="text" name="telephone" value="${utilisateur.telephone}"></td>
				</tr>
				<tr>
					<td></td>
					<td>Téléphone 2 :</td>
					<td><input type="text" name="telephone2" value="${utilisateur.telephone2}"></td>
				</tr>
				<tr>
					<td>*</td>
					<td>Email :</td>
					<td><input type="text" name="email" value="${utilisateur.email}"></td>
					<c:if test="${msgEmail != null}">
						<td class="erreur">${msgEmail}</td>
					</c:if>
				</tr>
				<c:if test="${!changePwd}">
				<tr>
					<td/>
					<td><button type="submit" name="enablePwdChangeBtn" value="Modifier le mot de passe">Modifier le mot de passe</button></td>
				</tr>
				</c:if>
				<c:if test="${changePwd}">
				<tr>
					<td>*</td>
					<td>Ancien mot de passe :</td>
					<td><input type="password" name="anc_motDePasse" required></td>
					<c:if test="${msgMotDePasse != null}">
						<td class="erreur">${msgMotDePasse}</td>
					</c:if>
				</tr>
				<tr>
					<td>*</td>
					<td>Nouveau mot de passe :</td>
					<td><input type="password" name="nouv_motDePasse" required></td>
					<c:if test="${msgMotDePasse != null}">
						<td class="erreur">${msgMotDePasse}</td>
					</c:if>
				</tr>
				<tr>
					<td>*</td>
					<td>Confirmer le mot de passe :
					</td>
					<td><input type="password" name="conf_motDePasse" required></td>
					<c:if test="${msgMotDePasse != null}">
						<td class="erreur">${msgMotDePasse}</td>
					</c:if>
				</tr>
				</c:if>
				<tr><td></td></tr>
				<tr><td></td></tr>
				<tr>
					<td/>
					<td><button type="submit">Enregistrer</button></td>
				</tr>
				<tr>
					<td/>
					<td>* Champ obligatoire</td>
				</tr>
			</table>
		</form>

	</fieldset>
</body>
</html>