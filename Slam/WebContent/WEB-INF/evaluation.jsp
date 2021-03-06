<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap.css">
<title>Liste d'�valuation</title>
</head>
<body>
	<div class='container'>
		<div class="row">
			<div class="col-sm-12 col-md-12">
			<h3>Vous pouvez g�rer les �valuations suivantes : </h3>
				<c:forEach items="${ listeEvaluation }" var="listeEvaluation"
					varStatus="status">
					<p>
						Remplir les notes pour <a
							href="EnregistrerNotes?evaluation=${ listeEvaluation.idEvaluation }&idSession=${ idSession }">l'�valuation
							${ status.count }</a> / Consulter les statistiques pour <a href="StatistiquesEvaluation?evaluation=${ listeEvaluation.idEvaluation}&idSession=${ idSession }" >l'�valuation
							${ status.count }</a>
					</p>
				</c:forEach>
				<form action="ListeEvaluation" method="POST">
				<input type="submit" value="Ajouter une �valuation">
				</form>
			</div>
		</div>
	</div>
</body>
</html>