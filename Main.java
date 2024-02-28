import java.util.Scanner;

public class Main {
	
	private static boolean terminar;
	
	public static void main(String[] args) {
		System.out.println(Config.welcome);
		System.out.println(Config.version);
		System.out.println("");

		Session session = new Session();
		Maze maze = new Maze();
		terminar = false;
		
		do {
			if (!session.isLogged()) {
				unloggedMenu(session);
			} else {
				loggedMenu(session, maze);
			}
		} while (!terminar);
	}
	
	static void unloggedMenu(Session session) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		boolean terminarTry = false;
		do {
			terminarTry = false;
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
		case 0:
			terminar = true;
			break;
		case 1:
			session.login();
			break;
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
	
	static void loggedMenu(Session session, Maze maze) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		boolean terminarTry = false;
		
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
		case 0:
			terminar = true;
			break;
		case 1:
			if(maze.loadMaze()){
				System.out.println("Se ha cargado el laberinto correcatemente");
			}else{
				System.out.println("No se ha podido cargar el laberinto");
			}
			break;
		case 2:
			maze.showMaze();
			break;
		case 3:
			if(maze.setStartEnd()) {
				System.out.println("Coordenadas establecidas correctamente");
			}else {
				System.out.println("No se ha podido establecer las coordenadas");
			}
			break;
		case 4:
			System.out.println("Proximamente");
			break;
		case 5:
			session.showUser();
			break;
		case 6:
			session.logout();
			maze.freeAll();
			break;
		default:
			System.out.println(Config.option_error);
			break;

		}
	}
}
