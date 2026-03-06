package mx.unam

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var texto: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView1 = findViewById<TextView>(R.id.textView_1)
        val mensaje: String = textView1.text.toString()
        Log.i("Mensaje de un componente textView_1", mensaje)

        texto = TextToSpeech(this, this)

        findViewById<Button>(R.id.btnEjecutar).setOnClickListener {
            hablar()
        }
    }

    override fun onInit(status: Int) {
        val textView1 = findViewById<TextView>(R.id.textView_1)
        if (status == TextToSpeech.SUCCESS) {
            textView1.text = "Si se puede hablar"
            val localSpanish = Locale("spa", "MEX")
            texto?.setLanguage(localSpanish)
        } else {
            textView1.text = "No puede hablar"
        }
    }

    private fun hablar() {
        val textView1 = findViewById<TextView>(R.id.textView_1)
        val mensaje: String = textView1.text.toString()
        texto?.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, "")
    }


    }

