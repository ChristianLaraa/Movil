package mx.unam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.unam.databinding.ItemLabBinding

class LabAdapter(
    private val items: List<LabItem>,
    private val onOpen: (LabItem) -> Unit
) : RecyclerView.Adapter<LabAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLabBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemLabBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvBadge.text = item.techBadge
            imgScreenshot.setImageResource(item.screenshotRes)
            btnOpen.setOnClickListener { onOpen(item) }
        }
    }

    override fun getItemCount() = items.size
}
