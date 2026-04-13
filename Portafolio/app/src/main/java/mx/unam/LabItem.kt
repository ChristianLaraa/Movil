package mx.unam

data class LabItem(
    val title: String,
    val description: String,
    val packageName: String,       // com.tuusuario.lab1
    val screenshotRes: Int,        // R.drawable.screenshot_lab1
    val techBadge: String          // "Kotlin", "Java", etc.
)