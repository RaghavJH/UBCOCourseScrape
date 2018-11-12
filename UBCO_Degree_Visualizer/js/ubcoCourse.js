/*
 *
 *
 * To-do: Check if any other functions are required.
 *
 *
 */

var data, courseTypes;

// Constructor
var ubcoCourses = function() {

    var xml = new JKL.ParseXML("courses.xml");

    data = xml.parse();

  	courseTypes = data.ubcocourses.coursetype;
}

ubcoCourses.prototype.getCourseTypeByCode = function(code) {
	//Iterate over each course type
	for(var i = 0; i < courseTypes.length; i++){
		//If the course type is found, return its index.
		if(code.localeCompare(courseTypes[i].code) == 0)
			return i;
	}
	//If the course type does not exist, return -1.
	return -1;
}

ubcoCourses.prototype.getCourseBySection = function(index, section){
  var courseCode = courseTypes[index].code;
  var courses = courseTypes[index].course;

	//Iterate over each course in a course type
	for(var i = 0; i < courses.length; i++){
		//If the sections match, return the course as an object.
		if(courses[i].section == section) {
			return new course(
          courseCode,
    			courses[i].section,
    			courses[i].credits,
    			courses[i].title,
    			courses[i].description,
    			courses[i].prerequisite,
    			courses[i].corequisite,
    			courses[i].equivalency
    			);
		}
	}
	//If the course does not exist, return null.
	return null;
}

ubcoCourses.prototype.getCourseByTitle = function(index, title){
	var courses = courseTypes[index].course;
	//Iterate over each course in a course type
	for(var i = 0; i < courses.length; i++){
		//If the sections match, return the course as an object.
		if(courses[i].title == title) {
			return new course(
    			courses[i].section,
    			courses[i].credits,
    			courses[i].title,
    			courses[i].description,
    			courses[i].prerequisite,
    			courses[i].corequisite,
    			courses[i].equivalency
    			);
		}
	}
	//If the course does not exist, return null.
	return null;
}
