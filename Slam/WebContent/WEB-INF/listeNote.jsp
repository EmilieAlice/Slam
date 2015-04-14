<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap.css">

<title>Liste de vos notes</title>
</head>
<body>
	<div class='container'>
		<div class="row">
			<div class="col-sm-12 col-md-12">
				<c:choose>
					<c:when test="${ !empty verifStagiaire }">
						<h1>Vous n'etes pas stagiaire, vous n'avez donc pas
							d'évaluation</h1>
					</c:when>

					<c:when test="${ empty verifStagiaire }">
						<h1>Voici votre bulletin de notes</h1>
						<br>
						<h3>Les résultats des différentes évaluations</h3>
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Matière</th>
									<c:forEach var="i" begin="1" end="${nbreMaxEvaluations}"
										step="1">
										<th>Eval ${i}</th>
									</c:forEach>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listeDeModule}" var="module"
									varStatus="status">
									<tr>
										<c:forEach items="${listeDeNotesParModule}" var="hashmapNote"
											varStatus="status">

											<c:if test="${module.nomModule == hashmapNote.key.nomModule }">

												<td><c:out value="${hashmapNote.key.nomModule}" /></td>
												<c:forEach items="${hashmapNote.value}" var="note"
													varStatus="status">
													<td><c:out value="${note}"></c:out></td>
												</c:forEach>
											</c:if>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<p>
						<h3>Les moyennes par matières</h3>

						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Matière</th>
									<th>Moyenne</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listeDeModule}" var="module"
									varStatus="status">
									<tr>
										<c:forEach items="${listeDesMoyennesParModule}"
											var="hashmapMoyenne" varStatus="status">
											<c:if test="${module.nomModule == hashmapMoyenne.key.nomModule }">
												<td><c:out value="${hashmapMoyenne.key.nomModule}" /></td>
												<td><c:out value="${hashmapMoyenne.value}" /></td>
											</c:if>

										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<p>Votre moyenne générale est : ${moyenneGenerale }</p>
					</c:when>

				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>