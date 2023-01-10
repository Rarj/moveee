package com.example.movee.feature.caster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.movee.R
import com.example.movee.data.repository.caster.Caster
import com.example.movee.databinding.ItemCasterBinding

class CasterAdapter(private val casters: List<Caster>) : Adapter<CasterAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCasterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(caster: Caster) {
            binding.name.text = caster.name
            binding.character.text = caster.character
            val image =
                if (caster.profilePath == null) R.drawable.ic_person else caster.getProfileUrl()
            binding.imageCaster.load(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCasterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = casters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(casters[position])
    }
}