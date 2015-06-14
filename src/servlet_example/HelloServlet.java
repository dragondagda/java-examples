package servlet_example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(description = "Hello World Servlet", urlPatterns = { "" })
public class HelloServlet extends HttpServlet {

	/**
	 * generated serialVersionUID.
	 */
	private static final long serialVersionUID = -6471808644883244726L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.fillContent(resp);
		resp.setContentType("text/html");
		resp.setStatus(200);
	}

	private void fillContent(HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		this.appendDocTypeAndHtmlStart(writer);
		this.appendHead(writer);
		this.appendBody(writer);
		this.appendHtmlEnd(writer);
	}

	private void appendHtmlEnd(PrintWriter writer) {
		writer.append("</html>");
	}

	private void appendBody(PrintWriter writer) {
		Date date = new Date();
		writer.append("<body>");
		writer.append("<h1>Hallo Welt</h1>");
		writer.append("<p><b><u>Uhrzeit:</u></b> " + date + "</p>");
		writer.append("<p>Es sind " + date.getTime()
				+ " ms vergangen seit 01.01.1970 GMT</p>");
		writer.append("</body>");
	}

	private void appendHead(PrintWriter writer) {
		writer.append("<head>").append("<meta charset=\"UTF-8\">")
				.append("<title>Hallo Welt</title>").append("</head>");
	}

	private void appendDocTypeAndHtmlStart(PrintWriter writer) {
		writer.append("<!DOCTYPE html><html>");
	}
}
