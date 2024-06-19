<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Corso"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Area Studenti</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body style="background-image: url('https://dldi04hn0dos6.cloudfront.net/wp-content/uploads/2022/04/Come-scegliere-luniversita-e1650631604790.jpg');" class="d-flex justify-content-center align-items-center vh-100">
    <div align="center" class="p-5 bg-primary-transparent text-white border" style="background-color: rgba(128, 128, 128, 0.7);">
        <% String matricola = (String) session.getAttribute("matricola"); %>
        <h2>Benvenuto studente: <%= matricola %></h2>
        <a href="/Zucchetti/jsp/index.jsp">Logout</a>

        <% String success = (String) request.getAttribute("success"); %>
        <% String error = (String) request.getAttribute("error"); %>

        <% if (success != null) { %>
            <div class="alert alert-success"><%= success %></div>
        <% } %>

        <% if (error != null) { %>
            <div class="alert alert-danger"><%= error %></div>
        <% } %>

        <table border="1" class="table table-striped">
            <thead>
                <tr>
                    <th>ID corso</th>
                    <th>Materia</th>
                    <th>Nome docente</th>
                    <th>Cognome docente</th>
                </tr>
            </thead>
            <tbody>
                <% List<Corso> corsi = (List<Corso>) request.getAttribute("tabella_corso"); %>
                <% if (corsi != null) { %>
                    <% for (Corso corso : corsi) { %>
                    <tr>
                        <td><%= corso.getIdCorso() %></td>
                        <td><%= corso.getMateria() %></td>
                        <td><%= corso.getNomeProfessore() %></td>
                        <td><%= corso.getCognomeProfessore() %></td>
                    </tr>
                    <% } %>
                <% } else { %>
                <tr>
                    <td colspan="4">Hai già scelto il corso. puoi cambiare appello</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <form action="Prenotazione" method="post">
            <label for="materia">Inserisci l'ID del corso per la prenotazione:</label>
            <input type="number" name="materia" id="materia" class="form-control mb-2" required>
            <input type="submit" value="Prenota" class="btn btn-primary">
        </form>
    </div>
</body>
</html>
