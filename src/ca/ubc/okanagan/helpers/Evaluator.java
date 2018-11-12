package ca.ubc.okanagan.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator {
	
	private static ArrayList<String[]> keyList = new ArrayList<String[]>();
	
	public static String evaluate(String toEvaluate) {
		//Final evaluated string is stored in this.
		StringBuilder result = new StringBuilder();
		//For every key
		for(String[] key : keyList) {
			Pattern p = Pattern.compile(key[0]);
			Matcher m = p.matcher(toEvaluate);
			//If it matches this key
			if(m.find()) {
				//Extract the content, which is often the courses in the present.
				ArrayList<String> content = getContentFromSentence(m.group(0), key[1]);
				//Append the type e.g. OR, AND, %OR
				result.append(key[2] + ";");
				
				//Append the content
				for(int i = 0; i < content.size(); i++) {
					if(i != content.size() - 1)
						result.append(content.get(i) + ";");
					else
						result.append(content.get(i));
				}
				
				result.append("+");
			}
			
			
		}
		
		//If its not none, and is not evaluated, return N/R.
		if(!toEvaluate.equalsIgnoreCase("None") && result.toString().isEmpty()) {
			return "N/R"; //N/R stands for NOT recognized
		}
		
		return result.toString();
	}

	public static void loadKeys() {
		try(
				BufferedReader in = new BufferedReader(new FileReader(new File("keywords.dat")));
				) {
			String input = null;
			while((input = in.readLine()) != null) {
				if(!input.startsWith("#")) { 
					keyList.add(input.split(":"));
				}
			}
		} catch(IOException e) {
			System.out.println("Error in IO.");
		}
	}
	
	private static ArrayList<String> getContentFromSentence(String sentence, String regex) {
		Matcher m = Pattern.compile(regex, Pattern.MULTILINE)
						.matcher(sentence);
		
		ArrayList<String> content = new ArrayList<String>(5);
		
		while(m.find()) {
			content.add(m.group(0));
		}
		
		return content;
	}
	
//	public static void main(String[] args) {
//		
//		loadKeys();
//		
//		System.out.println(evaluate("Either (a) FREN 344 or (b) FREN 345. Third-year standing.".toLowerCase()));
//		
//	}
	
}
