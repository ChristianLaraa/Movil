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

class MaterialAdapter(
    private val context: Context,
    private val listaTarjetas: ArrayList<Card>
) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var inicial: TextView = view.findViewById(R.id.initial)
        var nombreTarjeta: TextView = view.findViewById(R.id.name_tarjeta)
        var imagenView: ImageView = view.findViewById(R.id.image_view)
        var card: CardView = view.findViewById(R.id.card_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_holder, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name: String? = listaTarjetas[position].nombre
        val color: Int = listaTarjetas[position].color_recurso

        holder.inicial.setBackgroundColor(color)
        holder.inicial.text = name?.substring(0, 1)
        holder.nombreTarjeta.text = name
        holder.imagenView.setImageResource(R.drawable.libreria_img1)

        holder.card.setOnClickListener {
            Toast.makeText(context, "Carta $name", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = listaTarjetas.size

    override fun getItemId(position: Int): Long = listaTarjetas[position].id

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        animateCircularReveal(holder.itemView)
    }

    private fun animateCircularReveal(view: View) {
        view.post {
            val centroX = 0
            val centroY = 0
            val inicioRadius = 0.0f
            val finRadius = kotlin.math.max(view.width, view.height).toFloat()

            if (finRadius > 0) {
                val animacion: Animator = ViewAnimationUtils.createCircularReveal(
                    view,
                    centroX,
                    centroY,
                    inicioRadius,
                    finRadius
                )
                view.visibility = View.VISIBLE
                animacion.start()
            }
        }
    }
}