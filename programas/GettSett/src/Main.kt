class Persona() {
    var nombre: String = ""
        get() = field.uppercase() // Cada que pidas el nombre, saldrá en mayúsculas
        set(value) {
            field = value.replaceAfter(' ', "") // Solo guarda la primera palabra
        }

    var edad: Int = 0

    override fun toString(): String {
        return "Hola $nombre ya tienes $edad años"
    }
}

fun main(args: Array<String>) {
    val persona = Persona()
    persona.nombre = "Pedro Angel"
    persona.edad = 25
    println(persona)
    // Resultado: Hola PEDRO ya tienes 20 años
}