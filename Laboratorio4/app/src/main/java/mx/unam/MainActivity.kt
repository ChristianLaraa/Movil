package mx.unam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.unam.ui.theme.Laboratorio4Theme

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lenguajes:Array<String?>
    private lateinit var colores:IntArray
    private lateinit var recyclerView: RecyclerView
    private var adapter:MaterialAdapter?=null
    private var listaTarjetas=ArrayList<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        lenguajes=resources.getStringArray(R.array.lenguajes)
        colores=resources.getIntArray(R.array.inicio_colores)
        iniciarTarjetas()
        if(adapter==null) run {
            adapter = MaterialAdapter(this, listaTarjetas)
        }
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter!!
        recyclerView.layoutManager= LinearLayoutManager(this)
    }


    private fun iniciarTarjetas(){
        for (i in lenguajes.indices){
            val card=Card()
            card.id=i.toLong()
            card.nombre=lenguajes[i]
            card.color_recurso=colores[i]
            listaTarjetas.add(card)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}