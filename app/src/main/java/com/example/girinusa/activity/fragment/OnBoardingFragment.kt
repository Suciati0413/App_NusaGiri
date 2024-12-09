package com.example.girinusa.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.girinusa.R
import com.example.girinusa.activity.adapter.OnBoardingAdapter
import com.example.girinusa.activity.model.OnBoardingItem
import com.example.girinusa.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {

    private var binding: FragmentOnBoardingBinding? = null
    private val bind get() = binding!!
    private lateinit var onBoardingAdapter: OnBoardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return bind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBoardingItems()
        setCurrentIndicator(0)
        setUpIndicator()

    }

    private fun setCurrentIndicator(position: Int) {
        bind.apply {
            val chilCount = indicator.childCount
            for (i in 0 until chilCount) {
                val imageView = indicator.getChildAt(i) as ImageView
                if (i == position) {
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.indicator_active
                        )
                    )
                } else {
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.indicator_inactive
                        )
                    )
                }
            }
        }
    }

    private fun setUpIndicator() {
        val indicators = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(context)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive
                    )
                )
                it.layoutParams = layoutParams
                bind.indicator.addView(it)
            }
        }
    }

    private fun setOnBoardingItems() {
        onBoardingAdapter = OnBoardingAdapter(
            listOf(
                OnBoardingItem(onBoardingImage = R.drawable.img_onboarding1),
                OnBoardingItem(onBoardingImage = R.drawable.img_onboarding2),
                OnBoardingItem(onBoardingImage = R.drawable.img_onboarding3),
            )
        )
        bind.apply {
            imageOnBoardingViewPager.adapter = onBoardingAdapter
            imageOnBoardingViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                    if (position == onBoardingAdapter.itemCount - 1) {
                        btnNext.visibility = View.GONE
                    } else {
                        btnNext.visibility = View.VISIBLE
                    }
                }
            })
            (imageOnBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER

            btnNext.setOnClickListener {
                if (imageOnBoardingViewPager.currentItem + 1 < onBoardingAdapter.itemCount) {
                    imageOnBoardingViewPager.currentItem += 1
                } else {
                    findNavController().navigate(R.id.action_onBoardingFragment_to_homeActivity)
                }
            }
            btnSkip.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardingFragment_to_homeActivity)
            }
        }
    }
}