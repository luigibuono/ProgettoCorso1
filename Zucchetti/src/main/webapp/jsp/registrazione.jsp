<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<title>Benvenuto</title>
</head>
<body
	background=https://images.unsplash.com/photo-1523050854058-8df90110c9f1?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D>

	<nav class="navbar navbar-expand-lg navbar-dark ">
		<div class="container-fluid">
			<a class="navbar-brand " href="#"
				style="color: black; font-size: 1.5rem; font-weight: 700"><i class="fa-solid fa-book"></i> ZUCCHETTI</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon" style="color: black;"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarSupportedContent">
				<ul class="navbar-nav mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/Zucchetti"
						style="color: black; font-size: 1rem; font-weight: 500">HOME</a></li>
						
						<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/Zucchetti/jsp/registrazione.jsp"
						style="color: black; font-size: 1rem; font-weight: 500">Registrati</a></li>
				</ul>
				<div class="search-add">
					<!-- Eventuali contenuti della sezione "search-add" -->
				</div>
				<a class="nav-link active logout"><i
					class="fa-solid fa-arrow-right-from-bracket"
					style="cursor: pointer; display: none"></i></a>
			</div>
		</div>
	</nav>

		<div class="banner">
			<div class="content p-5  bg-primary-trasparent text-white border" style="background-color: rgba(128, 128, 128, 0.7);">
				<h2>BENVENUTO IN UNIVERSITA</h2>
				<p>Scegli il corso che fa per te!</p>
			</div>
		</div>
		
	<div class="d-flex justify-content-center align-items-center vh-100">

		<div align="center"
			class="p-5 bg-primary-trasparent text-white border"
			style="background-color: rgba(128, 128, 128, 0.7); margin-top:15rem;">
			<form action="Registrazione" method="post">
			<h2 class="m-2 p-2" style="background-color: rgba(0, 114, 188, 0.87);">REGISTRAZIONE</h2>
				<h3>Inserisci nome </h3>
				<input type="text" name="nome" placeholder="nome">
				<h3>Inserisci cognome </h3>
				<input type="text" name="cognome" placeholder="cognome">
				<h3>Inserisci username </h3>
				<input type="text" name="username" placeholder="username">
				<p>
				<h3>Inserisci password</h3>
				<input type="text" name="password" placeholder="password">
				<p class="mt-4">
					<input type="submit" value="Registrati">
				<p>
					<%
					String messaggio = (String) request.getAttribute("messaggio");
					%>
					<%
					if (messaggio != null) {
					%>
				
				<p align="center">
					<a style="font-family: helvetica; color: red; font-size: 20px">
						<%
						out.print(messaggio);
						%>
					</a>
					<%-- si poteva fare anche con l'espressione <%=messaggio%> --%>
				</p>
				<%
				}
				%>
				<p class="mt-4">
					Non sei ancora registrato?<a href="">Registrati qui!</a>
				<p>
			</form>
		</div>
	</div>
	<footer class="text-center bg-body-tertiary">

		<!-- Copyright -->
		<div class="text-center p-4"
			style="background-color: rgba(0, 0, 0, 0.05);">
			© 2024 Copyright: <a class="text-body"
				href="">Team ItConsulting</a>
		</div>

	</footer>
</body>
<style>
.nav-item a.nav-link:hover {
	background-color: #dc3545 !important;
}

.banner .content {
    position: absolute;
    top: 20%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    color: #fff;
    z-index: 3;
    opacity: 0; 
    animation: fadeIn 3s; 
}

.banner h1 {
    font-size: 2em;
    margin: 0;
}

.banner p {
    font-size: 1.2em;
    margin: 0;
}

  @keyframes fadeIn {
        0% { opacity: 0; }
        100% { opacity: 1; }
    }

</style>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        setTimeout(function() {
            document.querySelector(".banner .content").style.opacity = "1";
        }, 1000); // Avvia l'animazione dopo 5000 millisecondi (5 secondi)
    });
</script>
</html>
