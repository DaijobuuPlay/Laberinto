import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
	private char[][] map;
	private String filename;
	private boolean loaded = false;
	private int startI;
	private int startJ;
	private int endI;
	private int endJ;

	public Maze() {
		setLoaded(false);
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public int getStartI() {
		return startI;
	}

	public void setStartI(int startI) {
		this.startI = startI;
	}

	public int getStartJ() {
		return startJ;
	}

	public void setStartJ(int startJ) {
		this.startJ = startJ;
	}

	public int getEndI() {
		return endI;
	}

	public void setEndI(int endI) {
		this.endI = endI;
	}

	public int getEndJ() {
		return endJ;
	}

	public void setEndJ(int endJ) {
		this.endJ = endJ;
	}

	public boolean loadMaze() {
		freeAll();
		boolean ok = false;
		boolean archivo = false;
		Scanner sc = new Scanner(System.in);
		File folder = new File("laberintos/");
		File[] listOfFiles = folder.listFiles();
		do {
			archivo = false;
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(i + ". " + listOfFiles[i].getName());
				}
			}
			System.out.print("Seleccione el archivo que contenga el laberinto: ");
			int opcion = sc.nextInt();
			if (opcion < listOfFiles.length) {
				setFilename(listOfFiles[opcion].getPath());
				archivo = true;
			} else {
				System.out.println("Selecciona un archivo valido");
			}
		} while (!archivo);
		setMap(fileMaze(filename));
		if (map != null) {
			ok = true;
		} else {
			ok = false;
		}
		return ok;
	}

	public void showMaze() {
		if (map == null) {
			System.out.println("Debe cargar antes un laberinto");
		} else {
			map[startI][startJ] = 'I';
			map[endI][endJ] = 'F';
			map[0][0] = ' ';
			
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					System.out.print(map[i][j]);
					if (j != 0) {
						System.out.print(' ');
					}
				}
				System.out.println("");
			}
		}
	}

	private char[][] fileMaze(String archivo) {
		int i = 1;
		char[][] mazeLine = new char[100][100];
		char[][] laberinto = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String line;
			int lineLength = 0;

			while ((line = reader.readLine()) != null) {
				if (lineLength < line.length()) {
					lineLength = line.length();
				}
				mazeLine[i] = characterRead(line);
				i++;
			}

			laberinto = new char[i+1][lineLength + 2];
			for (int j = 1; j < i; j++) {
				laberinto[j+1] = mazeLine[j];
			}
			
			for(int j = 0; j<2; j++) {
				laberinto[0][j] = ' ';
				laberinto[1][j] = ' ';
			}
			for (int j = 2; j < laberinto[1].length; j++) {
				laberinto[1][j] = Character.forDigit(j-1, 10);
				if (j-1 < 10) {
					laberinto[0][j] = ' ';
				} else {
					laberinto[0][j] = Character.forDigit((j-1) / 10, 10);
					laberinto[1][j] = Character.forDigit((j-1) % 10, 10);
				}
			}

			for (int j = 2; j < laberinto.length; j++) {
				laberinto[j][1] = Character.forDigit(j-1, 10);
				if (j-1 < 10) {
					laberinto[j][0] = ' ';
				} else {
					laberinto[j][0] = Character.forDigit((j-1) / 10, 10);
					laberinto[j][1] = Character.forDigit((j-1) % 10, 10);
				}
			}

		} catch (IOException e) {
			System.out.println("No existe el archivo");
		}

		return laberinto;
	}

	private char[] characterRead(String line) {
		char[] characters = new char[line.length() + 2];
		for (int i = 0; i < line.length(); i++) {
			characters[i + 2] = line.charAt(i);
		}
		return characters;
	}

	public boolean setStartEnd() {
		if (map == null) {
			System.out.println("Debe cargar antes un laberinto");
			return false;
		}
		
		if (startI >= 0) {
			map[startI][startJ] = '#';
			map[endI][endJ] = '#';
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
		if (startIaux <= map.length-2 && startJaux <= map[0].length-2 && endIaux <= map.length-2
				&& endJaux <= map[0].length-2) {
			setStartI(startIaux+1);
			setStartJ(startJaux+1);
			setEndI(endIaux+1);
			setEndJ(endJaux+1);
		}else {
			return false;
		}

		return true;
	}


	public void freeAll() {
		setMap(null);
		setStartI(0);
		setStartJ(0);
		setEndJ(0);
		setEndI(0);
	}
}
