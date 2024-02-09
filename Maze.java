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
		} while (archivo == false);
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
			for (int i = 1 ; i < map[1].length; i++) {
				map[0][i] = (char)('A' + (i-1));
			}
			for (int i = 1; i < map.length; i++) {
				map[i][0] = Character.forDigit(i, 10);
				if(i<10){
					map[i][1] = ' ';
				}else{
					map[i][0] = Character.forDigit(i/10, 10);
					map[i][1] = Character.forDigit(i%10, 10);
				}
			}

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					System.out.print(map[i][j]);
					if(j != 0){
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

	public void setStartEnd() {
		if (map == null) {
			System.out.println("Debe cargar antes un laberinto");
		} else {
			if (startI >= 0) {
				map[startI][startJ] = '#';
				map[endI][endJ] = '#';
			}
			Scanner sc = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Casilla Inicial I: ");
			int startI = sc.nextInt();
			System.out.print("Casilla Inicial J: ");
			char letraSJ = sc2.nextLine().charAt(0);
			if(letraSJ >= 'A' && letraSJ <= 'Z'){
				letraSJ -= 64;
			}
			if(letraSJ >= 'a' && letraSJ <= 'z'){
				letraSJ -= 96;
			}
			int startJ = (int)letraSJ;
			System.out.print("Casilla Final I: ");
			int endI = sc.nextInt();
			System.out.print("Casilla Final J: ");
			char letraEJ = sc2.nextLine().charAt(0);
			if(letraEJ >= 'A' && letraEJ <= 'Z'){
				letraEJ -= 64;
			}
			if(letraEJ >= 'a' && letraEJ <= 'z'){
				letraEJ -= 96;
			}
			int endJ = (int) letraEJ;
			if (startI <= map.length && startJ <= map[0].length && endI <= map.length && endJ <= map[0].length) {
				setStartI(startI);
				setStartJ(startJ);
				setEndI(endI);
				setEndJ(endJ);
				System.out.println("Coordenadas establecidas correctamente");

			} else {
				System.out.println("No se ha podido establecer las coordenadas");
			}
		}
	}

	public void freeAll(){
		setMap(null);
		setStartI(0);
		setStartJ(0);
		setEndJ(0);
		setEndI(0);
	}
}
