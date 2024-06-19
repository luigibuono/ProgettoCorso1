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
				<a class="navbar-brand " href="#" style="color: black; font-size:1.5rem;font-weight: 700 ">ZUCCHETTI</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon" style="color: black;"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-end"
					id="navbarSupportedContent">
					<ul class="navbar-nav mb-2 mb-lg-0">
						 <li class="nav-item">
                       <a class="nav-link active" aria-current="page" href="/Zucchetti/html/home.html" style="color: black; font-size: 1rem;font-weight: 500">HOME</a>
                      
                    </li>
					</ul>
					<div class="search-add">
						<!-- Eventuali contenuti della sezione "search-add" -->
					</div>
					<a class="nav-link active logout"><i
						class="fa-solid fa-arrow-right-from-bracket" 
						style="cursor: pointer; display:none" ></i></a>
				</div>
			</div>
		</nav>
	<div class="d-flex justify-content-center align-items-center vh-100">

		<div align="center"
			class="p-5 bg-primary-trasparent text-white border"
			style="background-color: rgba(128, 128, 128, 0.7);">
			<form action="login" method="post">
				<h3>inserisci nome utente</h3>
				<input type="text" name="username">
				<p>
				<h3>inserisci password</h3>
				<input type="password" name="password">
				<p>
					<input type="submit" value="Accedi">
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
			</form>
		</div>
	</div>
</body>
</html>
