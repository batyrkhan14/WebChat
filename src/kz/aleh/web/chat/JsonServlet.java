package kz.aleh.web.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kz.aleh.web.chat.dao.Dao;
import kz.aleh.web.chat.dto.ContactDto;
import kz.aleh.web.chat.model.User;

/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao;
	{
		dao = new Dao();
	}        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String type = request.getParameter("type");
		ServletOutputStream out = response.getOutputStream();
		System.out.println("Type: " + type);
		if (type.equals("logIn")){
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			LogInResponse lir;
			try {
				User user = dao.getUserByEmail(email);
				if (user.getPassword().equals(password)){
					lir = new LogInResponse(true, user);
				}
				else{
					lir = new LogInResponse(false);
				}
			} catch (Exception e) {
				lir = new LogInResponse(false);
				e.printStackTrace();
			}
			out.print(new Gson().toJson(lir));
		}
		else if (type.equals("signUp")){
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			SignUpResponse sr = new SignUpResponse();
			try {
				dao.getUserByEmail(email);
				sr.setErrorCode(1);
			} catch (Exception e) {
				User user = new User();
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				user.setLastSeen(new Date());
				try {
					dao.addUser(user);
					sr.setSuccess(true);
				} catch (Exception e1) {
					sr.setSuccess(false);
					sr.setErrorCode(0);
				}
			}
			out.print(new Gson().toJson(sr));
		}
		else if (type.equals("getContacts")){
			int userId = Integer.valueOf(request.getParameter("userId"));
			List<ContactDto> contacts = dao.getContactListOfUser(userId);
			out.print(new Gson().toJson(contacts));
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
