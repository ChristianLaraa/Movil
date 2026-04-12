package mx.unam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.unam.databinding.ActivityNestedScrollViewBinding

class NestedScrollViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNestedScrollViewBinding
    private lateinit var lenguajes: Array<String?>
    private lateinit var colores: IntArray
    private lateinit var recyclerView: RecyclerView
    private var adapter: MaterialAdapter? = null
    private var listaTarjetas = ArrayList<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNestedScrollViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        lenguajes = resources.getStringArray(R.array.lenguajes)
        colores = resources.getIntArray(R.array.inicio_colores)

        iniciarTarjetas()

        if (adapter == null) {
            adapter = MaterialAdapter(this, listaTarjetas)
        }

        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun iniciarTarjetas() {
        for (i in lenguajes.indices) {
            val card = Card()
            card.id = i.toLong()
            card.nombre = lenguajes[i]
            card.color_recurso = colores[i]
            listaTarjetas.add(card)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}