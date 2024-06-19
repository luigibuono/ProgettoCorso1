package entities;

public class CourseData {
	 private int id;
	    private String courseName;
	    private String courseDuration;
	    private float coursePrice;

	    public CourseData (int id, String courseName, String courseDuration, float coursePrice) {
	        this.id = id;
	        this.courseName = courseName;
	        this.courseDuration = courseDuration;
	        this.coursePrice = coursePrice;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		public String getCourseDuration() {
			return courseDuration;
		}

		public void setCourseDuration(String courseDuration) {
			this.courseDuration = courseDuration;
		}

		public float getCoursePrice() {
			return coursePrice;
		}

		public void setCoursePrice(float coursePrice) {
			this.coursePrice = coursePrice;
		}

	   
	    
	}
