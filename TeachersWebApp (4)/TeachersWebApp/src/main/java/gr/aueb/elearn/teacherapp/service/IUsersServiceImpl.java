package gr.aueb.elearn.teacherapp.service;

import java.util.List;

import gr.aueb.elearn.teacherapp.dao.IUsersDAO;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;
import gr.aueb.elearn.teacherapp.dto.UserDTO;
import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.model.Users;
import gr.aueb.elearn.teacherapp.service.exceptions.EntityNotFoundException;
import gr.aueb.elearn.teacherapp.service.util.JPAHelper;

public class IUsersServiceImpl implements IUsersService{
	private final IUsersDAO usersDAO;
	
	
	public IUsersServiceImpl(IUsersDAO UsersDAO ) {
		this.usersDAO =UsersDAO;
	}

	public IUsersServiceImpl() {
		this.usersDAO = null;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Users> getUsersByUsername(String username)throws EntityNotFoundException {	
		List<Users> Users = null; 
		try {
			JPAHelper.beginTransaction();		
			Users = usersDAO.getUsersByUsername(username);
			if (Users.isEmpty()) throw new EntityNotFoundException(List.class, 0L);
		} catch (EntityNotFoundException e) {
			JPAHelper.rollbackTransaction();
			throw e;
		} finally {
			JPAHelper.closeEntityManager();
		}
		
		return Users;
	}
	
	@Override
	public List<Users> getUsersByUsernameandPass(String username, String password) throws EntityNotFoundException {	
		List<Users> Users = null; 
		
		try {
			JPAHelper.beginTransaction();		
			Users = usersDAO.getUsersByUsernameAndPassword(username,password);
			if (Users.isEmpty()) throw new EntityNotFoundException(List.class, 0L);
		} catch (EntityNotFoundException e) {
			JPAHelper.rollbackTransaction();
			throw e;
		} finally {
			JPAHelper.closeEntityManager();
		}
		
		return Users;
	}
	@Override
	public void insertUsers(UserDTO UsersDTO) {
		Users users;	
		
		try {	
			JPAHelper.beginTransaction();
			
			users = new Users();
			users.setUsername(UsersDTO.getUsername());
			users.setPassword(UsersDTO.getPassword());	
			
			if (UsersDTO.getId() == null) 
		
				usersDAO.insert(users);

			JPAHelper.commitTransaction();
		} catch (Exception e) {
			JPAHelper.rollbackTransaction();
			throw e;
		} finally {
			JPAHelper.closeEntityManager();
		}
	}

	@Override
	public void updateUsers(UserDTO userDTO)throws EntityNotFoundException {
		// TODO Auto-generated method stub
		Users userToUpdate;
		try {
			JPAHelper.beginTransaction();
			userToUpdate = new Users();
			userToUpdate.setId(userDTO.getId());
			userToUpdate.setUsername(userDTO.getUsername());
			userToUpdate.setPassword(userDTO.getPassword());	
			
			if ((usersDAO.getUserById(userToUpdate.getId())) == null) 
				throw new EntityNotFoundException(Users.class, userToUpdate.getId());
			
			usersDAO.update(userToUpdate);
			JPAHelper.commitTransaction();
		}
		catch (EntityNotFoundException e) {
			JPAHelper.rollbackTransaction();
		} finally {
			JPAHelper.closeEntityManager();
		}
	}

	@Override
	public void updateUsersPass(UserDTO userDTO, String pass) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		Users userToUpdate;
		try {
			JPAHelper.beginTransaction();
			userToUpdate = new Users();
			userToUpdate.setId(userDTO.getId());
			userToUpdate.setUsername(userDTO.getUsername());
			userToUpdate.setPassword(pass);	
			
			if ((usersDAO.getUserById(userToUpdate.getId())) == null) 
				throw new EntityNotFoundException(Users.class, userToUpdate.getId());
			
			usersDAO.update(userToUpdate);
			JPAHelper.commitTransaction();
		}
		catch (EntityNotFoundException e) {
			JPAHelper.rollbackTransaction();
		} finally {
			JPAHelper.closeEntityManager();
		}
	}

	@Override
	public Users getUserIdByUsername(String eMail) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public Users getUserIdByUsername(String eMail) throws EntityNotFoundException{
		Users Users; 
		try {
			JPAHelper.beginTransaction();		
			Users = usersDAO.getUsersByUsername(eMail);
			if (Users.isEmpty()) throw new EntityNotFoundException(List.class, 0L);
		} catch (EntityNotFoundException e) {
			JPAHelper.rollbackTransaction();
			throw e;
		} finally {
			JPAHelper.closeEntityManager();
		}
		
		return Users;
	}*/
	}
	
	
	

