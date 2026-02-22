data class Persona1(var nombre: String, var edad: Int) {
    lateinit var despues: String // Prometemos inicializarla luego

    init {
        this.nombre = nombre.uppercase()
        this.edad = this.edad * 2
        despues = "Inicio despues"
    }

    private fun ocuparDespues() {
        if (::despues.isInitialized) {
            println("Se inicio despues $despues")
        }
    }
}