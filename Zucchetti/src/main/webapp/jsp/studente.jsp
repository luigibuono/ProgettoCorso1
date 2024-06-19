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
<body background="https://dldi04hn0dos6.cloudfront.net/wp-content/uploads/2022/04/Come-scegliere-luniversita-e1650631604790.jpg" class="d-flex justify-content-center align-items-center vh-100">
    <div align="center" class="p-5 bg-primary-trasparent text-white border" style="background-color: rgba(128, 128, 128, 0.7);">
        <% String matricola = (String) session.getAttribute("matricola"); %>
        <% ResultSet res = (ResultSet) request.getAttribute("tabella_corso"); %>
        <% ResultSet res1 = (ResultSet) request.getAttribute("elenco_appelli"); %>
        <% String materia = (String) request.getAttribute("materia"); %>
        <% String messaggio = (String) request.getAttribute("messaggio"); %>
        <% String data = (String) request.getAttribute("data"); %>
        <% String AppPrenotato = (String) request.getAttribute("app_prenotato"); %>
        <% String error = (String) request.getAttribute("error"); %>
        
        <p>Benvenuto studente: <%= matricola %></p>
        <a href="jsp/logout.jsp">Logout</a>

        <% if (messaggio != null) { %>
            <p><%= messaggio %></p>
        <% } %>

        <% if (error != null) { %>
            <div style="color: red;"><%=error%></div>
            <button align="center" class="btn btn-light" type="button">
                <a href="jsp/logout.jsp">Logout</a>
                <a href="jsp/formstudente.jsp">Indietro</a>

            </button>
        <% } %>


            
        <% if (res != null) { %>
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
                Inserisci la prenotazione che vuoi effettuare <input type="number" name="materia" class="form-control mb-2" required>
                <input type="submit" value="Prenota" class="btn btn-primary">
            </form>
        <% } %>

        <% if (res1 != null) { %>
            <p>Per l'esame di <%= materia %> sono disponibili i seguenti appelli:</p>
            <table border="1" class="table table-striped">
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
                Inserisci la prenotazione che vuoi effettuare <input type="number" name="appello" class="form-control mb-2" required>
                <input type="submit" value="Prenota" class="btn btn-primary">
            </form>
        <% } %>

        <% if (data != null ) { %>
            <p>Prenotazione effettuata con successo in data <%= data %> per il corso <%= AppPrenotato %></p>
        <% } %>
    </div>
</body>
</html>
ml>