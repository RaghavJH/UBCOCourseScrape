package ca.ubc.okanagan.core;

public class CourseType {
	
	public final String courseCode;
	
	public final String courseFullName;
	
	public CourseType(String courseCode, String courseFullName) {
		this.courseCode = courseCode;
		this.courseFullName = courseFullName;
	}
	
	public String getCourseCode() {
		return this.courseCode;
	}
	
	public String getCourseFullName() {
		return this.courseFullName;
	}
	
	@Override
	public String toString() {
		return String.format("Course Code: %s, Course Name: %s",
				this.courseCode, this.courseFullName);
	}

}
