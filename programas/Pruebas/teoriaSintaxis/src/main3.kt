fun main(args: Array<String>) {
    val salida = { entradaUno: String, entradaDos: Int ->
        val mundo = (1..20).shuffled().last()
        "Hola $entradaUno tu numero de vidas son $entradaDos para el mundo $mundo"
    }

    val especial = { valorUno: String, valorDos: Int ->
        val incremento = (1..40).shuffled().last() + valorDos
        "Hola $valorUno se incremento la siguiente puntuacion $incremento"
    }

    simulacion("jugador1", salida, ::mostrarMensaje)
    simulacion("Master", especial, ::mostrarMensaje)
}

inline fun simulacion(jugador: String, bienvenida: (String, Int) -> String, agregarOtraFuncion: (Int) -> Unit) {
    val sorteo = (1..5).shuffled().last()
    agregarOtraFuncion(sorteo)
    println(bienvenida(jugador, sorteo))
}

fun mostrarMensaje(sorteo: Int = 0) {
    println("El valor del sorteo es: $sorteo")
}