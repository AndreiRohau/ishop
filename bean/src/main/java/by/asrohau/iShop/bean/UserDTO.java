package by.asrohau.iShop.bean;

public class UserDTO {

	private int id;
	private String login;

	public UserDTO() {}
	
	public UserDTO(String login) {
		super();
		this.login = login;
	}
	
	public UserDTO(User user) {
		super();
		this.login = user.getLogin();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserDTO userDTO = (UserDTO) o;

		if (id != userDTO.id) return false;
		return login != null ? login.equals(userDTO.login) : userDTO.login == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (login != null ? login.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", login='" + login + '\'' +
				'}';
	}
}
