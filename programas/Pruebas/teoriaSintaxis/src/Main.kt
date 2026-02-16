fun main(args: Array<String>) {
    val nombre = "Pedro"
    var puntos = 89

    if (puntos >= 100) {
        println("$nombre tiene 100 o mas puntos")
    } else if (puntos >= 80 && puntos <= 99) {
        println("$nombre tiene esta en el rango de [80,99]")
    } else if (puntos >= 70 && puntos < 80) {
        println("$nombre tiene esta en el rango de [70,89)")
    } else {
        println("$nombre es menor a 70")
    }
}

