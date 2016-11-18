package kz.aleh.web.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.aleh.web.chat.dao.Dao;
import kz.aleh.web.chat.model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	
	Dao dao;

	{
		dao = new Dao();
		
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		User user = new User();
		user.setName(request.getParameter("name"));
		if (request.getParameter("password").equals(request.getParameter("passwordconf"))){
			user.setPassword(request.getParameter("password"));
		}
		else{
			errors.add("Password are different.");
		}
		if (request.getParameter("email").matches(EMAIL_PATTERN)){
			try{
				dao.getUserByEmail(request.getParameter("email"));
				errors.add("Email with such user already exists.");
			}
			catch (Exception e){
				user.setEmail(request.getParameter("email"));
			}
		}
		else{
			errors.add("Email is not valid.");
		}
		if (errors.isEmpty()){
			try{
				user.setLastSeen(new Date());
				dao.addUser(user);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			catch (Exception e){
				errors.add("Unknown server error, try again.");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
			}			
		}
		else{
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
		
	}

}
