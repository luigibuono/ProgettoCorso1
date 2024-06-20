
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body
	background=https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?q=80&w=2072&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D>

	<nav class="navbar navbar-expand-lg navbar-dark ">
		<div class="container-fluid">
			<a class="navbar-brand " href="#"
				style="color: black; font-size: 1.5rem; font-weight: 700">ZUCCHETTI</a>
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
						aria-current="page" href="/Zucchetti/html/home.html"
						style="color: black; font-size: 1rem; font-weight: 500">HOME</a></li>
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

<div class="d-flex justify-content-center align-items-center vh-100">
	<div
		class="container-fluid p-5 bg-primary-transparent text-white border"
		style="background-color: rgba(128, 128, 128, 0.7); width: 30rem;">
		<%-- Controllo se le variabili di sessione sono presenti --%>
		<%
    String nome = (String) session.getAttribute("nome");
    String cognome = (String) session.getAttribute("cognome");
    String materia = (String) session.getAttribute("materia");
    ResultSet appelli = (ResultSet) request.getAttribute("appelli");
    ResultSet elenco = (ResultSet) request.getAttribute("elenco_studenti");
    String nomeMateria = (String) request.getAttribute("Materia");
    String Data = (String) request.getAttribute("Data");
    String error = (String) request.getAttribute("error");

    if (nome == null && cognome == null) {
        response.sendRedirect("/Zucchetti/jsp/index.jsp");
    }
    %>
		<div class="d-flex justify-content-center text-center">
			<p style="font-size:1.5rem; font-weight: 700">
				Bentornato
				<%= nome %>
				<%= cognome %></p>
		</div>

		<%-- Pulsante Indietro con Bootstrap --%>
		<div class="d-flex justify-content-center text-center">
			<button class="btn btn-light mb-3">
				<a href="/Zucchetti/jsp/index.jsp"
					class="text-decoration-none text-dark">Indietro</a>
			</button>
		</div>
		<%-- Visualizzazione dell'errore --%>
		<% if (error != null) { %>
		<div style="color: red;"><%= error %></div>
		<% } %>

		<%-- Visualizzazione degli appelli --%>
		<% if (appelli != null) { %>
		<p>
			Per la sua materia
			<%= materia %>
			sono disponibili i seguenti appelli:
		</p>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID Appello</th>
					<th>Data</th>
				</tr>
			</thead>
			<tbody>
				<% while (appelli.next()) { %>
				<tr>
					<td><%= appelli.getInt(1) %></td>
					<td><%= appelli.getDate("Data") %></td>
				</tr>
				<% } %>
			</tbody>
		</table>

		<%-- Form per la selezione dell'appello --%>
		<form action="StampaStudenti" method="post">
			<div class="mb-3">
				<label for="ID_appello" class="form-label">Seleziona l'ID
					Appello</label> <input type="number" name="ID_appello" class="form-control"
					required>
			</div>
			<div class="d-flex justify-content-center text-center">
				<button type="submit" class="btn btn-primary">Vai</button>
			</div>
		</form>
		<% } %>

		<%-- Visualizzazione dell'elenco degli studenti --%>
		<% if (elenco != null) { %>
		<p>
			Per l'esame in data
			<%= Data %>
			si sono prenotati i seguenti studenti:
		</p>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Matricola</th>
				</tr>
			</thead>
			<tbody>
				<% while (elenco.next()) { %>
				<tr>
					<td><%= elenco.getString("nome") %></td>
					<td><%= elenco.getString("cognome") %></td>
					<td><%= elenco.getString("Matricola") %></td>
				</tr>
				<% } %>
			</tbody>
		</table>
		<% } %>
	</div>
</div>
</body>
<style>
    .nav-item a.nav-link:hover {
        background-color: #dc3545 !important;
    }
</style>
</html>