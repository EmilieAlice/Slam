<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap.css">
<title>Statistiques</title>
</head>
<body>
	<div class='container'>
		<div class="row">
			<div class="col-sm-12 col-md-12">
				<h3>Voici les statistiques pour cette évaluation : </h3>
				<p>- la moyenne est de ${ evaluation.moyenne }</p>

				<p>- la note la plus haute est de ${ evaluation.noteLaPlusHaute }</p>

				<p>- la note la plus basse est de ${ evaluation.noteLaPlusBasse }</p>
				
				<p>Voici la position de cette évaluation : ${rang }/${nbreEvaluations }</p>
			</div>
		</div>
	</div>

</body>
</html>