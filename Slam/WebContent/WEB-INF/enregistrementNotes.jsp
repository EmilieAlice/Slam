<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stagiaire</title>
</head>
<body>
	<h1>Formulaire pour rentrer les notes de la session ???</h1>
	<form action="EnregistrerNotes" method="POST">
		<table>
			<thead>
				<tr>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Note</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ listeStagiaires }" var="stagiaire">
					<tr>
						<td>${ stagiaire.nom }</td>
						<td>${ stagiaire.prenom }</td>
						<td><input type="text" name="${ stagiaire.nom }"
							maxlength="4" size="6"></td>
						<c:if test="${msgNote != null}">
							<td> ${msgNote}</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="hidden" name="idEvaluation" value="${ idEvaluation }">
		<input type="hidden" name="idSession" value="${ idSession }">
		<input type="submit" value="Sauvegarder">
	</form>

</body>
</html>