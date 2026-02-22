class MemoriaUsb {
    var marca: String = ""
        get() = field.uppercase()
        private set(value) { // Solo la propia clase puede cambiar la marca
            field = value.replaceAfter(' ', "*")
        }

    constructor(dato: String) {
        marca = dato
        println("Entrada 1")
    }

    init {
        println("Entrada 2")
        println(marca)
        marca = "Kingston"
    }
}