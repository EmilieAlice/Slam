<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Nouvelle candidature</title>
</head>
<body>
	<div id="contenu">
		<header>
			<div class="logo"><a href="index.html"><img src="images/logo.png"></a></div>
			<div class="slogan"><img src="images/slogan.png" width="90%"></div>
			<h3>Bonjour ${user.civilite} </h3>
		</header>
		<p class="phrase">Bienvenue sur votre candidature en ligne Agriotes.
		Cette  interface vous permet de compléter en ligne votre dossier de  candidature et ainsi de valider votre inscription 
		au concours d'admission.</p>
		<!--==================================form===================================-->
		<div id="formulaire">
	
			<form name="f1" method="POST" action="">
				<p>
					<label for="session">La session</label> <span> <select
						id="session" name="session">
							<c:forEach items="${sessions}" var="session">
								<option value="${session.key}">${session.value}</option>
							</c:forEach>
					</select>
					</span>
				</p>
	
				<p>
					<label for="cv">Votre CV</label> <span> <input type="file"
						id="cv" name="cv" class="cv" />
	
					</span>
					<c:if test="${msgCv != null}">
						<span class="erreur">${msgCv}</span>
					</c:if>
				</p>
	
				<p>
					<label for="lm">Votre LM</label> <span> <input type="file"
						id="lm" name="lm" class="lm" />
	
					</span>
					<c:if test="${msgLm != null}">
						<span class="erreur">${msgLm}</span>
					</c:if>
				</p>
	
	
				<p>
					<label for="msg">Votre message</label> <span> <textarea
							rows="7" cols="35" id="msg" name="msg"></textarea>
					</span>
				</p>
				<p>
					<input type="submit" name="submit" value="Envoyer" class="button1" />
					<input type="reset" value="Annuler" class="button2" />
				</p>
	
	
			</form>
	
		</div>
		<!--formulaire-->
	
	
		<!--==============================================/form===============================-->
	
		<!-- End Form -->
	</div>
	<footer>
		Copyright © 2015. agriotes.fr - réalisé par Jamal Lasri.
	</footer>
</body>
</html>