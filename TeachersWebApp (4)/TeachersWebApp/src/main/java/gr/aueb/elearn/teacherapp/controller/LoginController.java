package gr.aueb.elearn.teacherapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.elearn.teacherapp.dao.IUsersDAO;
import gr.aueb.elearn.teacherapp.dao.IUsersDAOImpl;
import gr.aueb.elearn.teacherapp.dto.UserDTO;
import gr.aueb.elearn.teacherapp.model.Users;
import gr.aueb.elearn.teacherapp.service.IUsersService;
import gr.aueb.elearn.teacherapp.service.IUsersServiceImpl;
import gr.aueb.elearn.teacherapp.service.exceptions.EntityNotFoundException;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String eMail = request.getParameter("eMail");
		String password = request.getParameter("password");
		
		IUsersDAO UserDAO= new IUsersDAOImpl();
		List <Users> users= new ArrayList<>();
		IUsersService usersServ = new IUsersServiceImpl (UserDAO);
		UserDTO userDTO= new UserDTO();
		
		userDTO.setUsername(eMail);
		userDTO.setPassword(password);
		
		response.setContentType("text/html");
		try {
			users=usersServ.getUsersByUsernameandPass(eMail,password);
			
			request.getRequestDispatcher("/jsps/teachersmenu.jsp").forward(request, response);
		}catch (EntityNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("error", "Login Error");
			request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
		}	
		
		
		
		
		
		/*if (eMail.equals("a8anassis@gmail.com") && (password.contentEquals("123"))) {
			// redirect to search page
			request.getRequestDispatcher("/jsps/teachersmenu.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Login Error");
			request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
		}*/
				
	}

}
