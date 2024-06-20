package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mysql.cj.protocol.Resultset;

import entities.CourseData;

public class CourseDAO {
	private Connection connection;

	public CourseDAO(Connection connection) {
		this.connection = connection;
	}


	public boolean insertCourse(CourseData course) throws SQLException {
		String query = "INSERT INTO COURSEDATA(COURSENAME, COURSEDURATION, COURSEPRICE) VALUES (?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getCourseDuration());
			ps.setFloat(3, course.getCoursePrice());
			int count = ps.executeUpdate();
			return count == 1;
		}
	}

	public boolean updateCourse(CourseData course) throws SQLException {
		String query = "UPDATE COURSEDATA SET COURSENAME=?, COURSEDURATION=?, COURSEPRICE=? WHERE ID=?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getCourseDuration());
			ps.setFloat(3, course.getCoursePrice());
			ps.setInt(4, course.getId());
			int count = ps.executeUpdate();
			return count == 1;
		}
	}

	public List<CourseData> getAllCourses() throws SQLException {
		List<CourseData> courses = new ArrayList<>();
		String query = "SELECT ID, COURSENAME, COURSEDURATION, COURSEPRICE FROM COURSEDATA";
		try (PreparedStatement ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("ID");
				String courseName = rs.getString("COURSENAME");
				String courseDuration = rs.getString("COURSEDURATION");
				float coursePrice = rs.getFloat("COURSEPRICE");
				CourseData course = new CourseData(id, courseName, courseDuration, coursePrice);
				courses.add(course);
			}
		}
		return courses;
	}

	//metodoRicerca
	public List<CourseData> searchCourseById(int searchId) throws SQLException {
	    List<CourseData> courses = new ArrayList<>();
	    String query = "SELECT ID, COURSENAME, COURSEDURATION, COURSEPRICE FROM COURSEDATA WHERE ID = ?";
	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setInt(1, searchId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                int id = rs.getInt("ID");
	                String courseName = rs.getString("COURSENAME");
	                String courseDuration = rs.getString("COURSEDURATION");
	                float coursePrice = rs.getFloat("COURSEPRICE");
	                CourseData course = new CourseData(id, courseName, courseDuration, coursePrice);
	                courses.add(course);
	            }
	        }
	    }
	    return courses;
	}



	public Optional<CourseData> getCourseById(int id) throws SQLException {
		String query = "SELECT ID, COURSENAME, COURSEDURATION, COURSEPRICE FROM COURSEDATA WHERE ID = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				CourseData course = new CourseData(rs.getInt("ID"), rs.getString("COURSENAME"), rs.getString("COURSEDURATION"), rs.getFloat("COURSEPRICE"));
				return Optional.of(course);
			}
		}
		return Optional.empty();
	}

	public boolean deleteCourse(int id) throws SQLException {
		String query = "DELETE FROM COURSEDATA WHERE ID=?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			return count == 1;
		}
	}
}

