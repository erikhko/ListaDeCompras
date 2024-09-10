package br.com.fiap.listadecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(private val onItemRemoved: (ItemModel) -> Unit) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var items = listOf<ItemModel>()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView = view.findViewById<TextView>(R.id.textViewItem)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(item: ItemModel) {
            textView.text = item.name

            button.setOnClickListener {
                onItemRemoved(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    /**
     * Método que adiciona um novo item à lista e notifica o RecyclerView que os dados foram alterados.
     * @param newItem O item a ser adicionado.
     * Adiciona o item à lista de itens e chama o método notifyDataSetChanged para atualizar a visualização.
     * Método que atualiza a lista de itens e notifica o RecyclerView que os dados foram alterados.
     * @param newItems A nova lista de itens a ser exibida.
     * Atualiza a lista de itens e chama o método notifyDataSetChanged para atualizar a visualização.
     */
    fun updateItems(newItems: List<ItemModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}