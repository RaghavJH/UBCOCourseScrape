package ca.ubc.okanagan.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	
	private final String url;
	
	public HttpRequest(String url) {
		this.url = url;
	}
	
	//This method sends a GET request to a URL and receives HTML text.
	public String getPageContent() {
		try {
			URL Url = new URL(this.url);
			HttpURLConnection con = (HttpURLConnection)Url.openConnection();
			con.setRequestMethod("GET");
			setConnectionProperties(con);
			
			StringBuilder htmlContent = new StringBuilder();
			
			try(
					BufferedReader in = new BufferedReader
					(new InputStreamReader(con.getInputStream()));
					){
				
				String inputLine = null;
				while((inputLine = in.readLine()) != null) {
					htmlContent.append(inputLine);
				}
				
			}

			return htmlContent.toString();
		} catch(IOException e) {
			System.out.println("An error occured during IO.");
			return null;
		}
	}
	
	//Helper method to set appropriate headers similar to browser request
	protected void setConnectionProperties(HttpURLConnection con) {
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
		con.setRequestProperty("Cache-Control", "max-age=0");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Referrer", "http://www.calendar.ubc.ca/okanagan/courses.cfm");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
	
		//System.out.println(con.getRequestProperties().toString());
	}
}
