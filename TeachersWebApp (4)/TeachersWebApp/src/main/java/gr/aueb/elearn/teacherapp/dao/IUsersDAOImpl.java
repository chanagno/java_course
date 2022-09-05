package gr.aueb.elearn.teacherapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.model.Users;
import gr.aueb.elearn.teacherapp.service.util.JPAHelper;

public class IUsersDAOImpl implements IUsersDAO {

	@Override
	public void insert(Users user) {
		EntityManager em = getEntityManager();
		em.persist(user);
	}
	@Override
	public void delete(Object id) {
		EntityManager em = getEntityManager();
		Users toDelete = getUserById(id);
		em.remove(toDelete);
	}
	@Override
	public void update(Users user){
		getEntityManager().merge(user);
	}
	@Override
	public Users getUserById (Object id) {
		EntityManager em = getEntityManager();
		return em.find(Users.class, id);
	}	
	
	@Override
	public List<Users> getUsersByUsernameAndPassword(String username,String password) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder(); 
    	CriteriaQuery<Users> selectQuery = builder.createQuery(Users.class);
    	Root<Users> root = selectQuery.from(Users.class);
    	
    	Predicate predicateUsername= builder.equal(root.get("username"), username);
    	Predicate predicatePassword=builder.equal(root.get("password"), password);
    	Predicate predicateUserAnPass=builder.and(predicateUsername,predicatePassword);
    	/*ParameterExpression<String> val = builder.parameter(String.class, "Username");
    	List <USers> users=get
    	selectQuery.select(root).where(builder.like(root.get("password"), val));
    	
		TypedQuery<Users> query = getEntityManager().createQuery(selectQuery);
		query.setParameter("Username", username + "%");
		query.setParameter("Password", password + "%");
		*/
    	selectQuery.where(predicateUserAnPass);
    	TypedQuery<Users> query=  getEntityManager().createQuery(selectQuery);
		return query.getResultList();
	}
	@Override
	public List<Users> getUsersByUsername(String username) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder(); 
    	CriteriaQuery<Users> selectQuery = builder.createQuery(Users.class);
    	Root<Users> root = selectQuery.from(Users.class);
    	
    	
    	
    	
    	ParameterExpression<String> val = builder.parameter(String.class, "Username");
    	selectQuery.select(root).where(builder.like(root.get("password"), val));
    	
		TypedQuery<Users> query = getEntityManager().createQuery(selectQuery);
		query.setParameter("Username", username + "%");
		return query.getResultList();
	}
	
	
	@Override
	public List<Users> getAllUsers() {
		return getUsersByUsername("");
	}
	public EntityManager getEntityManager() {
		return JPAHelper.getEntityManager();
	}
}
