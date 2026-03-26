package mx.unam

import android.os.Bundle
import android.widget.AdapterView
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class VideoActivity : AppCompatActivity() {

    private lateinit var model: ArrayList<Modelo>
    private lateinit var adap: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val surface = findViewById<android.widget.VideoView>(R.id.surface)
        val list = findViewById<android.widget.ListView>(R.id.list)

        val controller = MediaController(this)
        surface.setMediaController(controller)
        controller.setAnchorView(surface)

        fillList()
        list.adapter = adap

        list.setOnItemClickListener(
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val data: Modelo = model[position]
                var ruta = ""

                when (data.type) {
                    1 -> {
                        val nombreSinExtension = data.nameFile.substring(
                            0,
                            data.nameFile.indexOf('.')
                        )
                        ruta = "android.resource://$packageName/raw/$nombreSinExtension"
                    }
                    2 -> {
                        ruta = data.nameFile
                    }
                }

                val rutaUri = ruta.toUri()
                surface.setVideoURI(rutaUri)
                surface.start()
                Toast.makeText(this, data.nameFile, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun fillList() {
        model = ArrayList()
        model.add(Modelo(nameFile = "video.3gp", nameImage = R.drawable.video_uno, type = 1))
        model.add(
            Modelo(
                nameFile = "https://archive.org/download/ElephantsDream/ed_hd.mp4",
                nameImage = R.drawable.video_dos,
                type = 2
            )
        )
        model.add(Modelo(nameFile = "personal.3gp", nameImage = R.drawable.video_tres, type = 1))
        adap = RecipeAdapter(context = this, list = model)
    }

}