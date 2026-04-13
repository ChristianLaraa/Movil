package mx.unam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import mx.unam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // ✅ AGREGA AQUÍ CADA UNO DE TUS LABORATORIOS
    private val labs = listOf(
        LabItem(
            title = "Laboratorio 1",
            description = "Descripción breve de lo que hace esta app.",
            packageName = "mx.unam",
            screenshotRes = R.drawable.ic_launcher_background,
            techBadge = "Kotlin"
        ),
        LabItem(
            title = "Laboratorio 2",
            description = "Descripción breve de lo que hace esta app.",
            packageName = "com.tuusuario.laboratorio2",
            screenshotRes = R.drawable.ic_launcher_background,
            techBadge = "Java"
        )
        // Agrega más...
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = LabAdapter(labs) { lab ->
            abrirApp(lab.packageName)
        }
    }

    private fun abrirApp(packageName: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            startActivity(intent)
        } else {
            Snackbar.make(
                binding.root,
                "App no instalada en este dispositivo",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
