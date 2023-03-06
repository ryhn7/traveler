package com.example.traveler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCategory: RecyclerView
    private lateinit var rvPlace: RecyclerView

    private var listCategory = ArrayList<Category>()
    private var listPlace = ArrayList<Place>()

    companion object{
        const val EXTRA_PLACE = "extra_place"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCategory = findViewById(R.id.rv_categories)
        rvPlace = findViewById(R.id.rv_places)
        rvCategory.setHasFixedSize(true)
        rvPlace.setHasFixedSize(true)

        listCategory.addAll(getListCategory())
        listPlace.addAll(getListPlace())
        showRecyclerList()

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
//                send data to detail activity
                sendData(data)

            }

            private fun sendData(data: Place) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(EXTRA_PLACE, data)
                startActivity(intent)
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

        val lists = ArrayList<Place>()

        for (i in placeRating.indices) {
            val place = Place(placeRating[i], placePhoto.getResourceId(i, -1), placeName[i], placeLocation[i], placePrice[i], placeDescription[i])
            lists.add(place)
        }
        return lists
    }
}