<%@ page import="entities.CourseData"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Courses List</title>
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
				<a class="navbar-brand " href="#" style="color: #ffffff; font-size:1.5rem;font-weight: 700 ">ZUCCHETTI</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon" style="color: #ffffff;"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-end"
					id="navbarSupportedContent">
					<ul class="navbar-nav mb-2 mb-lg-0">
						 <li class="nav-item">
                       <a class="nav-link active" aria-current="page" href="/Zucchetti/jsp/index.jsp" style="color: #ffffff; font-size: 1rem;font-weight: 500">Studenti</a>
                      
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active"
                            href="/Zucchetti/jsp/index2.jsp" style="color: #ffffff;font-size: 1rem;font-weight: 500">Professori</a>
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

		<div class="d-flex justify-content-center align-items-center"
			style="height: 100vh;">
			<div class="card p-4" style="width: 40rem;">
				<%
    CourseData course = (CourseData) request.getAttribute("course");
    if (course != null) {
%>
				<form action="editurl?id=<%= course.getId() %>" method="post" class="mt-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title text-center mb-4">Edit Course Details</h5>
                        <div class="mb-3">
                            <label for="courseName" class="form-label">Course Name</label>
                            <input type="text" class="form-control" id="courseName" name="courseName" value="<%= course.getCourseName() %>">
                        </div>
                        <div class="mb-3">
                            <label for="courseDuration" class="form-label">Course Duration</label>
                            <input type="text" class="form-control" id="courseDuration" name="courseDuration" value="<%= course.getCourseDuration() %>">
                        </div>
                        <div class="mb-3">
                            <label for="coursePrice" class="form-label">Course Price</label>
                            <input type="text" class="form-control" id="coursePrice" name="coursePrice" value="<%= course.getCoursePrice() %>">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary me-2">Edit</button>
                            <button type="reset" class="btn btn-secondary">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
				
				<%
    } else {
        out.println("<h2>Course not found</h2>");
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
			© 2024 Copyright: <a class="text-body"
				href="/">Team ItConsulting</a>
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
