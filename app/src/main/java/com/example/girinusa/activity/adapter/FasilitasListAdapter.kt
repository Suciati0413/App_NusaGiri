package com.example.girinusa.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.girinusa.activity.model.FacilityItem
import com.example.girinusa.databinding.FacilityItemBinding

class FasilitasListAdapter(private val FacilityItems: List<FacilityItem>):
    RecyclerView.Adapter<FasilitasListAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: FacilityItemBinding):
    RecyclerView.ViewHolder(binding.root){
        private val imgFacility = binding.iconImgFacility
        private val nameFacility = binding.facilityName

        fun bind(facilityItem: FacilityItem){
            imgFacility.setImageResource(facilityItem.imgFacility)
            nameFacility.text = facilityItem.nameFacility
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            FacilityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return FacilityItems.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(FacilityItems[position])
    }

}