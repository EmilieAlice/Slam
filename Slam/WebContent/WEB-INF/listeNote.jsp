<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap.css">
<title>Liste de vos notes</title>
</head>
<body>
	<div class="row">
		<c:choose>
			<c:when test="${ !empty verifStagiaire }">
				<h1>Vous n'etes pas stagiaire, vous n'avez donc pas
					d'évaluation</h1>
			</c:when>

			<c:when test="${ empty verifStagiaire }">
				<h1>Voici la liste de vos différentes notes par matières</h1>

				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Matière</th>
							<c:forEach var="i" begin="1" end="${nbreMaxEvaluations}" step="1">
								<th>Eval ${i}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listeDeModule}" var="module"
							varStatus="status">
							<c:forEach items="${listeDeNotesParModule}" var="hashmap"
								varStatus="status">
								<c:if test="${module.nom == hashmap.key.nom }">
									<tr>
										<td><c:out value="${hashmap.key.nom}" /></td>
										<c:forEach items="${hashmap.value}" var="note"
											varStatus="status">
											<td><c:out value="${note}"></c:out></td>
										</c:forEach>
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>

			</c:when>

		</c:choose>
	</div>
</body>
</html>