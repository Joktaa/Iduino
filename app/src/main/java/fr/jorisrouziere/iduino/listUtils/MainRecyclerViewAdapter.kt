package fr.jorisrouziere.iduino.listUtils

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import fr.jorisrouziere.iduino.R

import fr.jorisrouziere.iduino.databinding.ItemBarBinding
import fr.jorisrouziere.iduino.model.Bar

class MainRecyclerViewAdapter(
    private val values: List<Bar>
) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemBarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemBarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.itemName
        val favoriteButton: ImageButton = binding.itemFavoriteButton

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }

}