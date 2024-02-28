/**
 * Clase que contiene la informacion de un usuario
 * @author Victor
 *
 */
public class User {
	/**
	 * Nombre de usuario
	 */
	private String username;
	/**
	 * Nombre del usuario
	 */
	private String name;
	/**
	 * NIF del usuario
	 */
	private String nif;
	/**
	 * Email del usuario
	 */
	private String email;
	/**
	 * Direccion del usuario
	 */
	private String address;
	/**
	 * Fecha de nacimiento del usuario
	 */
	private String birthdate;
	/**
	 * Rol del usuario (De momento no se usa)
	 */
	private String role = null;

	/**
	 * Constructor de un usuario
	 */
	public User(String username, String name, String nif, String email, String address, String birthdate) {
		this.username = username;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
	}

	/**
	 * Devuelve el nombre de usuario
	 * @return Nombre de usuario
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Devuelve el nombre del usuario
	 * @return Nombre del usuario
	 */
	public String getName() {
		return name;
	}

	/**
	 * Devuelve el NIF del usuario
	 * @return NIF del usuario
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Devuelve el email del usuario
	 * @return Email del usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Devuelve el Direccion de lusuario
	 * @return Direccion de usuario
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Devuelve la fecha de nacimiento del usuario
	 * @return Fecha de nacimiento del usuario
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * Devuelve el rol del usuario
	 * @return Rol del usuario
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Establece el nombre de usuario
	 * @param username Nombre de usuario
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Establece el nombre del usuario
	 * @param name Nombre del usuario
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Establece el NIF del usuario
	 * @param nif NIF del usuario
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * Establece el email del usuario
	 * @param email Email del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Establece la direccion del usuario
	 * @param address Direccion del usuario
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Establece la fecha de nacimiento del usuario
	 * @param birthdate Fecha de nacimiento del usuario
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Establece el rol del usuario
	 * @param role Rol del usuario
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
