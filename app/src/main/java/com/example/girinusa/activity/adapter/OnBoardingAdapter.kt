package com.example.girinusa.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.girinusa.activity.model.OnBoardingItem
import com.example.girinusa.databinding.OnboardingItemBinding

class OnBoardingAdapter(private val onBoardingItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.ListViewHolder>() {

    class ListViewHolder(binding: OnboardingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imgOnBoarding = binding.onBoardingImg

        fun bind(onBoardingItem: OnBoardingItem) {
            imgOnBoarding.setImageResource(onBoardingItem.onBoardingImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            OnboardingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }
}