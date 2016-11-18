package kz.aleh.web.chat;

import java.io.IOException;

import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.aleh.web.chat.dao.Dao;
import kz.aleh.web.chat.model.User;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="WebChat")
	Dao dao;
	{
		dao = new Dao();
	}     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			User user = dao.getUserByEmail(request.getParameter("email"));
			if (user.getPassword().equals(request.getParameter("password"))){
				request.setAttribute("userId", user.getId());
				request.getRequestDispatcher("/chat.jsp").forward(request, response);
			}
			else{
				request.setAttribute("isError", true);
				request.getRequestDispatcher("/index.jsp").forward(request, response);

			}
		}
		catch (Exception e){
			request.setAttribute("isError", true);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
