package gr.aueb.elearn.teacherapp.dao;

import java.util.List;
import gr.aueb.elearn.teacherapp.model.Teacher;

/**
 * @author Ath. Androutsos
 */
public interface ITeacherDAO {
	void insert(Teacher teacher);
	void delete(Object id);
	void update(Teacher newTeacher);
	List<Teacher> getTeachersBySurname(String surname);
	Teacher getTeacherById(Object id);
	List<Teacher> getAllTeachers();
}

