package gr.aueb.elearn.teacherapp.dto;

import gr.aueb.elearn.teacherapp.model.Users;

public class UserDTO {

	private Long Id;
	private String username;
	private String password;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String username, String password) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void insert(Users users) {
		// TODO Auto-generated method stub
		
	}

}
