package CursoJava_ExamenBolos;
import java.io.Serializable;
import java.time.LocalDate;

public class Tirada implements Serializable {
	private static final long serialVersionUID = 1L;
	private Simulacion[] simulacion;
	private String nombre ;
	private LocalDate fechaTirada;

	public Tirada(String nombre) {
		this.nombre = nombre;
		this.simulacion = new Simulacion[3];
		this.fechaTirada = LocalDate.now();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Simulacion[] getSimulacion() {
		return simulacion;
	}

	public void setSimulacion(Simulacion[] simulacion) {
		this.simulacion = simulacion;
	}

	public LocalDate getFechaTirada() {
		return fechaTirada;
	}

	public void setFechaTirada(LocalDate fechaTirada) {
		this.fechaTirada = fechaTirada;
	}

	public void realizarTirada() {
		for (int i = 0; i < simulacion.length; i++) {
			simulacion[i] = new Simulacion();
			System.out.println("Tiro numero: " + i + 1 + " has conmseguido: " + simulacion[i].getPuntuacion());
			;
		}
	}

	// SE CREA UN BOLLEANO SEGURO QUE SI NO CAMBIE A TRUE PROGRAMA DEVOLVERA -1 EN
	// SIGNO QUE NO SE REALIZO NINGUN TIRO
	public int mejorLanzamiento() {
		int puntuacion = 0;
		boolean seguro = false;
		for (int i = 0; i < simulacion.length && simulacion[i] != null; i++) {
			if (puntuacion < simulacion[i].getPuntuacion()) {
				puntuacion = simulacion[i].getPuntuacion();
				seguro = true;
			}
		}
		if (seguro) {
			return puntuacion;
		} else {
			return puntuacion - 1;
		}
	}

	// SE CREA UN BOLLEANO SEGURO QUE SI NO CAMBIE A TRUE PROGRAMA DEVOLVERA -1 EN
	// SIGNO QUE NO SE REALIZO NINGUN TIRO, ADEMAS LO DESAROLLO AQUI EL METODO
	// PORQUE ES SITIO QUE LO CORECPONDE YA QUE ES DE ESTA CLASE
	public int peorLanzamiento() {
		int puntuacion = 100;
		boolean seguro = false;

		for (int i = 0; i < simulacion.length && simulacion[i] != null; i++) {

			if (puntuacion > simulacion[i].getPuntuacion()) {
				puntuacion = simulacion[i].getPuntuacion();
				seguro = true;
			}
		}
		if (seguro) {
			return puntuacion;
		} else {
			return puntuacion - 1;
		}
	}

	// SE CREA UN BOLLEANO SEGURO QUE SI NO CAMBIE A TRUE PROGRAMA DEVOLVERA -1 EN
	// SIGNO QUE NO SE REALIZO NINGUN TIRO
	public int totalLanzaminetos() {
		int puntuacion = 0;
		boolean seguro = false;
		for (int i = 0; i < simulacion.length && simulacion[i] != null; i++) {
			puntuacion += simulacion[i].getPuntuacion();
			seguro = true;
		}
		if (seguro) {
			return puntuacion;
		} else {
			return puntuacion - 1;
		}

	}

}
