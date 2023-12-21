package com.user.phisheye.View.Education

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.user.phisheye.Data.Model.Infographic
import com.user.phisheye.databinding.ItemInfographicBinding

class ListEducationAdapter(private val data: List<Infographic>, private val clickListener: (Infographic) -> Unit) :
    RecyclerView.Adapter<ListEducationAdapter.EducationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val binding =
            ItemInfographicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EducationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val infographic = data[position]
        holder.bind(infographic)

        // Set click listener di sini
        holder.itemView.setOnClickListener {
            clickListener(infographic)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class EducationViewHolder(private val binding: ItemInfographicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(infographic: Infographic) {
            binding.apply {
                textViewTitle.text = infographic.name
                textViewDescription.text = infographic.source
                imageViewInfographic.setImageResource(infographic.photo)
            }
        }
    }
}