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

/**
 * Servlet implementation class TeacherInsertController
 */
@WebServlet("/TeacherInsertController")
public class TeacherInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		TeacherDTO teacherDTO;
		ITeacherDAO teacherDAO = new TeacherDAOImpl();
		ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);
		
		response.setContentType("text/html");
		
		/* For UTF-8 support since Tomcat's default is ISO-8859-1
		 * If no doFilter has been implemented the
		 * following line should be uncommented
		 */
		//request.setCharacterEncoding("UTF-8");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		teacherDTO = new TeacherDTO();
		teacherDTO.setFirstName(firstName);
		teacherDTO.setLastName(lastName);
		
		try {
			teacherServ.insertTeacher(teacherDTO);
			request.setAttribute("insertedTeacher", teacherDTO);
			request.getRequestDispatcher("/jsps/teacherinserted.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("<h1 style=\"color:red\">Teacher already exist</h1>");
			request.getRequestDispatcher("/jsps/teachersmenu.jsp").include(request, response);
		}	
	}
}
