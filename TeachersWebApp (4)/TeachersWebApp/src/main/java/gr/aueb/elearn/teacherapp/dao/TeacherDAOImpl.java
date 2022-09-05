package gr.aueb.elearn.teacherapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.service.util.JPAHelper;

public class TeacherDAOImpl implements ITeacherDAO {
	
	@Override
	public void insert(Teacher teacher) {
		EntityManager em = getEntityManager();
		em.persist(teacher);
	}

	@Override
	public void delete(Object id) {
		EntityManager em = getEntityManager();
		Teacher toDelete = getTeacherById(id);
		em.remove(toDelete);
	}

	@Override
	public void update(Teacher newTeacher){
		getEntityManager().merge(newTeacher);
	}
	
	@Override
	public List<Teacher> getTeachersBySurname(String surname) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder(); 
    	CriteriaQuery<Teacher> selectQuery = builder.createQuery(Teacher.class);
    	Root<Teacher> root = selectQuery.from(Teacher.class);
    	
    	ParameterExpression<String> val = builder.parameter(String.class, "Teachersurname");
    	selectQuery.select(root).where(builder.like(root.get("lastName"), val));
    	
		TypedQuery<Teacher> query = getEntityManager().createQuery(selectQuery);
		query.setParameter("Teachersurname", surname + "%");
		return query.getResultList();
	}

	@Override
	public Teacher getTeacherById(Object id) {
		EntityManager em = getEntityManager();
		return em.find(Teacher.class, id);
	}	
	
	@Override
	public List<Teacher> getAllTeachers() {
		return getTeachersBySurname("");
	}

	public EntityManager getEntityManager() {
		return JPAHelper.getEntityManager();
	}
}


/*
 * @Override public List<Teacher> getTeachersBySurname(String surname) {
 * CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
 * CriteriaQuery<Teacher> selectQuery = builder.createQuery(Teacher.class);
 * Root<Teacher> root = selectQuery.from(Teacher.class);
 * selectQuery.select(root).where(builder.like(root.get("lastName"), surname +
 * "%")); TypedQuery<Teacher> query =
 * getEntityManager().createQuery(selectQuery); return query.getResultList(); }
 */