package mx.unam

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.unam.databinding.ActivityMainBinding
import mx.unam.databinding.ItemDogBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        initRecyclerView()

        binding.btnSearch.setOnClickListener {
            performSearch()
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun performSearch() {
        val query = binding.etSearch.text.toString().trim()
        if (query.isNotEmpty()) {
            hideKeyboard()
            search(query.lowercase())
        } else {
            Toast.makeText(this, "Escribe una raza para buscar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
        binding.etSearch.clearFocus()
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun search(type: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit()
                    .create(ApiService::class.java)
                    .getBreedByDogs("$type/images")
                val dogModel: DogResponse? = call.body()
                runOnUiThread {
                    if (call.isSuccessful) {
                        val images: List<String> = dogModel?.images ?: emptyList()
                        dogImages.clear()
                        dogImages.addAll(images)
                        adapter.notifyDataSetChanged()
                        if (images.isEmpty()) {
                            binding.recycleView.visibility = View.GONE
                            binding.tvEmpty.visibility = View.VISIBLE
                            binding.tvEmpty.text = "No se encontraron resultados para \"$type\""
                        } else {
                            binding.recycleView.visibility = View.VISIBLE
                            binding.tvEmpty.visibility = View.GONE
                        }
                    } else {
                        showError(type)
                    }
                }
            } catch (e: Exception) {
                runOnUiThread { showError(type) }
            }
        }
    }

    private fun showError(type: String) {
        binding.recycleView.visibility = View.GONE
        binding.tvEmpty.visibility = View.VISIBLE
        binding.tvEmpty.text = "❌ No se encontró la raza \"$type\""
        Toast.makeText(this, "Error en Conexión", Toast.LENGTH_SHORT).show()
    }
}

// ── ViewHolder ──────────────────────────────────────────────
class DogViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(image: String) {
        Picasso.get().load(image).into(binding.imgView)
    }
}

// ── Adapter ─────────────────────────────────────────────────
class DogAdapter(private val images: List<String>) : RecyclerView.Adapter<DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}