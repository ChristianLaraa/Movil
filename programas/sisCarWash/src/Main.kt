// 1. Definición de la Interfaz (Evita crear una 'class Tarea' para no tener el error de redeclaración)
interface Tarea {
    fun realizar()
}

// 2. Clase Persona con su atributo privado y métodos de acceso (get/set)
open class Persona(private var nombre: String) {

    // Métodos solicitados en el diagrama: get/set
    fun getNombre(): String = nombre

    fun setNombre(nuevoNombre: String) {
        this.nombre = nuevoNombre
    }

    // Relación de asociación: Persona usa una Tarea
    fun ejecutarTarea(tarea: Tarea) {
        println("${getNombre()} está realizando una actividad:")
        tarea.realizar()
    }
}

// 3. Implementaciones concretas de las tareas según el pizarrón
class LimpiarSillones : Tarea {
    override fun realizar() {
        println("Resultado: Los sillones han quedado limpios.")
    }
}

class LimpiarTapetes : Tarea {
    override fun realizar() {
        println("Resultado: Los tapetes han sido aspirados.")
    }
}

class LimpiarCristales : Tarea {
    override fun realizar() {
        println("Resultado: Los cristales están impecables.")
    }
}

// 4. Función Principal para probar el flujo
fun main() {
    // Instanciamos a la persona
    val usuario = Persona("Juan")

    // Creamos una lista de tareas basadas en la interfaz
    val listaDeTareas: List<Tarea> = listOf(
        LimpiarSillones(),
        LimpiarTapetes(),
        LimpiarCristales()
    )

    // Ejecutamos cada tarea
    for (tarea en listaDeTareas) {
        usuario.ejecutarTarea(tarea)
    }
}