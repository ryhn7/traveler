package com.example.traveler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.databinding.ActivityDetailBinding
import com.example.traveler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvCategory: RecyclerView
    private lateinit var rvPlace: RecyclerView

    private var listCategory = ArrayList<Category>()
    private var listPlace = ArrayList<Place>()

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_PLACE = "extra_place"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        rvCategory = findViewById(R.id.rv_categories)
        rvPlace = findViewById(R.id.rv_places)
        rvCategory.setHasFixedSize(true)
        rvPlace.setHasFixedSize(true)

        listCategory.addAll(getListCategory())
        listPlace.addAll(getListPlace())
        showRecyclerList()

        binding.civProfile.setOnClickListener {
            val intent = Intent(this, AboutPage::class.java)
            startActivity(intent)
        }

    }


    private fun showRecyclerList() {
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvPlace.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listCategoryAdapter = ListCategoryAdapter(listCategory)
        val listPlaceAdapter = ListPlaceAdapter(listPlace)
        rvCategory.adapter = listCategoryAdapter
        rvPlace.adapter = listPlaceAdapter

        listPlaceAdapter.setOnItemClickCallback(object : ListPlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Place) {
                sendData(data)
            }

            private fun sendData(data: Place) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(EXTRA_PLACE, data)
                startActivity(intent)
            }
        })

        listCategoryAdapter.setOnItemClickCallback(object :
            ListCategoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Category) {
                showSelectedCategory(data)
            }

            private fun showSelectedCategory(data: Category) {
                Toast.makeText(this@MainActivity, "You choose " + data.name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getListCategory(): ArrayList<Category> {
        val categoryName = resources.getStringArray(R.array.category_name)
        val list = ArrayList<Category>()

        for (i in categoryName.indices) {
            val category = Category(categoryName[i])
            list.add(category)
        }
        return list
    }

    private fun getListPlace(): ArrayList<Place> {
        val placeRating = resources.getStringArray(R.array.place_rating)
        val placePhoto = resources.obtainTypedArray(R.array.place_photo)
        val placeName = resources.getStringArray(R.array.place_name)
        val placeLocation = resources.getStringArray(R.array.place_location)
        val placePrice = resources.getStringArray(R.array.place_price)
        val placeDescription = resources.getStringArray(R.array.place_description)
        val placeReview = resources.getStringArray(R.array.place_review)

        val lists = ArrayList<Place>()

        for (i in placeRating.indices) {
            val place = Place(
                placeRating[i],
                placePhoto.getResourceId(i, -1),
                placeName[i],
                placeLocation[i],
                placePrice[i],
                placeDescription[i],
                placeReview[i]
            )
            lists.add(place)
        }
        return lists
    }
}