import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Session {
	private User user;
	private boolean logged;
	private String FILE_PATH = "/media/dam23-25/VICTOR ZHOU/DAM/Programacion/Programas/Java/ProyectoLaberinto/";
	private String USERS_FILE = "logins.txt";

	public Session() {
		logged = false;
	}

	public boolean isLogged() {
		return logged;
	}

	public String getFILE_PATH() {
		return FILE_PATH;
	}

	public String getUSERS_FILE() {
		return USERS_FILE;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}

	public void setUSERS_FILE(String uSERS_FILE) {
		USERS_FILE = uSERS_FILE;
	}

	public boolean login() {
		boolean ok = false;
		boolean isUsername = false;
		Scanner sc = new Scanner(System.in);

		System.out.print("Introduzca su nombre de usuario: ");
		String username = sc.nextLine();

		try (BufferedReader reader = new BufferedReader(new FileReader(getUSERS_FILE()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(":");
				if (username.equals(fields[0])) {
					isUsername = true;
					System.out.print("Introduzca su contraseña: ");
					String pass = sc.nextLine();
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
		if (!isUsername) {
			System.out.println("No se ha encontrado este usuario");
		}
		return ok;
	}

	public boolean signup() {
		Scanner sc = new Scanner(System.in);
		boolean end = false;
		boolean ok = false;
		boolean repeat = false;

		do {
			System.out.print("Introduzca un nombre de usuario: ");
			String username = sc.nextLine();
			System.out.print("Introduzca una contraseña: ");
			String pass = sc.nextLine();

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

	public void logout() {
		setLogged(false);
		user = null;
		System.out.println("Se ha cerrado sesion correctamente");
	}
}
