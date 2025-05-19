# CursoJava App de juego de los Bolos

🎳 ¡Vamos a jugar a los bolos!

Aunque no será necesario conocer las reglas del juego original, vamos a implementar nuestra propia competición.
Crearemos una aplicación en Java que registre las puntuaciones de los jugadores en una partida de bolos, donde cada uno podrá realizar tres lanzamientos. 🎯

## 📦 Clases a implementar

##   🧪 Clase Simulación (1 punto)

  - Simula un lanzamiento de bolos generando un número aleatorio entre 0 y 100.

  - Contiene el método:

    - devolverPuntuación() ➡️ retorna la puntuación del lanzamiento.

##   🎲 Clase Tirada (2 puntos)

  - Almacena:

    - Los tres lanzamientos de cada jugador (gracias a Simulación).

    - El nombre del tirador 🧍 y la fecha 📅.

  - Contiene los métodos:

    - realizarTirada() ➡️ muestra el valor de los tres lanzamientos.

    - mejorLanzamiento() ➡️ devuelve el lanzamiento con mayor puntuación.

    - totalLanzamientos() ➡️ suma de los tres lanzamientos.

##   🏁 Clase Principal

  - Gestiona un array de hasta 100 partidas.
  - Se deben realizar las siguientes tareas:

    - Tirar() 🎯: Realizar una tirada. (1 punto)

    - maximaPuntuacion() 🥇: Mostrar la tirada con el lanzamiento más alto (imprimir todas si hay empate). (0.5 puntos)

    - minimaPuntuacion() 🥉: Mostrar la tirada con el lanzamiento más bajo (imprimir todas si hay empate). (0.5 puntos)

    - mayorTotal() 🏆: Mostrar la tirada con mayor total (si hay empate, la más antigua). (0.5 puntos)

##   🚪 Salir de la aplicación

  - 💾 Persistencia de datos
    
  - Al iniciar la app:

    - Comprobar si existe el fichero datos.dat 📁 y recuperar las partidas previas. (1.5 puntos)

  - Antes de salir:

    - Seleccionar la tirada con mayor puntuación total.

    - Guardar el nombre y puntuación en un fichero llamado ganador.txt 📝. (1.5 puntos)

  - Al salir:
    - Se guarda la partida con mayor puntuación en ganador.txt 🏅 (incluye nombre y puntuación del tirador) (1.5 puntos)

    - Se guarda el estado actual del array en datos.dat 🗃️ 
      
##   📝 Nota importante:

  - En este ejercicio se deberá utilizar exclusivamente las variables y métodos indicados explícitamente en el enunciado.
