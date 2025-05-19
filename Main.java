package CursoJava_ExamenBolos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class Main {

	public static Tirada[] partidas = new Tirada[100];
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static final String RUTA = "Cursojava_ExamenBolos/datos.dat";
	public static final String RUTA_DOS = "Cursojava_ExamenBolos/ganador.txt";

	public static void main(String[] args) {
		cargarDatos();
		menu();
	}

	// METODO PRINCIPAL DE MENU
	private static void menu() {
		boolean flag = true;
		while (flag) {
			System.out.println("---- MENU PRINCIPAL ----");
			System.out.println("1. Tirar los tres Tiros");
			System.out.println("2. Mostrar maxima Puntuacion");
			System.out.println("3. Mostrar minimaPuntuacion");
			System.out.println("4. Muestra La puntuacion Mayor de Partida mas antigua");
			System.out.println("5. Salir");
			try {
				int opcion = Integer.parseInt(br.readLine());
				flag = menuSwitch(opcion);
			} catch (NumberFormatException | IOException e) {
				System.err.println("Error, intenta de nuevo");
			}
		}
	}

	// METODO MENUsWITCH DEVUELVE EL VALOR DE BOOLEANO PARA FLAG DEL MENU, CUALQUEIR
	// OPCION MENOS LA DE 5 DEVUELVE TRUE CONSERVANDO SU FUNCIONAMIENTO, LA E 5
	// CAMBIA A FALSE Y CIERRA LA APP
	private static boolean menuSwitch(int opcion) {
		switch (opcion) {
		case 1:
			tirar();
			break;
		case 2:
			maximaPuntuacion();
			break;
		case 3:
			minimaPuntuacion();
			break;
		case 4:
			mayorTotal();
			break;
		case 5:
			guardarGanador(); // ANTES DEL SALIR SE GUARDAN DATOS DE MAXIMO GANADOR CON MAXIMA PUNTUACION
			guardarDatos(); // A SU VEZ AL SALIR SE GUARDA LA ARRAY EN EL ESTADO EN QUE SE ENCUTNTRA (ESTO
							// SE PODRIA INTERPRETAR PONIENDO METODO YA FUERA MENU EN LINEA 24)
			return false;

		default:
			System.err.println("Numero fuera de rango, vuelves al menu");
			break;
		}
		return true;
	}

	// METODO TIRAR CREA UNA PARTIDA AUXILIAR, USANDO METODO DE REALIZAR TIRADA QUE
	// HACE TRES TIRADAS Y LAS IMPRIME, POSTERIORMENTE LA GUARDAMOS EN PRIMERA
	// POSICION LIBRE DE LA ARRAY DE 100 PARTIDAS
	private static void tirar() {
		if (primerLibre() != -1) {
			System.out.println("Dime tu nombre: ");
			String nombre;
			try {
				nombre = br.readLine();
				Tirada t1 = new Tirada(nombre);
				t1.realizarTirada();
				partidas[primerLibre()] = t1;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// METODO MINIMAPUNTUACION RECORRE LA ARRAY DE LAS PARTIDAS Y IMPRIME SUS
	// RESULTADOS MINIMOS, EN CASO DE QUE NO SE JUGARA NINGUNA SALTA COMUNICADO QUE
	// AUN NO HAY PARTIDAS ALMACENADAS
	private static void minimaPuntuacion() {
		boolean existePartida = false;
		for (int i = 0; i < partidas.length && partidas[i] != null; i++) {
			System.out.println("En la partida numero: " + (i + 1) + " , " + partidas[i].getNombre()
					+ " obtubo un minimo de: " + partidas[i].peorLanzamiento());
			existePartida = true;
		}
		if (!existePartida) {
			System.out.println("No se jugo aun ninguna Partida");
		}
	}

	// METODO MAXIMAMAPUNTUACION RECORRE LA ARRAY DE LAS PARTIDAS Y IMPRIME SUS
	// RESULTADOS MAXIMOS, EN CASO DE QUE NO SE JUGARA NINGUNA SALTA COMUNICADO QUE
	// AUN NO HAY PARTIDAS ALMACENADAS
	private static void maximaPuntuacion() {
		boolean existePartida = false;
		for (int i = 0; i < partidas.length && partidas[i] != null; i++) {
			System.out.println("En la partida numero: " + (i + 1) + " , " + partidas[i].getNombre()
					+ " obtubo un maximo de: " + partidas[i].mejorLanzamiento());
			existePartida = true;
		}
		if (!existePartida) {
			System.out.println("No se jugo aun ninguna Partida");
		}
	}

	// METODO MAYORTOTAL PRIMERO CREA UNA FECHA AUXILIAR QUE SERIA DE PRIMERA
	// PARTIDA SI EXISTE
	private static void mayorTotal() {

		if (partidas[0] != null) {
			LocalDate masAntigua = partidas[0].getFechaTirada();
			for (int i = 0; i < partidas.length && partidas[i] != null; i++) {
				if (masAntigua.isBefore(partidas[i].getFechaTirada())) {
					masAntigua = partidas[i].getFechaTirada();
				}
			}

			for (int i = 0; i < partidas.length && partidas[i] != null; i++) {
				if (partidas[i].getFechaTirada().isEqual(masAntigua)) {
					System.out.println("En la partida numero: " + (i + 1) + ", " + partidas[i].getNombre()
							+ " obtubo un total de: " + partidas[i].totalLanzaminetos());
				}
			}
		} else {
			System.out.println("No hay partidas guardadas");
		}

	}

	// METODO PRIMERlIBRE ES PARA MIRAR PRIMERA POSICION LIBRE DE LA ARRAY, EN CASO
	// DE QUE OCUPARIA LOS 100, DEVOLVERIA -1 LO QUE QUEIRE DECIR QUE ESTA OCUAPDA
	// ENTERA
	private static int primerLibre() {
		int numero = -1;
		for (int i = 0; i < partidas.length; i++) {
			if (partidas[i] == null) {
				numero = i;
				return numero;
			}
		}
		return numero;
	}

	// METODO CARGAR DATOS PRIMERO COMPRUEBA SI ARCHIVO EXISTE, SI EXISTE LO CARGA,
	// SINO PUES RETURN Y PARA EL METODO
	private static void cargarDatos() {
		File archivo = new File(RUTA);
		if (!archivo.exists()) {
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA))) {
			partidas = (Tirada[]) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// METODO GUARDAR DATOS CREA ARCHIVO SI NO ESTA Y SI ESTA EN LA RUTA INDICADA
	// GUARDA LA ARRAY DE PARTIDAS
	private static void guardarDatos() {
		ObjectOutputStream aux = null;
		try (FileOutputStream fos = new FileOutputStream(RUTA)) {
			aux = new ObjectOutputStream(fos);
			aux.writeObject(partidas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// METODO DE GUARDAR GANADOR COMPRUBA SI EXISTE ARCHIVO, SINO LA CREA,
	// POSTERIORMENTE RECORO LA ARRAY EN BUSCA DE PARTIDA CON MAYOR PUNTUACION
	// CREANDO UNA PARTIDA AUXILIAR QUE LUEGO LA MAXIMA GUARDO EN ARCHIVO DE TEXTO.
	private static void guardarGanador() {
		File archivo = new File(RUTA_DOS);
		if (!archivo.exists()) {
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (FileWriter aux = new FileWriter(RUTA_DOS)) {
			Tirada t1 = null;
			boolean existePartida = false;
			int puntuacionMaxima = 0;
			for (int i = 0; i < partidas.length && partidas[i] != null; i++) {
				if (puntuacionMaxima < partidas[i].totalLanzaminetos()) {
					puntuacionMaxima = partidas[i].totalLanzaminetos();
					t1 = partidas[i];
					existePartida = true;
				}
			}
			if (existePartida) {
				aux.write(t1.getNombre() + " " + t1.totalLanzaminetos());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
