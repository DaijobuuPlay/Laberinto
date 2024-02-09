import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println(Config.welcome);
		System.out.println(Config.version);
		System.out.println("");

		Scanner sc = new Scanner(System.in);
		Session session = new Session();
		Maze maze = new Maze();
		int opcion = 0;
		boolean terminar = false;
		boolean terminarTry = false;
		do {
			if (session.isLogged() == false) {
				do {
					terminarTry = false;
					try {
						System.out.println(Config.unlogged_menu);
						System.out.print("Seleccione una opcion: ");
						opcion = sc.nextInt();
						System.out.println("");
						terminarTry = true;
					} catch (Exception e) {
						System.out.println("Introduce una opcion valida");
						sc.nextLine();
						terminarTry = false;
					}
				} while (terminarTry == false);

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
					System.out.println("Introduce una opcion valida");
					break;
				}
			} else {

				do {
					terminarTry = false;
					try {
						System.out.println(Config.logged_menu);
						System.out.print("Seleccione una opcion: ");
						opcion = sc.nextInt();
						System.out.println("");
						terminarTry = true;
					} catch (Exception e) {
						System.out.println("Introduce una opcion valida");
						sc.nextLine();
					}
				} while (terminarTry == false);

				switch (opcion) {
				case 0:
					terminar = true;
					break;
				case 1:
					if(maze.loadMaze() == true){
						System.out.println("Se ha cargado el laberinto correcatemente");
					}else{
						System.out.println("No se ha podido cargar el laberinto");
					}
					break;
				case 2:
					maze.showMaze();
					break;
				case 3:
					maze.setStartEnd();
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
					System.out.println("Seleccione una opcion valida");
					break;

				}
			}
		} while (terminar == false);
	}
}
