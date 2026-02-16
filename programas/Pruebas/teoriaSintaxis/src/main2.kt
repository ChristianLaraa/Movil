fun main(args: Array<String>) {
    val nombre = "Pedro"
    var puntos = 89
    var salida = operacionPuntos(nombre = nombre)
    println(salida)
    salida = operacionPuntos(puntos = 100)
    println(salida)
}

private fun operacionPuntos(puntos: Int = 30, nombre: String = "Anonimo") =
    when (puntos) {
        100 -> "$nombre tiene 100 o mas puntos"
        in 80..99 -> "$nombre esta en rango de [80,99]"
        in 70..79 -> "$nombre esta en rango [70,79]"
        else -> "$nombre es menor a 70"
    }