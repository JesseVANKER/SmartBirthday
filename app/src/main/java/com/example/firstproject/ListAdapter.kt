package com.example.firstproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.model.Personne
import com.example.firstproject.databinding.CustomRowBinding
import com.example.firstproject.fragments.HomeFragmentDirections

class ListAdapter : RecyclerView.Adapter<ListAdapter.PersonneVH>() {

    private var personneList = emptyList<Personne>()

    class PersonneVH(val bindingLineGateau: CustomRowBinding) : RecyclerView.ViewHolder(bindingLineGateau.root)

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonneVH {
        val binding = CustomRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return PersonneVH(binding)
    }

    override fun getItemCount(): Int {
        return personneList.size
    }

    override fun onBindViewHolder(holder: PersonneVH, position: Int) {
        val currentItem = personneList[position]
        holder.bindingLineGateau.personne = currentItem
        holder.bindingLineGateau.relativeLayout2.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(personne : List<Personne>){
        this.personneList = personne
        notifyDataSetChanged()
    }

}