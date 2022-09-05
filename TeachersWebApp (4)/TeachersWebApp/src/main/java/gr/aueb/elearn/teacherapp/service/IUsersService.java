package gr.aueb.elearn.teacherapp.service;
import java.util.List;

import gr.aueb.elearn.teacherapp.dto.UserDTO;
import gr.aueb.elearn.teacherapp.model.Users;
import gr.aueb.elearn.teacherapp.service.exceptions.EntityNotFoundException;

public interface IUsersService {

	//void insertUsers ()
	
	
	List<Users> getUsersByUsername (String username)	throws EntityNotFoundException;

	List<Users> getUsersByUsernameandPass(String username, String password) throws EntityNotFoundException;

	void insertUsers(UserDTO UsersDTO) throws Exception;

	void updateUsers(UserDTO userDTO) throws EntityNotFoundException;


	void updateUsersPass(UserDTO userDTO, String pass)throws EntityNotFoundException;

	Users getUserIdByUsername(String eMail) throws EntityNotFoundException;
	
	
}
