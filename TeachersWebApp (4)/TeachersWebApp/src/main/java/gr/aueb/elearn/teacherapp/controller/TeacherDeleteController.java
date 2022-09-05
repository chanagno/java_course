package gr.aueb.elearn.teacherapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dao.TeacherDAOImpl;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;

import gr.aueb.elearn.teacherapp.service.ITeacherService;
import gr.aueb.elearn.teacherapp.service.TeacherServiceImpl;
import gr.aueb.elearn.teacherapp.service.exceptions.EntityNotFoundException;

/**
 * Servlet implementation class TeacherDeleteController
 */
@WebServlet("/TeacherDeleteController")
public class TeacherDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		TeacherDTO teacherDTO = new TeacherDTO();
		ITeacherDAO teacherDAO = new TeacherDAOImpl();
		ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);
		
		response.setContentType("text/html");
		Long id = Long.parseLong(request.getParameter("id"));
		
		teacherDTO.setId(id);
		
		try {
			teacherServ.deleteTeacher(teacherDTO);
			request.setAttribute("deletedTeacherId", id);
			request.getRequestDispatcher("/jsps/teacherdeleted.jsp").forward(request, response);	
		} catch (EntityNotFoundException e2) {
			response.getWriter().write("<h1 style=\"color:red\">Teacher does not exist</h1>");
			request.getRequestDispatcher("/jsps/teachers.jsp").include(request, response);
		}	
	}
}
