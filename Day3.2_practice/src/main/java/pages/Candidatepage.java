package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.User;

/**
 * Servlet implementation class Candidatepage
 */
@WebServlet("/Candidate")
public class Candidatepage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter())
		{
			pw.print("<h4>In candidate list page</h4>");

			HttpSession hs = request.getSession();
			System.out.println("Session is now "+hs.isNew());
			System.out.println("Session id "+hs.getId());
			User user;
			user = (User) hs.getAttribute("user_info");
			if(user!=null)
			{
			pw.print("<h3>"+user+"</h3>");
			}
			else
			{
				pw.print("<h5> No Cookies, Session Traking failed !!!</h5>");
			}
		}
	}

}
