<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap.css">
<title>Stagiaire</title>
</head>
<body>
	<div class='container'>
		<div class="row">
			<div class="col-sm-12 col-md-12">
				<h1>Formulaire pour rentrer les notes de la session ???</h1>

				<form action="EnregistrerNotes" method="POST">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Nom</th>
								<th>Prenom</th>
								<th>Note</th>
								<c:if test="${ !empty listeErreur }">
									<th>Erreur</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ listeStagiaires }" var="stagiaire">
								<tr>
									<td>${ stagiaire.nom }</td>
									<td>${ stagiaire.prenom }</td>
									<td><input type="text" name="${ stagiaire.nom }"
										maxlength="4" size="6"></td>
									<c:forEach items="${ listeErreur }" var="erreur">
										<c:if test="${ erreur.key == stagiaire.nom }">
											<td>${erreur.value}</td>
										</c:if>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" name="idEvaluation" value="${ idEvaluation }">
					<input type="hidden" name="idSession" value="${ idSession }">
					<input type="submit" value="Sauvegarder">
				</form>
			</div>
		</div>
	</div>
</body>
</html>