
import java.util.Scanner;

/**
 * Clase Main donde se ejecuta todos los metodos de las distintas clases y se
 * muestra el laberinto
 * 
 * @author Victor
 *
 */
public class Main {

	/**
	 * Variable que indica si queremos terminar el juego
	 */
	private static boolean terminar;

	/**
	 * Metodo main donde se ejecuta el menu y los metodos relacionados
	 */
	public static void main(String[] args) {
		// Se muestra mensaje de bienvenida y su version
		System.out.println(Config.welcome);
		System.out.println(Config.version);
		System.out.println("");

		// Iniciamos una nueva sesion e inicializamos un laberinto
		Session session = new Session();
		Maze maze = new Maze();
		terminar = false;
		

		// Mostramos los menu dependiendo de la sesion que haya en el momento
		do {
			if (!session.isLogged()) {
				unloggedMenu(session);
			} else {
				loggedMenu(session, maze);
			}
		} while (!terminar);
	}

	/**
	 * Metodo que muestra el menu para inicio y registro de usuario
	 * 
	 * @param session Sesion a la que queremos gestionar el inicio de sesion del
	 *                usuario
	 */
	static void unloggedMenu(Session session) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		boolean terminarTry = false;
		do {
			terminarTry = false;
			// Pide una opcion del menu
			try {
				System.out.println(Config.unlogged_menu);
				System.out.print("Seleccione una opcion: ");
				opcion = sc.nextInt();
				System.out.println("");
				terminarTry = true;
			} catch (Exception e) {
				System.out.println(Config.option_error);
				sc.nextLine();
				terminarTry = false;
			}
		} while (!terminarTry);

		switch (opcion) {
			// Termina la sesion
			case 0:
				System.out.println(Config.goodbye);
				terminar = true;
				break;
			// Inicia la sesion
			case 1:
				session.login();
				break;
			// Registra un usuario
			case 2:
				if (session.signup()) {
					System.out.println("Se creo correctamente el usuario");
				}
				break;
			default:
				System.out.println(Config.option_error);
				break;
		}
	}

	/**
	 * Metodo que muestra el menu con una sesion iniciada y permite manipular el
	 * laberinto
	 * 
	 * @param session Sesion que estamos gestionando
	 * @param maze    Laberinto que usamos para la sesion
	 */
	static void loggedMenu(Session session, Maze maze) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		boolean terminarTry = false;
		boolean casillas = false;

		// Pedimos una opcion valida
		do {
			terminarTry = false;
			try {
				System.out.println(Config.logged_menu);
				System.out.print("Seleccione una opcion: ");
				opcion = sc.nextInt();
				System.out.println("");
				terminarTry = true;
			} catch (Exception e) {
				System.out.println(Config.option_error);
				sc.nextLine();
			}
		} while (!terminarTry);

		switch (opcion) {
			// Terminamos la sesion
			case 0:
				terminar = true;
				break;
			// Cargamos el laberinto
			case 1:
				if (maze.loadMaze()) {
					System.out.println("Se ha cargado el laberinto correcatemente");
				} else {
					System.out.println("No se ha podido cargar el laberinto");
				}
				break;
			// Mostramos el laberinto
			case 2:
				maze.showMaze();
				break;
			// Establecemos las coordenadas de inicio y final
			case 3:
				if (maze.setStartEnd()) {
					System.out.println("Coordenadas establecidas correctamente");
					casillas = true;
				} else {
					System.out.println("No se ha podido establecer las coordenadas");
				}
				break;
			// Mostramos la solucion del laberinto(No implementado)
			case 4:
					pathFinderMenu(session, maze);

				break;
			// Mostramos la informacion del usuario iniciado
			case 5:
				session.showUser();
				break;
			/* Cerramos la sesion y liberamos el laberinto */
			case 6:
				session.logout();
				maze.freeAll();
				break;
			default:
				System.out.println(Config.option_error);
				break;

		}
	}

	static void pathFinderMenu(Session session, Maze maze) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		boolean terminarTry = false;
		if(maze.getStartI() <= 0){
			System.out.println("Establece las casillas de Inicio y Fin");
			return;
		}
		do {
			terminarTry = false;
			// Pide una opcion del menu
			try {
				System.out.println(Config.path_finder);
				System.out.print("Seleccione una opcion: ");
				opcion = sc.nextInt();
				System.out.println("");
				terminarTry = true;
			} catch (Exception e) {
				System.out.println(Config.option_error);
				sc.nextLine();
				terminarTry = false;
			}
		} while (!terminarTry);

		switch (opcion) {
			case 0:
				break;
			case 1:
				try {
					maze.firstPath();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				break;
			case 2:
			try {
				maze.fastestPath();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				break;
		}
	}
}
