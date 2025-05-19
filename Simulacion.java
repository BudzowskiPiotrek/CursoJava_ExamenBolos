package CursoJava_ExamenBolos;
import java.io.Serializable;
import java.util.Random;

public class Simulacion implements Serializable{
	private static final long serialVersionUID = 1L;
	private int puntuacion;

	public Simulacion() {
		Random rm = new Random();
		this.puntuacion = rm.nextInt(101);
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int devolverPuntuacion() {
		return puntuacion;
	}

}
