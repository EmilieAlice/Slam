<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap.css">
<link rel="stylesheet" href="style.css">
<title>Liste d'évaluation</title>
</head>
<body>
	<div class='container'>
		<div class="row">
			<div class="col-sm-12 col-md-12">
				<c:forEach items="${ listeEvaluation }" var="listeEvaluation"
					varStatus="status">
					<p>
						Remplir les notes pour <a
							href="EnregistrerNotes?evaluation=${ listeEvaluation.idEvaluation }&idSession=${ idSession }">l'évaluation
							${ status.count }</a>
					</p>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>