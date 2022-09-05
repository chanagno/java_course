package gr.aueb.elearn.teacherapp.service;

import java.util.List;
import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;
import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.service.exceptions.EntityNotFoundException;

import gr.aueb.elearn.teacherapp.service.util.JPAHelper;

public class TeacherServiceImpl implements ITeacherService {
	
	private final ITeacherDAO teacherDAO;
	
	public TeacherServiceImpl(ITeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	@Override
	public void insertTeacher(TeacherDTO teacherDTO) {
		Teacher teacher;	
		try {	
			JPAHelper.beginTransaction();
			
			teacher = new Teacher();
			teacher.setLastName(teacherDTO.getLastName());
			teacher.setFirstName(teacherDTO.getFirstName());	
			
			if ((teacherDTO.getId()) == null) 
				teacherDAO.insert(teacher);
			else {
				// An option would be to call
				// teacherDAO.update(teacher);
				// otherwise do nothing
			}
			JPAHelper.commitTransaction();
		} catch (Exception e) {
			JPAHelper.rollbackTransaction();
			throw e;
		} finally {
			JPAHelper.closeEntityManager();
		}
	}

	@Override
	public void deleteTeacher(TeacherDTO teacherDTO) 
			throws EntityNotFoundException {
		Teacher teacherToDelete;
		try {
			JPAHelper.beginTransaction();
			
			teacherToDelete = new Teacher();
			teacherToDelete.setId(teacherDTO.getId());
			
			if ((teacherDAO.getTeacherById(teacherToDelete.getId())) == null) 
				throw new EntityNotFoundException(Teacher.class, teacherToDelete.getId());
			
			teacherDAO.delete(teacherToDelete.getId());
			
			
			JPAHelper.commitTransaction();
		} catch (EntityNotFoundException e) {
			JPAHelper.rollbackTransaction();
		} finally {
			JPAHelper.closeEntityManager();
		}
	}
	
	@Override
	public void updateTeacher(TeacherDTO newTeacherDTO) 
			throws EntityNotFoundException {
		
		Teacher teacherToUpdate;
		try {
			JPAHelper.beginTransaction();
			teacherToUpdate = new Teacher();
			teacherToUpdate.setId(newTeacherDTO.getId());
			teacherToUpdate.setLastName(newTeacherDTO.getLastName());
			teacherToUpdate.setFirstName(newTeacherDTO.getFirstName());	
			
			if ((teacherDAO.getTeacherById(teacherToUpdate.getId())) == null) 
				throw new EntityNotFoundException(Teacher.class, teacherToUpdate.getId());
			
			teacherDAO.update(teacherToUpdate);
			JPAHelper.commitTransaction();
		}
		catch (EntityNotFoundException e) {
			JPAHelper.rollbackTransaction();
		} finally {
			JPAHelper.closeEntityManager();
		}
	}

	@Override
	public List<Teacher> getTeachersBySurname(String surname)
			throws EntityNotFoundException {	
		List<Teacher> teachers = null; 
		try {
			JPAHelper.beginTransaction();		
			teachers = teacherDAO.getTeachersBySurname(surname);
			if (teachers.isEmpty()) throw new EntityNotFoundException(List.class, 0L);
		} catch (EntityNotFoundException e) {
			JPAHelper.rollbackTransaction();
			throw e;
		} finally {
			JPAHelper.closeEntityManager();
		}
		
		return teachers;
	}
}

