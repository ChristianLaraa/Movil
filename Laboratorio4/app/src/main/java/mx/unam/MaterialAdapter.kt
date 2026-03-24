package mx.unam

import android.animation.Animator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MaterialAdapter(private val context: Context, private val listaTarjetas: ArrayList<Card>) :
    RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var inicial: TextView = view.findViewById(R.id.initial)
        var nombreTarjeta: TextView = view.findViewById(R.id.name_tarjeta)
        var imagenView: ImageView = view.findViewById(R.id.image_view)
        var card: CardView = view.findViewById(R.id.card_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = LayoutInflater.from(parent.context)
        val v = li.inflate(R.layout.card_view_holder, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarjeta = listaTarjetas[position]
        val name: String? = tarjeta.nombre
        val color: Int = tarjeta.color_recurso

        holder.inicial.setBackgroundColor(color)
        // Agregamos una validación simple para evitar errores si el nombre es nulo o vacío
        holder.inicial.text = if (!name.isNullOrEmpty()) name.substring(0, 1) else "?"

        holder.nombreTarjeta.text = name
        holder.imagenView.setImageResource(R.drawable.libreria_img1)

        holder.card.setOnClickListener {
            Toast.makeText(context, "Carta $name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setOnClickListener(function: () -> Unit) {}

    override fun getItemCount(): Int {
        return listaTarjetas.size
    }

    override fun getItemId(position: Int): Long {
        return listaTarjetas[position].id
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        animateCircularReveal(holder.itemView)
    }

    private fun animateCircularReveal(view: View) {
        // Post para asegurar que el view ya tiene dimensiones medidas (width y height)
        view.post {
            val centroX = 0
            val centerY = 0
            val inicioRadius = 0.0f
            val finRadius = kotlin.math.max(view.width, view.height).toFloat()

            if (view.isAttachedToWindow) {
                val animacion: Animator = ViewAnimationUtils.createCircularReveal(
                    view,
                    centroX,
                    centerY,
                    inicioRadius,
                    finRadius
                )
                view.visibility = View.VISIBLE
                animacion.start()
            }
        }
    }
}