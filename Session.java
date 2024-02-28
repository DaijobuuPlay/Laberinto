import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Clase que gestiona las sesiones de los usuarios como inicio de sesion, registro etc...
 * @author Victor
 *
 */
public class Session {
	/*
	 * Usuario de la sesion
	 */
	private User user;
	/**
	 * Valor booleano que indicia si hay un usuario iniciado
	 */
	private boolean logged;
	/**
	 * Ruta del archivo con la informacion de registro de los usuarios
	 */
	private String FILE_PATH = "/media/dam23-25/VICTOR ZHOU/DAM/Programacion/Programas/Java/ProyectoLaberinto/";
	/**
	 * Nombre del archivo de la informacion de registro de los usuarios
	 */
	private String USERS_FILE = "logins.txt";

	/**
	 * Constructor que inicializa una sesion declarando que no esta iniciada la sesion de usuario
	 */
	public Session() {
		logged = false;
	}

	/**
	 * Devuevle si hay un usuario iniciado o no
	 * @return true si hay un usuario iniciado y false si no es el caso
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * Devuelve la ruta del archivo de informacion de los usuarios
	 * @return Ruta del archivo de informacion de los usuarios
	 */
	public String getFILE_PATH() {
		return FILE_PATH;
	}

	/**
	 * Devuelve el nombre del archivo de informacion de los usuarios
	 * @return Nombre del archivo de informacion de los usuarios
	 */
	public String getUSERS_FILE() {
		return USERS_FILE;
	}

	/**
	 * Establece si hay un usuario iniciado
	 * @param logged Valor booleano que indica el estado de la sesion
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/**
	 * Establece la ruta del archivo de usuarios
	 * @param fILE_PATH Ruta del archivo de usuarios
	 */
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}

	/**
	 * Establece el nombre del archivo de usuarios
	 * @param uSERS_FILE Nombre del archivo de usuarios
	 */
	public void setUSERS_FILE(String uSERS_FILE) {
		USERS_FILE = uSERS_FILE;
	}

	/**
	 * Metodo que inicia sesion del usuario
	 * @return true si se ha iniciado, false si no se pudo iniciar sesion
	 */
	public boolean login() {
		boolean ok = false;
		boolean isUsername = false;
		Scanner sc = new Scanner(System.in);

		//Pide un usuario y lo busca en el archivo de usuarios
		System.out.print("Introduzca su nombre de usuario: ");
		String username = sc.nextLine();

		try (BufferedReader reader = new BufferedReader(new FileReader(getUSERS_FILE()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(":");
				//Si encuentra el usuario pide la contraseña de dicho usuario
				if (username.equals(fields[0])) {
					isUsername = true;
					System.out.print("Introduzca su contraseña: ");
					String pass = sc.nextLine();
					//Si coincide la contraseña establece la informacion del usuario al atributo user
					if (pass.equals(fields[1])) {
						setLogged(true);
						user = new User(fields[0], fields[2], fields[3], fields[4], fields[5], fields[6]);
						ok = true;
					} else {
						System.out.println("Contraseña incorrecta");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//En caso de no encontrar el usuario indica que no existe
		if (!isUsername) {
			System.out.println("No se ha encontrado este usuario");
		}
		return ok;
	}

	/**
	 * Metodo que registra un usuario y lo guarda en el archivo de usuarios
	 * @return
	 */
	public boolean signup() {
		Scanner sc = new Scanner(System.in);
		boolean end = false;
		boolean ok = false;
		boolean repeat = false;

		//Pide nombre de usuario y contraseña para agregarla al archivo de usuarios
		do {
			System.out.print("Introduzca un nombre de usuario: ");
			String username = sc.nextLine();
			System.out.print("Introduzca una contraseña: ");
			String pass = sc.nextLine();

			//Busca si el nombre de usuario ya existe en el archivo
			try (BufferedReader reader = new BufferedReader(new FileReader(getUSERS_FILE()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] fields = line.split(":");
					if (username.equals(fields[0])) {
						repeat = true;
						break;
					} else {
						repeat = false;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (repeat) {
				System.out.println("Este nombre de usuario esta en uso");
				//Si no coincide con ningun nombre, procede a pedirle sus datos personales
			} else {
				System.out.print("Introduzca un nombre: ");
				String name = sc.nextLine();
				System.out.print("Introduzca su NIF: ");
				String nif = sc.nextLine();
				System.out.print("Introduzca su email: ");
				String email = sc.nextLine();
				System.out.print("Introduzca su direccion: ");
				String address = sc.nextLine();
				System.out.print("Introduzca su fecha de nacimiento: ");
				String birthday = sc.nextLine();

				//Guarda los datos en el archivo de usuarios
				try {
					FileWriter writer = new FileWriter(getUSERS_FILE(), true);
					writer.write(username + ":" + pass + ":" + name + ":" + nif + ":" + email + ":" + address + ":"
							+ birthday + System.getProperty("line.separator"));
					writer.close();
					end = true;
					ok = true;
				} catch (IOException ew) {
					ew.printStackTrace();
				}
			}

		} while (!end);

		return ok;

	}

	/**
	 * Muestra la informacion del usuario que tenga iniciada la sesion
	 */
	public void showUser() {
		if (isLogged()) {
			System.out.println("Datos del usuario: " + user.getUsername());
			System.out.println("Nombre: " + user.getName());
			System.out.println("NIF: " + user.getNif());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Direccion: " + user.getAddress());
			System.out.println("Fecha de nacimiento: " + user.getBirthdate());
		} else {
			System.out.println("No hay ninguna sesion iniciada");
		}

	}

	/**
	 * Cierra la sesion iniciada
	 */
	public void logout() {
		setLogged(false);
		user = null;
		System.out.println("Se ha cerrado sesion correctamente");
	}
}
