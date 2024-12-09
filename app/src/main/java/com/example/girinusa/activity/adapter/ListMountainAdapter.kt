package com.example.girinusa.activity.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.girinusa.activity.DetailActivity
import com.example.girinusa.activity.model.MountainCardItem
import com.example.girinusa.databinding.CardMountainItemBinding

class ListMountainAdapter(private val mountainItems: ArrayList<MountainCardItem>) :
    RecyclerView.Adapter<ListMountainAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = CardMountainItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ListViewHolder(binding)
    }


    override fun getItemCount(): Int = mountainItems.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, location, price, photo, description, status, elevationHome, elevation, temperature, facility) = mountainItems[position]
        holder.binding.IvMountain.setImageResource(photo)
        holder.binding.tvMountainName.text = name
        holder.binding.tvMountainLocation.text = location
        holder.binding.tvMountainPrice.text = price.toString()
        holder.binding.tvElevation.text = elevationHome
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_mountain", mountainItems[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }

    }

    class ListViewHolder(val binding: CardMountainItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: MountainCardItem)
    }
}