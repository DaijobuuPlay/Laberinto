import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.lang.Thread;

/**
 * Clase Maze donde incluye todas las funciones relacionadas a la manipulacion
 * del laberinto
 * 
 * @author Victor
 */
public class Maze {

	/**
	 * Mapa del laberinto
	 */
	private char[][] map;

	/**
	 * Archivo donde se carga el laberinto
	 */
	private String filename;
	/**
	 * Variable que indica si hay un laberinto cargado
	 */
	private boolean loaded = false;
	/**
	 * Variable que indica la posicion del punto I del comienzo
	 */
	private int startI;
	/**
	 * Variable que indica la posicion del punto J del comienzo
	 */
	private int startJ;
	/**
	 * Variable que indica la posicion del punto I del final
	 */
	private int endI;
	/**
	 * Variable que indica la posicion del punto J del final
	 */
	private int endJ;

	/**
	 * Lista de pasos que da el resolvedor del camino
	 */
	private Stack<Coordinate> path;

	/**
	 * Constructor del laberinto. Solamente indica que no hay ningun
	 * laberinto cargado
	 */
	public Maze() {
		setLoaded(false);
		path = new Stack<Coordinate>();
	}

	/**
	 * Devuelve el mapa del laberinto
	 * 
	 * @return mapa del laberinto que contenga el atributo map
	 */
	public char[][] getMap() {
		return map;
	}

	/**
	 * Establece el mapa del laberinto
	 * 
	 * @param map Mapa del laberinto el cual va a ser cargado
	 */
	public void setMap(char[][] map) {
		this.map = map;
	}

	/**
	 * Devuelve el archivo del laberinto
	 * 
	 * @return archivo del laberinto
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Establece el archivo que contiene el laberinto
	 * 
	 * @param filename archivo que contiene el laberinto
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Indica si esta cargado el laberinto
	 * 
	 * @return true si esta cargado y false si no lo esta
	 */
	public boolean isLoaded() {
		return loaded;
	}

	/**
	 * Establece si esta cargado un laberinto o no
	 * 
	 * @param loaded Valor booleano que indica el estado del laberinto
	 */
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	/**
	 * Devuelve la coordenada de la fila del punto del inicio
	 * 
	 * @return indice de la coordenada i del inicio
	 */
	public int getStartI() {
		return startI;
	}

	/**
	 * Establece la coordenada de la fila del punto de inicio
	 * 
	 * @param startI indice de la coordenada i del inicio
	 */
	public void setStartI(int startI) {
		this.startI = startI;
	}

	/**
	 * Devuelve la coordenada de la columna del punto del inicio
	 * 
	 * @return indice de la coordenada j del inicio
	 */
	public int getStartJ() {
		return startJ;
	}

	/**
	 * Establece la coordenada de la columna del punto del inicio
	 * 
	 * @param startJ indice de la coordenada j del inicio
	 */
	public void setStartJ(int startJ) {
		this.startJ = startJ;
	}

	/**
	 * Devuelve la coordenada de la fila del punto del final
	 * 
	 * @return indice de la coordenada i del final
	 */
	public int getEndI() {
		return endI;
	}

	/**
	 * Establece la coordenada de la fila del punto del final
	 * 
	 * @param endI indice de la coordenada i del final
	 */
	public void setEndI(int endI) {
		this.endI = endI;
	}

	/**
	 * Devuelve la coordenada de la columna del punto del final
	 * 
	 * @return indice de la coordenada j del final
	 */
	public int getEndJ() {
		return endJ;
	}

	/**
	 * Establece la coordenada de la columna del punto del final
	 * 
	 * @param endJ indice de la coordenada j del final
	 */
	public void setEndJ(int endJ) {
		this.endJ = endJ;
	}

	public Stack<Coordinate> getPath() {
		return path;
	}

	public void setPath(Stack<Coordinate> path) {
		this.path = path;
	}

