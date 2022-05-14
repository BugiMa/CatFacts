package com.bugima.catfacts.presentation.fact_listing

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bugima.catfacts.databinding.ItemFactBinding
import com.bugima.catfacts.domain.model.CatFact

class FactListingRecyclerViewAdapter(
    private var facts: List<CatFact>
) : RecyclerView.Adapter<FactListingRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemFactBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = facts[position]
        holder.bind(fact.id)
        holder.itemView.setOnClickListener {
            val directions = FactListingFragmentDirections
                .actionFactListingFragmentToFactDetailsFragment(fact.id)
            it.findNavController().navigate(directions)
        }
    }

    inner class ViewHolder(private val itemBinding: ItemFactBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(factId: String) {
            itemBinding.factIdTextView.text = factId
        }
    }

    fun updateData(data: List<CatFact>) {
        facts = facts + data
        this.notifyItemRangeInserted(itemCount, data.size)
    }

    override fun getItemCount(): Int = facts.size
}