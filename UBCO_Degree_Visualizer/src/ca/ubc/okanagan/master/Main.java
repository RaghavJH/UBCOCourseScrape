package ca.ubc.okanagan.master;

import java.util.concurrent.ThreadPoolExecutor;

import ca.ubc.okanagan.core.CourseType;
import ca.ubc.okanagan.core.Courses;
import ca.ubc.okanagan.helpers.RequestParser;
import ca.ubc.okanagan.helpers.XMLWriter;
import ca.ubc.okanagan.http.CallableHttpRequest;
import ca.ubc.okanagan.http.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		//Initiate the process of building the course list
		
		//First get home page to scrape all courses
		HttpRequest h = new HttpRequest("http://www.calendar.ubc.ca/okanagan/courses.cfm?go=code");
		ArrayList<CourseType> courseTypes = RequestParser.parseCourseTypes(h.getPageContent());

		//Allow a max of 50 threads when scraping
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(50);
		
		//Starting scraping pages.
		
		//This is the baseUrl for the course pages
		final String baseUrl = "http://www.calendar.ubc.ca/okanagan/courses.cfm?go=code&code=";
		
		ArrayList<Future<Courses>> scraped = new ArrayList<Future<Courses>>();
		
		for(CourseType courseType : courseTypes) {
			Callable<Courses> callable = 
					new CallableHttpRequest(baseUrl + courseType.getCourseCode());
			Future<Courses> future = executor.submit(callable);
			scraped.add(future);
		}
		
		//This will block and put all the courseTypes with their respective
		//courseLists into the hashmap
		
		//Create a CourseList for each CourseType
		HashMap<CourseType, Courses> courseContainer = new HashMap<CourseType, Courses>();
		
		for(int i = 0; i < scraped.size(); i++) {
			courseContainer.put(courseTypes.get(i), scraped.get(i).get());
		}
		
		XMLWriter.writeAsXml(courseContainer);
		
		System.out.println("Done");
	}

}
