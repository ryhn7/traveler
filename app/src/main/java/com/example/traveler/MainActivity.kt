package com.example.traveler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCategory: RecyclerView
    private var listCategory = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCategory = findViewById(R.id.rv_categories)
        rvCategory.setHasFixedSize(true)

        listCategory.addAll(getListCategory())
        showRecyclerList()

    }

    private fun showRecyclerList() {
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listCategoryAdapter = ListCategoryAdapter(listCategory)
        rvCategory.adapter = listCategoryAdapter
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
}