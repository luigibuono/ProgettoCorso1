<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <meta charset="ISO-8859-1">
    <title>Area Studenti</title>
</head>
<body background="https://dldi04hn0dos6.cloudfront.net/wp-content/uploads/2022/04/Come-scegliere-luniversita-e1650631604790.jpg" >
   
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
		
    <div class="container-fluid py-5" style="background-color: rgba(128, 128, 128, 0.7);">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card border-primary">
                <div class="card-body">
                    <% String matricola = (String) session.getAttribute("matricola"); %>
                    <% ResultSet res = (ResultSet) request.getAttribute("tabella_corso"); %>
                    <% ResultSet res1 = (ResultSet) request.getAttribute("elenco_appelli"); %>
                    <% String materia = (String) request.getAttribute("materia"); %>
                    <% String messaggio = (String) request.getAttribute("messaggio"); %>
                    <% String data = (String) request.getAttribute("data"); %>
                    <% String AppPrenotato = (String) request.getAttribute("app_prenotato"); %>
                    <% String error = (String) request.getAttribute("error"); %>
                    
                    <h4 class="card-title text-center mb-4">Benvenuto studente: <%= matricola %></h4>
                    <div class="d-flex justify-content-center text-center">
                    <a href="/Zucchetti/jsp/logout.jsp" class="btn btn-danger ">Logout</a>
					</div>
                    <% if (messaggio != null) { %>
                        <p class="mt-3"><%= messaggio %></p>
                    <% } %>

                    <% if (error != null) { %>
                        <div class="alert alert-danger mt-3"><%= error %></div>
                        <div class="d-flex justify-content-center mt-3">
                            <a href="/Zucchetti/jsp/logout.jsp" class="btn btn-danger me-2">Logout</a>
                            <a href="/Zucchetti/jsp/formstudente.jsp" class="btn btn-secondary">Indietro</a>
                        </div>
                    <% } %>

                    <% if (res != null) { %>
                        <div class="mt-4">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>ID corso</th>
                                        <th>Materia</th>
                                        <th>Nome docente</th>
                                        <th>Cognome docente</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% while (res.next()) { %>
                                        <tr>
                                            <td><%= res.getInt("idcorso") %></td>
                                            <td><%= res.getString("materia") %></td>
                                            <td><%= res.getString("nome") %></td>
                                            <td><%= res.getString("cognome") %></td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>

                            <form action="Prenotazione" method="post">
                                <div class="mb-3">
                                    <label for="materia" class="form-label">Inserisci la prenotazione che vuoi effettuare</label>
                                    <input type="number" name="materia" id="materia" class="form-control" required>
                                </div>
                                <div class="d-flex justify-content-center text-center">
                                <button type="submit" class="btn btn-primary">Prenota</button>
                                </div>
                            </form>
                        </div>
                    <% } %>

                    <% if (res1 != null) { %>
                        <div class="mt-4">
                            <p>Per l'esame di <%= materia %> sono disponibili i seguenti appelli:</p>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>ID Appello</th>
                                        <th>Data</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% while (res1.next()) { %>
                                        <tr>
                                            <td><%= res1.getInt(1) %></td>
                                            <td><%= res1.getDate("Data") %></td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>

                            <form action="Prenota" method="post">
                                <div class="mb-3">
                                    <label for="appello" class="form-label">Inserisci la prenotazione che vuoi effettuare</label>
                                    <input type="number" name="appello" id="appello" class="form-control" required>
                                </div>
                                <div class="d-flex justify-content-center text-center">
                                <button type="submit" class="btn btn-primary">Prenota</button>
                                </div>
                            </form>
                        </div>
                    <% } %>

                    <% if (data != null) { %>
                        <p class="mt-4">Prenotazione effettuata con successo in data <%= data %> per il corso <%= AppPrenotato %></p>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
    
</body>
</html>
