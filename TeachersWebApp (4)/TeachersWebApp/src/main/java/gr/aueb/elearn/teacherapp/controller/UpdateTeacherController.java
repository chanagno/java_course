package gr.aueb.elearn.teacherapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dao.IUsersDAO;
import gr.aueb.elearn.teacherapp.dao.IUsersDAOImpl;
import gr.aueb.elearn.teacherapp.dao.TeacherDAOImpl;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;
import gr.aueb.elearn.teacherapp.dto.UserDTO;
import gr.aueb.elearn.teacherapp.model.Users;
import gr.aueb.elearn.teacherapp.service.ITeacherService;
import gr.aueb.elearn.teacherapp.service.IUsersService;
import gr.aueb.elearn.teacherapp.service.IUsersServiceImpl;
import gr.aueb.elearn.teacherapp.service.TeacherServiceImpl;
import gr.aueb.elearn.teacherapp.service.exceptions.EntityNotFoundException;

@WebServlet("/UpdateTeacherController")
public class UpdateTeacherController extends HttpServlet{
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	TeacherDTO teacherDTO = new TeacherDTO();
	ITeacherDAO teacherDAO = new TeacherDAOImpl();
	ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);
	
	response.setContentType("text/html");
	Long id = Long.parseLong(request.getParameter("id"));
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	
	teacherDTO.setId(id);
	teacherDTO.setFirstName(firstName);
	teacherDTO.setLastName(lastName);
	try {
		teacherServ.updateTeacher(teacherDTO);
		request.setAttribute("updatedTeacher", teacherDTO);
		request.getRequestDispatcher("/jsps/UpdateTeacher.jsp").forward(request, response);	
	} catch (EntityNotFoundException e2) {
		response.getWriter().write("<h1 style=\"color:red\">Teacher does not exist</h1>");
		request.getRequestDispatcher("/jsps/teachers.jsp").include(request, response);
	}	
	}

	public UpdateTeacherController() {
		// TODO Auto-generated constructor stub
	}

}
