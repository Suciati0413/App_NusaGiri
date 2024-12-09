package com.example.girinusa.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.girinusa.R
import com.example.girinusa.activity.adapter.FasilitasListAdapter
import com.example.girinusa.activity.adapter.ListMountainAdapter
import com.example.girinusa.activity.model.MountainCardItem
import com.example.girinusa.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var rvMountain: RecyclerView
    private val list = ArrayList<MountainCardItem>()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var facilityAdapter: FasilitasListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewMountains.setHasFixedSize(true)
        list.addAll(getListMountain())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        supportActionBar?.title = "Welcome To GiriNusa"
        supportActionBar?.elevation = 0f
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this@HomeActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList() {
        binding.recyclerViewMountains.layoutManager = LinearLayoutManager(this)
        val listMountainAdapter = ListMountainAdapter(list)
        binding.recyclerViewMountains.adapter = listMountainAdapter

        listMountainAdapter.setOnItemClickCallback(object :
            ListMountainAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MountainCardItem) {
                showSelectedMountain(data)
            }
        })
    }

    private fun showSelectedMountain(mountain: MountainCardItem) {
        val intent = Intent(this@HomeActivity, DetailActivity::class.java)
        startActivity(intent)
    }

    private fun getListMountain(): Collection<MountainCardItem> {
        val dataName = resources.getStringArray(R.array.mountain_name)
        val dataLocation = resources.getStringArray(R.array.region_mountain)
        val dataPrice = resources.getStringArray(R.array.price_mountain)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDescription = resources.getStringArray(R.array.description_mountain)
        val dataStatus = resources.getStringArray(R.array.status_mountain)
        val dataElevationHome = resources.getStringArray(R.array.elevation_mountain_home)
        val dataElevation = resources.getStringArray(R.array.elevation_mountain)
        val dataTemperature = resources.getStringArray(R.array.temp_mountain)
        val listMountain = ArrayList<MountainCardItem>()

        for (i in dataName.indices) {
            val mountain = MountainCardItem(
                dataName[i],
                dataLocation[i],
                dataPrice[i],
                dataPhoto.getResourceId(i, -1),
                dataDescription[i],
                dataStatus[i],
                dataElevationHome[i],
                dataElevation[i],
                dataTemperature[i]
            )
            listMountain.add(mountain)
        }
        return listMountain
    }
}