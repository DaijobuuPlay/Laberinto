/**
 * Clase que establece algunas constantes
 * @author Victor
 *
 */
public class Config {
	/**
	 * Version del laberinto
	 */
	static String version = "0.2.0";
	/**
	 * Mensaje de bienvenida
	 */
	static String welcome = "Bienvenido al juego del laberinto de Victor";
	/**
	 * Mensaje de despedida
	 */
	static String goodbye = "Se ha finalizado el programa";
	/**
	 * Menu cuando no hay iniciada una sesion
	 */
	static String unlogged_menu = "\n1. Iniciar sesion\n2. Registro\n0. Salir";
	/**
	 * Menu cuando hay una sesion iniciada
	 */
	static String logged_menu = "\n1. Cargar laberinto\n2. Ver laberinto actual\n3. Establecer casillas de entrada y salida\n4. Buscar caminos\n5. Ver usuario actual\n6. Cerrar sesión\n0. Salir";
	/**
	 * Menu para resolver el laberinto
	 */
	static String path_finder = "\nSelecciona un camino\n----------------------\n1. El primer camino posible\n2. El camino mas corto\n0. Cancelar";
	/**
	 * Mensaje al introducir una opcion incorrecta
	 */
	static String option_error = "Introduce una opcion valida";

}