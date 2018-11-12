package ca.ubc.okanagan.core;

public class Course {
	
	private int courseSection;
	
	private String 
	courseCredits, courseTitle, 
	courseDescription, prerequisite, corequisite, equivalency;
	
	public Course(int courseSection, String courseCredits, String courseTitle, String courseDescription, String prerequisite, String corequisite, String equivalency) {
		
		this.courseSection = courseSection;
		this.courseCredits = courseCredits;
		this.courseTitle = courseTitle;
		
		if(courseDescription == null)
			this.courseDescription = "None";
		else
			this.courseDescription = courseDescription;
		//
		if(prerequisite == null)
			this.prerequisite = "None";
		else
			this.prerequisite = prerequisite;
		//
		if(corequisite == null)
			this.corequisite = "None";
		else
			this.corequisite = corequisite;
		//
		if(equivalency == null)
			this.equivalency = "None";
		else
			this.equivalency = equivalency;		
	}

	public int getCourseSection() {
		return courseSection;
	}
	
	public String getCourseCredits() {
		return this.courseCredits;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public String getPrerequisite() {
		return prerequisite;
	}

	public String getCorequisite() {
		return corequisite;
	}
	
	public String getEquivalency() {
		return equivalency;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Course Section: %d\nCourse Credits: %s\nCourse Title: %s\nCourse Description: %s\n"
				+ "Course Prequisite: %s\nCourse Corequisite: %s\nCourse Equivalency: %s\n",
				this.courseSection, this.courseCredits, this.courseTitle,
				this.courseDescription, this.prerequisite, this.corequisite,
				this.equivalency
		);
	}
}
