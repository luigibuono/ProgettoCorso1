
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
	background=https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?q=80&w=2072&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D
	class="d-flex justify-content-center align-items-center vh-100">
	<div align="center" class="p-5 bg-primary-trasparent text-white border"
		style="background-color: rgba(128, 128, 128, 0.7);">
		<%
		String nome = (String) session.getAttribute("nome");
		String cognome = (String) session.getAttribute("cognome");
		String materia = (String) session.getAttribute("materia");
		ResultSet appelli = (ResultSet) request.getAttribute("appelli");
		ResultSet elenco = (ResultSet) request.getAttribute("elenco_studenti");
		String nomeMateria = (String) request.getAttribute("Materia");
		String Data = (String) request.getAttribute("Data");
		String error = (String) request.getAttribute("error");// crearazione variabile errore
		//aggiunta a riga 33/36
		%>
		<%
		if (nome == null && cognome == null) {

			response.sendRedirect("index.jsp");
		}
		%>
		<p>
			Bentornato
			<%=nome%><%=cognome%></p>
		

		<%
		if (error != null) {
		%>
		<div style="color: red;"><%=error%></div>

		<button align="center" class="btn btn-light" type="button">

			<a href="index.jsp"> Indietro </a>
		</button>
		<%
		}
		%>
		<%
		if (appelli != null) {
		%>
		<p>
			Per la sua materia:
			<%=materia%>
			sono disponibili i seguenti appelli
		</p>
		<table border=1>
			<tr>
				<th>ID Appello</th>
				<th>Data</th>
			</tr>
			<%
			while (appelli.next()) {
			%>
			<tr>
				<th><%=appelli.getInt(1)%></th>
				<th><%=appelli.getDate("Data")%></th>
			</tr>
		</table>
		<%
		}
		%>
		<form action="StampaStudenti" method="post">
			<p>
				<input type="number" name="ID_appello">
			</p>
			<p>
				<input type="submit" value="Vai">
			</p>
		</form>
		<%
		}
		%>
		<%
		if (elenco != null) {
		%>

		<p>
			Per l'esame<%=nomeMateria%>
			in data<%=Data%>si sono prenotati i seguenti studenti:
		</p>
		<table border=1>
			<tr>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Matricola</th>
			</tr>
			<%
			while (elenco.next()) {
			%>
			<tr>
				<th><%=elenco.getString("nome")%></th>
				<th><%=elenco.getString("cognome")%></th>
				<th><%=elenco.getString("Matricola") %></th>
				<% }%>
				<%} %>
			</tr>

		</table>
	</div>
</body>
</html>