	/**
	 * Lee los archivos de la carpeta "laberintos" e indica que archivo de laberinto
	 * queremos
	 * que se cargue. Al elegir el archivo llama al metodo fileMaze y carga el
	 * laberinto en
	 * su respectivo atributo
	 * 
	 * @return true si ha cargado correctamente y false si ha habido un error
	 */
	public boolean loadMaze() {
		// Libera todo antes de cargar un nuevo laberinto en el caso de que hubiera uno
		// cargado anteriormente
		freeAll();
		// Inicializacion de variables
		boolean ok = false;
		boolean archivo = false;
		Scanner sc = new Scanner(System.in);
		// Indicamos donde se encuentra los archivos de laberintos
		File folder = new File("laberintos/");
		File[] listOfFiles = folder.listFiles();
		// Mostramos los archivos y pedimos que elija uno de la carpeta. No para hasta
		// que se haya elegido un archivo valido
		do {
			archivo = false;
			if(listOfFiles.length <= 0){
				System.out.println("No hay archivos de laberintos en la carpeta");
				return false;
			}else{
				for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(i + ". " + listOfFiles[i].getName());
				}
			}
			System.out.print("Seleccione el archivo que contenga el laberinto: ");
			int opcion = sc.nextInt();
			if (opcion < listOfFiles.length) {
				// Al elegir el archivo se guada en FileName
				setFilename(listOfFiles[opcion].getPath());
				archivo = true;
			} else {
				System.out.println("Selecciona un archivo valido");
			}
			}
			
		} while (!archivo);
		/*
		 * Establecemos como mapa el mapa del archivo habiendolo construido con
		 * con el metodo fileMaze
		 */
		setMap(fileMaze(filename));
		setLoaded(true);
		if (map != null) {
			ok = true;
		} else {
			ok = false;
		}
		return ok;
	}

	/**
	 * Comprueba si hay un mapa cargado y si fuera el caso, reenderiza en las
	 * casillas
	 * de inicio una I y final una J, y muestra el mapa
	 */
	public void showMaze() {
		char[] coords = { ' ', '^', '>', 'v', '<' };
		if (map == null) {
			System.out.println("Debe cargar antes un laberinto");
		} else {
			map[0][0] = ' ';

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {

					if (i == startI && j == startJ) {
						if (i > 0 || j > 0) {
							System.out.print("I");
						}else{
							System.out.print(" ");
						}

					} else if (i == endI && j == endJ) {
						if (i > 0 || j > 0) {
							System.out.print("F");
						}else{
							System.out.print(" ");
						}
					} else if (map[i][j] == ' ') {
						System.out.print(coords[getDirection(i, j)]);
					} else {
						System.out.print(map[i][j]);
					}

					if (j != 0) {
						System.out.print(' ');
					}
				}
				System.out.println("");
			}
		}
	}

	private int getDirection(int i, int j) {
		Coordinate aux = new Coordinate(i, j, 0);
		for (int x = 0; x < path.size(); x++) {
			if (path.get(x).equals(aux)) {
				return path.get(x).direction;
			}
		}
		return 0;
	}

	/**
	 * Recibe un archivo y lee el archivo para cargar el laberinto y guardarlo en
	 * en una matriz. Ademas tambien establece la enumeracion de sus filas y
	 * columnas
	 * 
	 * @param archivo Archivo del que se cargara el laberinto
	 * @return Matriz el cual se guarda el laberinto
	 */
	private char[][] fileMaze(String archivo) {
		int i = 0;
		char[][] mazeLine = new char[100][100];
		char[][] laberinto = null;
		// Lectura del fichero
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String line;
			int lineLength = 0;

			// Guarda el laberinto en bruto en mazeLine
			while ((line = reader.readLine()) != null) {
				// Marca la longitud maxima del laberinto
				if (lineLength < line.length()) {
					lineLength = line.length();
				}
				mazeLine[i] = characterRead(line);
				i++;
			}

			/*
			 * Crea una matriz del laberinto con los datos que ha recogido al leer el
			 * fichero
			 * y se añade unos espacios mas para la enumaracion
			 */
			laberinto = new char[i + 2][lineLength + 2];
			for (int j = 0; j < i; j++) {
				laberinto[j + 2] = mazeLine[j];
			}

			// Establece espacio el cuadrado donde no va a haber ningun caracter
			for (int j = 0; j < 2; j++) {
				laberinto[0][j] = ' ';
				laberinto[1][j] = ' ';
			}
			// Enumera las columnas
			for (int j = 2; j < laberinto[1].length; j++) {
				laberinto[1][j] = Character.forDigit(j - 1, 10);
				if (j - 1 < 10) {
					laberinto[0][j] = ' ';
				} else {
					laberinto[0][j] = Character.forDigit((j - 1) / 10, 10);
					laberinto[1][j] = Character.forDigit((j - 1) % 10, 10);
				}
			}

			// Enumera las filas
			for (int j = 2; j < laberinto.length; j++) {
				laberinto[j][1] = Character.forDigit(j - 1, 10);
				if (j - 1 < 10) {
					laberinto[j][0] = ' ';
				} else {
					laberinto[j][0] = Character.forDigit((j - 1) / 10, 10);
					laberinto[j][1] = Character.forDigit((j - 1) % 10, 10);
				}
			}

		} catch (IOException e) {
			System.out.println("No existe el archivo");
		}

		return laberinto;
	}

	/**
	 * Lee un string y lo convierte en un array de caracteres añadiendole 2 espacios
	 * la inicio
	 * 
	 * @param line String del laberinto que queremos convertir a char[]
	 * @return char[] con los caracteres del string
	 */
	private char[] characterRead(String line) {
		char[] characters = new char[line.length() + 2];
		for (int i = 0; i < line.length(); i++) {
			characters[i + 2] = line.charAt(i);
		}
		return characters;
	}

	/**
	 * Establece las coordenadas de inicio y final comprobando que sean correctas
	 * 
	 * @return true si se ha establecido correctamente y false si hay un error
	 */
	public boolean setStartEnd() {
		if (map == null) {
			System.out.println("Debe cargar antes un laberinto");
			return false;
		}

		// Si se ha establecido anteriormente limpiamos las coordenadas
		if (startI >= 0) {
			map[startI][startJ] = ' ';
			map[endI][endJ] = ' ';
		}

		Scanner sc = new Scanner(System.in);

		System.out.print("Casilla Inicial I: ");
		int startIaux = sc.nextInt();
		System.out.print("Casilla Inicial J: ");
		int startJaux = sc.nextInt();

		System.out.print("Casilla Final I: ");
		int endIaux = sc.nextInt();
		System.out.print("Casilla Final J: ");
		int endJaux = sc.nextInt();
		if ((startIaux <= map.length - 2 && startJaux <= map[0].length - 2 && endIaux <= map.length - 2
				&& endJaux <= map[0].length - 2)
				&& (map[startIaux + 1][startJaux + 1] != '#' && map[endIaux + 1][endJaux + 1] != '#')) {
			setStartI(startIaux + 1);
			setStartJ(startJaux + 1);
			setEndI(endIaux + 1);
			setEndJ(endJaux + 1);
			path.clear();
		} else {
			return false;
		}

		return true;
	}

	public void firstPath() throws InterruptedException {
		boolean found = false;
		this.path.clear();
		Coordinate coord = new Coordinate(this.startI, this.startJ, 0);
		this.path.add(coord);

		while (!found && !this.path.isEmpty()) {
			if (this.path.peek().direction < 4) {
				this.path.peek().direction++;
			} else {
				this.path.pop();
				continue;
			}
			Coordinate nextCoord = checkPaths(this.path.peek());
			if (nextCoord != null) {
				if (!contains(nextCoord)) {
					if (nextCoord.i == endI && nextCoord.j == endJ) {
						found = true;
					}
					this.path.add(nextCoord);
				} else {
					continue;
				}

			} else {
				continue;
			}

		}
		if (this.path.isEmpty()) {
			System.out.println("No se pudo encontrar un camino posible");
		} else {
			this.showMaze();
			System.out.println("Pasos: " + this.path.size());
		}

	}

	public void fastestPath() throws InterruptedException {
		this.path.clear();
		Coordinate coord = new Coordinate(this.startI, this.startJ, 0);
		this.path.add(coord);
		Stack<Coordinate> fastest = new Stack<>();

		while (!this.path.isEmpty()) {
			if (this.path.peek().direction < 4) {
				this.path.peek().direction++;
			} else {
				this.path.pop();
				continue;
			}
			Coordinate nextCoord = checkPaths(this.path.peek());
			if (nextCoord != null) {
				if (!contains(nextCoord)) {
					if (nextCoord.i == endI && nextCoord.j == endJ) {
						if (fastest.isEmpty() || this.path.size() < fastest.size()) {
							fastest = copyPath(this.path);
						}
					}
					this.path.add(nextCoord);
				} else {
					continue;
				}

			} else {
				continue;
			}

		}

		this.path = fastest;
		if (this.path.isEmpty()) {
			System.out.println("No se pudo encontrar un camino posible");
		} else {
			this.showMaze();
			System.out.println("Pasos: " + this.path.size());
		}
	}

	private Stack<Coordinate> copyPath(Stack<Coordinate> path) {
		Stack<Coordinate> copy = new Stack<Coordinate>();
		for (Coordinate c : path) {
			copy.add(new Coordinate(c.i, c.j, c.direction));
		}
		return copy;
	}

	private boolean contains(Coordinate coord) {
		for (Coordinate c : this.path) {
			if (c.equals(coord)) {
				return true;
			}
		}
		return false;
	}

	private Coordinate checkPaths(Coordinate coord) {
		switch (coord.direction) {
			case 1:
				if (map[coord.i - 1][coord.j] == ' ') {
					return new Coordinate(coord.i - 1, coord.j, 0);
				}
				return null;
			case 2:
				if (map[coord.i][coord.j + 1] == ' ') {
					return new Coordinate(coord.i, coord.j + 1, 0);
				}
				return null;
			case 3:
				if (map[coord.i + 1][coord.j] == ' ') {
					return new Coordinate(coord.i + 1, coord.j, 0);
				}
				return null;
			case 4:
				if (map[coord.i][coord.j - 1] == ' ') {
					return new Coordinate(coord.i, coord.j - 1, 0);
				}
				return null;
			default:
				return null;
		}
	}

	/**
	 * Establece el mapa a nulo, las coordenadas a 0 e indiica que no hay cargado
	 * ningun laberinto
	 */
	public void freeAll() {
		setMap(null);
		setStartI(0);
		setStartJ(0);
		setEndJ(0);
		setEndI(0);
		setLoaded(false);
		this.path.clear();
	}

}
