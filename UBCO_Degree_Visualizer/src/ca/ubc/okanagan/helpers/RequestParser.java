package ca.ubc.okanagan.helpers;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ca.ubc.okanagan.core.Course;
import ca.ubc.okanagan.core.CourseType;
import ca.ubc.okanagan.core.Courses;
/*
 *
 *
 *	Surely there's a better way to write this class ...
 *
 *
 */
public final class RequestParser {
	
	public static Courses parseToCourseList(String html) {
		
		Courses c = new Courses();
		
		Document Doc = Jsoup.parse(html);
		
		Elements dts = Doc.getElementsByTag("dt");
		Elements dds = Doc.getElementsByTag("dd");
		
		for(int i = 0; i < dds.size(); i++) {
			
			Element dt = dts.get(i);
			Element dd = dds.get(i);
			
			int courseSection;
			
			String 	description = null,
					courseCredits = null,
					courseTitle = null, 
					prerequisite = null, 
					corequisite = null,
					equivalency = null;
			
			courseSection = Integer.parseInt(dt.select("a").attr("name"));
			courseCredits = dt.html().split("[\\(\\)]")[1];
			courseTitle = dt.select("b").html();			
			
			String innerHtml = dd.html().toLowerCase();
			
			if(!innerHtml.equals("")) {
				
				Elements iTags = dd.select("i");
				
				if(innerHtml.contains("prerequisite") || innerHtml.contains("corequisite")) {
					
					String[] split = dd.html().split("<br>");
					description = split[0].replaceAll("\n", "");
					
					if(split.length == 1) {
						
						if(iTags.size() == 0)
							description = split[0];
						else {
							
							if(iTags.get(0).html().equals("Prerequisite:"))
								prerequisite = getAttribute(split[0], 'p');
							else if(iTags.get(0).html().equals("Corequisite:"))
								corequisite = getAttribute(split[0], 'c');
							else
								equivalency = getAttribute(split[0], 'e');
						}
					}
					else if(split.length == 2) {					
						
						int correctIndex = 0;
						
						while(correctIndex < iTags.size()) {
							
							if(iTags.get(correctIndex).html().equals("Prerequisite:")) 
								prerequisite = getAttribute(split[1], 'p');
							 else if(iTags.get(correctIndex).html().equals("Corequisite:"))
								corequisite = getAttribute(split[1], 'c');
							 else if(iTags.get(correctIndex).html().equals("Equivalency:")) {
								 equivalency = getAttribute(split[1], 'e');
							 }

							correctIndex++;
						}
						
							 
					} else if(split.length == 3){
						
						
						int splitIndex = 1;
						
						for(Element it : iTags) {
							char firstChar = it.html().toLowerCase().replace("<i>", "").charAt(0);
							
							if(firstChar == 'p') {
								prerequisite =  getAttribute(split[splitIndex], 'p');
							} else if(firstChar == 'c') {
								corequisite = getAttribute(split[splitIndex], 'c');
							} else if(firstChar == 'e') {
								equivalency = getAttribute(split[splitIndex], 'e');
							}
							
							splitIndex++;
						}
						
					} else {
						
						int splitIndex = 1;
						
						for(Element it : iTags) {
							char firstChar = it.html().toLowerCase().replace("<i>", "").charAt(0);
							
							if(firstChar == 'p') {
								prerequisite =  getAttribute(split[splitIndex], 'p');
							} else if(firstChar == 'c') {
								corequisite = getAttribute(split[splitIndex], 'c');
							} else if(firstChar == 'e') {
								equivalency = getAttribute(split[splitIndex], 'e');
							}
							
							splitIndex++;
						}
					}
				} else {
					description = dd.html().replaceAll("<br>", "").replaceAll("\n", "");
				}
			}
			
			c.add(new Course(courseSection, courseCredits, courseTitle, description, prerequisite, corequisite, equivalency));
			
		}

		return c;
		
	}
	
	//This method parses the course page to return all the coursecodes and their names.
	public static ArrayList<CourseType> parseCourseTypes(String html){
		
		ArrayList<CourseType> courseTypes = new ArrayList<CourseType>();
		
		Document Doc = Jsoup.parse(html);

		Elements tds = Doc.getElementsByTag("td");
		
		for(int i = 1; i < tds.size(); i++) {
			//continue this because this td contains every td in one html chunk
			if(i == 100)
				continue;
			
			//Make sure the a tags selected size are greater than 0
			//Since some td contains &nbsp;
			Elements firstTds = null;
			Elements secondTds = null;
			if( (firstTds = tds.get(i).select("a")).size() > 0 && 
					(secondTds = tds.get(i+1).select("a")).size() > 0)
				courseTypes.add(
						new CourseType(
						firstTds.html(), //i is the course code's <a> tag
						secondTds.html())); //i+1 is the course name's <a> tag
		}
		return courseTypes;
	}
	
	private static String getAttribute(String content, char type) {
		switch(type) {
		case 'p':
			return content.substring(content.indexOf("<i>Prerequisite:</i>") + "<i>Prerequisite:</i>".length()).replace("\n", "");
		case 'c':
			return content.substring(content.indexOf("<i>Corequisite:</i>") + "<i>Corequisite:</i>".length()).replace("\n", "");
		case 'e':
			return content.substring(content.indexOf("<i>Equivalency:</i>") + "<i>Equivalency:</i>".length()).replace("\n", "");
		default:
			return null;
		}
	}
	
}
