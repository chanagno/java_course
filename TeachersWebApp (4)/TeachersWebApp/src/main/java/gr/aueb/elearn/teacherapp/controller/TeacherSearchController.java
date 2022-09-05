package gr.aueb.elearn.teacherapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dao.TeacherDAOImpl;
import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.service.ITeacherService;
import gr.aueb.elearn.teacherapp.service.TeacherServiceImpl;
import gr.aueb.elearn.teacherapp.service.exceptions.EntityNotFoundException;

/**
 * Servlet implementation class TeacherSearchController
 */
@WebServlet("/TeacherSearchController")
public class TeacherSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ITeacherDAO teacherDAO = new TeacherDAOImpl();
		ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);
		List<Teacher> teachers = new ArrayList<>();	
		response.setContentType("text/html");
		String lastName = request.getParameter("searchInput");
		
		try {
			teachers = teacherServ.getTeachersBySurname(lastName);
			request.setAttribute("teachers", teachers);
			request.getRequestDispatcher("/jsps/teachers.jsp").forward(request, response);
		} catch (EntityNotFoundException e) {
			// e.printStackTrace();
			response.getWriter().write("<h1 style=\"color:red\">Teacher does not exist</h1>");
			request.getRequestDispatcher("/jsps/teachersmenu.jsp").include(request, response);
		}	
	}
}

/*
 * if (teachers != null) { request.setAttribute("teachers", teachers);
 * request.getRequestDispatcher("/jsps/teachers.jsp").forward(request,
 * response); } else { response.getWriter().
 * write("<h1 style=\"color:red\">Teacher does not exist</h1>");
 * request.getRequestDispatcher("/jsps/teachersmenu.jsp").include(request,
 * response); }
 */
