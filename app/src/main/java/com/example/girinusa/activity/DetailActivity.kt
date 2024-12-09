package com.example.girinusa.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.girinusa.R
import com.example.girinusa.activity.adapter.FasilitasListAdapter
import com.example.girinusa.activity.model.FacilityItem
import com.example.girinusa.activity.model.MountainCardItem
import com.example.girinusa.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var facilityAdapter: FasilitasListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()


        setContentView(binding.root)
        setFacilityItems()
        val dataMountain = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<MountainCardItem>("key_mountain",MountainCardItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<MountainCardItem>("key_mountain")
        }
        binding.tvMountainName.text = dataMountain?.name
        binding.tvMountainPrice.text = dataMountain?.price
        binding.tvMountainRegion.text = dataMountain?.region
        binding.tvDescriptionContent.text = dataMountain?.description
        binding.vpMainImage.setImageResource(dataMountain!!.photo)
        binding.tvStatus.text = dataMountain.status
        binding.tvElevation.text = dataMountain.elevation
        binding.tvTemperature.text = dataMountain.temperature


        binding.btnBack.setOnClickListener {
            val intent = Intent(this@DetailActivity,HomeActivity::class.java)
            startActivity(intent)
        }
         binding.actionShare.setOnClickListener {
             dataMountain.let {
                 val share = """
                     Check out this mountain!
                     
                     Name Mountain : ${it.name}
                     Region : ${it.region}
                     Status : ${it.status}
                     Price : ${it.price}
                     Elevation : ${it.elevation}
                     Temperature : ${it.temperature}
                     Description : ${it.description}
                 """.trimIndent()

                 val sendIntent =  Intent().apply {
                     action = Intent.ACTION_SEND
                     putExtra(Intent.EXTRA_TEXT,share)
                     type = "text/plain"
                 }
                 val shareIntent = Intent.createChooser(sendIntent, null)
                 startActivity(shareIntent)
             }
         }
    }


    private fun setFacilityItems() {
        facilityAdapter = FasilitasListAdapter(
            listOf(
                FacilityItem(imgFacility = R.drawable.home, nameFacility = "Basecamp"),
                FacilityItem(imgFacility = R.drawable.ph_toilet, nameFacility = "Toilet"),
                FacilityItem(imgFacility = R.drawable.parking, nameFacility = "Parking"),
                FacilityItem(imgFacility = R.drawable.water, nameFacility = "Water"),
                FacilityItem(imgFacility = R.drawable.burger, nameFacility = "Market"),
            )
        )
        binding.vpFasilitas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.vpFasilitas.adapter = facilityAdapter
    }

}