package gr.aueb.elearn.teacherapp.dao;

import java.util.List;

import gr.aueb.elearn.teacherapp.model.Users;

public interface IUsersDAO {
	void insert(Users user);
	void delete(Object id);
	void update(Users user);
	List<Users> getAllUsers();
	
	Users getUserById(Object id);
	
	List<Users> getUsersByUsernameAndPassword(String username, String password);
	List<Users> getUsersByUsername(String username);
}
