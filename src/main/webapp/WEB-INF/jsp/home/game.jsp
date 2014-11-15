<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Score CDI</title>

</head>
<body>
	<h4>Sessão atual ${cid}</h4>
	<p>Quanto é ${primeiro} * ${segundo}?</p>
	<form action="${linkTo[HomeController].game}?cid=${cid}" method="POST">
		<input name="resposta" type="number" required>
		<button type="submit">Continuar jogo!</button>
	</form>
	
	<a href="${linkTo[HomeController].fim}?cid=${cid}">Finalizar!</a>

</body>
</html>