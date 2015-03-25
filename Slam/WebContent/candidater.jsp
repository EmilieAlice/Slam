<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Nouvelle candidature</title>
</head>
<body>
	<h2>Vous pouvez candidater</h2>

	<!--==================================form===================================-->
	<div id="formulaire">

		<form name="f1" method="POST" action="">

			<p>
				<label for="nom">Votre nom</label> <span> <input type="text"
					id="nom" name="nom" class="nom" />
				</span>
				<c:if test="${msgNom != null}">
					<span class="erreur">${msgNom}</span>
				</c:if>
			</p>

			<p>
				<label for="prenom">Votre prénom</label> <span> <input
					type="text" id="prenom" name="prenom" class="nom" />
				</span>
				<c:if test="${msgPrenom != null}">
					<span class="erreur">${msgPrenom}</span>
				</c:if>
			</p>

			<p>
				<label for="mail">Votre email</label> <span> <input
					type="text" id="mail" name="mail" class="mail" />

				</span>
				<c:if test="${msgMail != null}">
					<span class="erreur">${msgMail}</span>
				</c:if>
			</p>

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
					id="cv" name="cv" class="mail" />

				</span>
				<c:if test="${msgCv != null}">
					<span class="erreur">${msgCv}</span>
				</c:if>
			</p>

			<p>
				<label for="lm">Votre LM</label> <span> <input type="file"
					id="lm" name="lm" class="mail" />

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

</body>
</html>