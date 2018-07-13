package by.asrohau.iShop.bean;

public class UserDTO extends Base {

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
		if (!super.equals(o)) return false;

		UserDTO userDTO = (UserDTO) o;

		return login != null ? login.equals(userDTO.login) : userDTO.login == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (login != null ? login.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				super.toString() +
				"login='" + login + '\'' +
				'}';
	}
}
