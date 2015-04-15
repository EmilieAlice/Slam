<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription formateur</title>
</head>
<body>
	<h1>Inscription formateur</h1>
	<fieldset>
		<form method="POST" action="">
			<table>
				<tr>
					<td>*</td>
					<td>Formateur :</td>
					<td><select name="selectFormateur">
							<c:forEach items="${formateurs}" var="formateur">
							<option value="${formateur.idPersonne}">${formateur.nom} - ${formateur.prenom}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>*</td>
					<td>Module :</td>
					<td><select name="selectModule">
							<c:forEach items="${modules}" var="module">
							<option value="${module.idModule}">${module.nomModule}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>*</td>
					<td>Session :</td>
					<td><select name="selectSession">
						<c:forEach items="${sessions}" var="sessionForm">
							<option  value="${sessionForm.idSession}">${sessionForm.nomSession}</option>
						</c:forEach>
					</select></td>
				</tr>
			</table>
			<br />
			<p></p>
			<button type="submit">Enregistrer</button>
			<br /> <br /> * Champ obligatoire
		</form>
</body>
</html>