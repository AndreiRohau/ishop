package by.asrohau.iShop.bean;

public class User extends Base {

	private String login;
	private String password;
	private String role;

	public User(){}

	public User(long id) {
		super(id);
	}

	public User(String login) {
		this.login = login;
	}

	public User(long id, String login) {
		super(id);
		this.login = login;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(long id, String login, String password) {
		super(id);
		this.login = login;
		this.password = password;
	}

	public User(String login, String password, String role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public User(long id, String login, String password, String role) {
		super(id);
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		User user = (User) o;

		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		return role != null ? role.equals(user.role) : user.role == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				super.toString() +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}