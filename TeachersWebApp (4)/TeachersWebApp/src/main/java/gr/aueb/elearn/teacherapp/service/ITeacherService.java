package gr.aueb.elearn.teacherapp.service;

import java.util.List;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;
import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.service.exceptions.EntityNotFoundException;

public interface ITeacherService {	
	
	/**
	 * Inserts or updates a {@link Teacher} based on the data carried by the
	 * {@link TeacherDTO}.
	 * 
	 * @param teacherDTO 
	 * 			DTO object that contains the data.
	 *
	 */
	void insertTeacher(TeacherDTO teacherDTO);
	
	/**
	 * Deletes a {@link Teacher} based on the data carried by the
	 * {@link TeacherDTO}.
	 * 
	 * @param teacherDTO 
	 * 			DTO object that contains the data.
	 * @throws EntityNotFoundException
	 * 			if entity not found. 
	 */
	
	void deleteTeacher(TeacherDTO teacherDTO)
			throws EntityNotFoundException;
	
	
	/**
	 * Updates a {@link Teacher} based on the data carried by the
	 * {@link TeacherDTO}.
	 * 
	 * @param newTeacherDTO
	 * 			DTO object that contains the data of the 
	 * 			new {@link Teacher}.
	 * @throws EntityNotFoundException
	 * 			if any Teacher identified by their id 
	 * 			was not found. 
	 */
	void updateTeacher(TeacherDTO newTeacherDTO) 
			throws EntityNotFoundException;
	
	/**
	 * Searches and gets back to the caller a list
	 * of the {@link Teacher} objects identified by
	 * their surname or surname's initial letters.
	 * 
	 * @param surname
	 * 			a String object that contains the
	 * 			surname or the letters that the 
	 * 			surname starts with, of the {@link Teacher}
	 * 			objects we are looking for. 
	 * @return
	 * 			a List that contains the results of
	 * 			the search, that is a List of {@link Teacher}
	 * 			objects. 
	 * @throws EntityNotFoundException
	 * 			if no Entity found.
	 */
	List<Teacher> getTeachersBySurname(String surname)
			throws EntityNotFoundException;
}
