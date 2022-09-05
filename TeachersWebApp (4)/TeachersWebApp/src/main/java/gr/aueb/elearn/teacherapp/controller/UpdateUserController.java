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

@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("id"));
		
		String eMail = request.getParameter("eMail");
		String password = request.getParameter("password");
		
		String Newpassword = request.getParameter("Newpassword");
		
		IUsersDAO UserDAO= new IUsersDAOImpl();
		Users users= new Users();
		IUsersService usersServ = new IUsersServiceImpl (UserDAO);
		
		response.setContentType("text/html");
		
		UserDTO userDTO =new UserDTO();
	//	userDTO.setUsername(eMail);
		//userDTO.setPassword(password);
		
		try {
			users=usersServ.getUserIdByUsername(eMail);
			
		}catch (EntityNotFoundException e) {
			e.printStackTrace();

			try {					
				//userDTO.setId(users[1].getId());
				usersServ.updateUsersPass(userDTO,Newpassword);
				request.setAttribute("updatedUser", userDTO);
				request.getRequestDispatcher("/jsps/UpdateUser.jsp").forward(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Sign up Error");
			request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
		}	
	}

}
