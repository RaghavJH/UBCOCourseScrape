var code, section, credits, title, description, prerequisite, corequisite, equivalency;

var course = function(code, section, credits, title, description, prerequisite, corequisite, equivalency) {
	this.code = code;
	this.section = section;
	this.credits = credits;
	this.title = title;
	this.description = description;
	this.prerequisite = prerequisite;
	this.corequisite = corequisite;
	this.equivalency = equivalency;
}

course.prototype.getCode = function() {
	return this.code;
}
course.prototype.getSection = function() {
	return this.section;
}

course.prototype.getCredits = function() {
	return this.credits;
}

course.prototype.getTitle = function() {
	return this.title;
}

course.prototype.getDescription = function() {
	return this.description;
}

course.prototype.getPrerquisite = function() {
	return this.prerequisite;
}

course.prototype.getCorequisite = function() {
	return this.corequisite;
}

course.prototype.getEquivalency = function() {
	return this.equivalency;
}
