package ca.ubc.okanagan.http;

import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

import ca.ubc.okanagan.core.Courses;
import ca.ubc.okanagan.helpers.RequestParser;

public final class CallableHttpRequest extends HttpRequest implements Callable<Courses> {

	public CallableHttpRequest(String url) {
		super(url);
	}
	
	@Override
	public Courses call() throws Exception {
		String pageContent = getPageContent();
		
		return pageContent != null ? RequestParser.parseToCourseList(pageContent) : null;
	}
	
	@Override
	protected void setConnectionProperties(HttpURLConnection con) {
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
		con.setRequestProperty("Cache-Control", "max-age=0");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Referrer", "http://www.calendar.ubc.ca/okanagan/courses.cfm?go=code");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
	}
}
