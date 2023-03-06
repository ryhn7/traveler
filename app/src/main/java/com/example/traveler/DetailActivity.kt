package com.example.traveler

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.traveler.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PLACE = "extra_place"
    }

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val place = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PLACE, Place::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PLACE)
        }

        if (place != null) {
            binding.tvDetailPlaceName.text = place.name
            binding.ivDetailPlaceImage.setImageResource(place.photo)
            binding.tvDetailRating.text = place.rating
            binding.tvDetailPriceValue.text = place.price
            binding.tvDetailPlaceLocation.text = place.description
            binding.tvDetailReview.text = place.review
        }

//        on click back button, go back to main activity
        binding.btnDetailBack.setOnClickListener {
            finish()
        }

//        on click share button, share the place
        binding.btnDetailActionShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey, check out this place ${place?.name} in ${place?.location}"
                )
                type = "text/plain"
            }
            startActivity(shareIntent)
        }

        binding.btnDetailBook.setOnClickListener {
            val toast = Toast.makeText(this, "Booking ${place?.name}...", Toast.LENGTH_SHORT)
            toast.show()
        }

    }
}