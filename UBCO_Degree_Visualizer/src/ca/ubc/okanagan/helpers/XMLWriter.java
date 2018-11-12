package ca.ubc.okanagan.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import ca.ubc.okanagan.core.Course;
import ca.ubc.okanagan.core.CourseType;
import ca.ubc.okanagan.core.Courses;

public class XMLWriter {
	
	private static final String TAB = "    ";
	private static final String DOUBLE_TAB = "        ";
	
	public static void writeAsXml(HashMap<CourseType, Courses> courseContainer) {
		
		File f = new File("courses.xml"); //Change to whatever
		
		int courseTypeCount = 0;
		int courseCount = 0;
		
		try(
				Writer writer = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
				PrintWriter out = new PrintWriter(writer);
				){
			
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<ubcocourses>");
			
			for(Map.Entry<CourseType, Courses> entry : courseContainer.entrySet()) {
				
				CourseType ct = entry.getKey();
				Courses courses = entry.getValue();
				
				courseTypeCount++;
				
				out.println(String.format(TAB + "<coursetype code=\"%s\" fullname=\"%s\">", ct.getCourseCode(), ct.getCourseFullName()));
				
				for(Course c : courses) {
					out.println(String.format(DOUBLE_TAB + "<course>"));
					
					out.println(String.format
							(DOUBLE_TAB + TAB + "<section>%d</section>", c.getCourseSection()));
					out.println(String.format
							(DOUBLE_TAB + TAB + "<credits>%s</credits>", c.getCourseCredits()));
					out.println(String.format
							(DOUBLE_TAB + TAB + "<title>%s</title>", c.getCourseTitle()));
					out.println(String.format
							(DOUBLE_TAB + TAB + "<description>%s</description>", 
									c.getCourseDescription().replace("", "")));
					out.println(String.format
							(DOUBLE_TAB + TAB + "<prerequisite>%s</prerequisite>", c.getPrerequisite()));
					out.println(String.format
							(DOUBLE_TAB + TAB + "<corequisite>%s</corequisite>", c.getCorequisite()));
					out.println(String.format
							(DOUBLE_TAB + TAB + "<equivalency>%s</equivalency>", c.getEquivalency()));
					
					out.println(DOUBLE_TAB + "</course>");
					
					courseCount++;
				}
				
				out.println(TAB + "</coursetype>");
			}
			out.println("</ubcocourses>");
			
			String stat = "Statistics - Course Types: " + courseTypeCount + ", Course Count: " + courseCount;
			System.out.println(stat);
		} catch(IOException e) {
			System.out.println("An error occured during I/O.");
		}		
		
	}
	
}