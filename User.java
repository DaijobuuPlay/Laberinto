public class User {
	private String username;
	private String name;
	private String nif;
	private String email;
	private String address;
	private String birthdate;
	private String role = null; // No se usara de momento

	public User(String username, String name, String nif, String email, String address, String birthdate) {
		this.username = username;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getNif() {
		return nif;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getRole() {
		return role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
