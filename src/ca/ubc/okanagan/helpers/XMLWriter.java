package ca.ubc.okanagan.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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
	
	public static void writeAsXml(HashMap<CourseType, Courses> courseContainer, String outputFile) {
		
		//Load illegal chars
		//Loaded in regex so we can use String.replaceAll(...);
		StringBuilder illegalChars = new StringBuilder("["); //Open regex bracket
		try(
				BufferedReader in = new BufferedReader(new FileReader(new File("illegal.dat")));
				) {
			String input = null;
			while((input = in.readLine()) != null)
				if(!input.startsWith("#")) //# represents comment.
					illegalChars.append(input);
			
			illegalChars.append("]"); //Close regex bracket
		} catch(IOException e) {
			System.out.println("Error while trying to read illegal chars.");
		}
		
		//Start xml process
		File f = new File(outputFile); //Change to whatever
		//Load keywords
		Evaluator.loadKeys();
		
		int courseTypeCount = 0;
		int courseCount = 0;
		
		try(
				Writer writer = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
				PrintWriter out = new PrintWriter(writer);
				){
			
			//Start of xml. 
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<ubcocourses>");
			
			//Iterate of the map of courses.
			for(Map.Entry<CourseType, Courses> entry : courseContainer.entrySet()) {
				
				CourseType ct = entry.getKey();
				Courses courses = entry.getValue();
				//SKip the courses if its null
				if(courses == null) {
					System.out.println("Had to skip a set of courses since it was null.");
					System.out.println("Usually this error is caused because the UBC server(s) is/are not responding.");
					continue;
				}
				
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
									c.getCourseDescription().replaceAll(illegalChars.toString(), "")));
									/*c.getCourseDescription().replace("", "")*/
					out.println(String.format
							(DOUBLE_TAB + TAB + "<prerequisite boolean=\"%s\">%s</prerequisite>", 
									Evaluator.evaluate(c.getPrerequisite().toLowerCase()), c.getPrerequisite()));
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