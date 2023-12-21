package com.user.phisheye.View.Education

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.user.phisheye.Data.Model.Infographic
import com.user.phisheye.R
import com.user.phisheye.databinding.ActivityDetailsEducationBinding
import com.user.phisheye.databinding.ActivityEducationBinding

class EducationDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsEducationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsEducationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val infographic: Infographic? = intent.getParcelableExtra("INFOTOGRAFIK")

        if (infographic != null) {
           Glide.with(this)
              .load(infographic.photo)
               .into(binding.imageViewInfographic)
            binding.textViewTitle.text = infographic.name
            binding.textViewDescription.text = infographic.source
            binding.textViewDetails.text = infographic.details
        }
        else {
            Toast.makeText(this, "Infographic tidak ditemukan", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }
}