<%@ page import="java.util.List"%>
<%@ page import="entities.CourseData"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Course List</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css"
	integrity="sha512-Ez0cGzNzHR1tYAv56860NLspgUGuQw16GiOOp/I2LuTmpSK9xDXlgJz3XN4cnpXWDmkNBKXR/VDMTCnAaEooxA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<div class=""
		style="height: 100vh; background-image: url('/Zucchetti/resources/sfondo2.jpg'); background-size: cover;">

		<nav class="navbar navbar-expand-lg navbar-dark ">
			<div class="container-fluid">
				<a class="navbar-brand " href="#"
					style="color: #ffffff; font-size: 1.5rem; font-weight: 700">ZUCCHETTI</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon" style="color: #ffffff;"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-end"
					id="navbarSupportedContent">
					<ul class="navbar-nav mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/Zucchetti/jsp/index.jsp"
							style="color: #ffffff; font-size: 1rem; font-weight: 500">Studenti</a>

						</li>
						<li class="nav-item"><a class="nav-link active"
							href="/Zucchetti/jsp/index.jsp"
							style="color: #ffffff; font-size: 1rem; font-weight: 500">Professori</a>
						</li>
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

		<div class="d-flex justify-content-center align-items-center"
			style="height: 100vh;">
			<div class="card p-4" style="width: 40rem;">
				<h2 class="bg-danger text-white text-center p-2 mb-4">Lista
					Corsi</h2>
					
				<!-- Search Form -->
				 <!-- Modulo di ricerca -->
                <form method="get" action="courseList">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Cerca per ID corso" name="searchId">
                        <button class="btn btn-primary" type="submit">Cerca</button>
                    </div>
                </form>
               
                <table class="table table-bordered" align="center">
                    <thead>
                        <tr>
                            <th>Corso Id</th>
                            <th>Nome Corso</th>
                            <th>Durata Corso</th>
                            <th>Prezzo Corso</th>
                            <th>Edita</th>
                            <th>Elimina</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<CourseData> courses = (List<CourseData>) request.getAttribute("courses");
                            if (courses != null && !courses.isEmpty()) {
                                for (CourseData course : courses) {
                        %>
                            <tr>
                                <td><%= course.getId() %></td>
                                <td><%= course.getCourseName() %></td>
                                <td><%= course.getCourseDuration() %></td>
                                <td><%= course.getCoursePrice() %></td>
                                <td><a href="editScreen?id=<%= course.getId() %>">Edita</a></td>
                                <td><a href="deleteurl?id=<%= course.getId() %>">Elimina</a></td>
                            </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="6" class="text-center">Nessun corso trovato</td>
                            </tr>
                        <%
                            }
                        %>
					</tbody>
				</table>
				<a href="/Zucchetti" class="btn btn-primary">Home</a>
			</div>
		</div>
	</div>

	<footer class="text-center bg-body-tertiary">

		<!-- Copyright -->
		<div class="text-center p-4"
			style="background-color: rgba(0, 0, 0, 0.05);">
			© 2024 Copyright: <a class="text-body" href="/">Team ItConsulting</a>
		</div>

	</footer>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js"
		integrity="sha512-EKWWs1ZcA2ZY9lbLISPz8aGR2+L7JVYqBAYTq5AXgBkSjRSuQEGqWx8R1zAX16KdXPaCjOCaKE8MCpU0wcHlHA=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
<style>
.nav-item a.nav-link:hover {
	background-color: #dc3545 !important;
}
</style>
</html>
