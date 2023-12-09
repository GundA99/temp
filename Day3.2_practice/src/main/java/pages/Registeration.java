package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class Registeration
 */
@WebServlet(urlPatterns = "/voter_regisater",loadOnStartup = 1)
public class Registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private UserDaoImpl cdao;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		
		try {
			cdao=new UserDaoImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside the doPost");
		
		User user = new User();
		user.setFirstName(request.getParameter("fn"));
		user.setLastName(request.getParameter("ln"));
		user.setEmail(request.getParameter("em"));
		user.setPassword(request.getParameter("pass"));
		user.setDob(Date.valueOf(request.getParameter("dob")));
		
		try (PrintWriter pw = response.getWriter()){
			String msg=cdao.registerNewVoter(user);
			pw.print(msg);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
