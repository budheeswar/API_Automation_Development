package udb.model;

public class User {

	public String name;
	public String gender;
	public String email;
	public String status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User(String name, String gender, String email, String status) {
		super();
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.status = status;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
