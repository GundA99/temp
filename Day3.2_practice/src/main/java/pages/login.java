package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class login
 */
@WebServlet(urlPatterns = "/Validate",loadOnStartup = 1)
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl dao;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		try {
			
			 dao= new UserDaoImpl();
		}
		catch(Exception e)
		{
			throw new ServletException("err in iniot of"+getClass(), e);			
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {

		try {
			dao.cleanUp();
		}
		catch(Exception e)
		{
			System.out.println("Err in destroy of"+getClass()+" "+e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String email = request.getParameter("em");
		String pass = request.getParameter("pass");
		User u = new User();
		try {

			u = dao.authenticateUser(email, pass);
			if(u!=null)
			{
				
				HttpSession session = request.getSession();
				System.out.println("Session is new "+session.isNew());
				System.out.println("Session id" + session.getId());
				session.setAttribute("user_info", u);
				if(u.getRole().equals("admin"))
				{
					response.sendRedirect("Admin");
				}
				else
				{
					if(u.isVotingStatus())
					response.sendRedirect("logout");
					else
					{
						response.sendRedirect("Candidate");
					}
				}
			}
			else
			{
				try(PrintWriter pw = response.getWriter())
				{
					pw.print("<h3> Invalid login please <a href='index.html'>Retry</a><h3>");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
				
	}

}
