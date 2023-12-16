package com.user.phisheye.View.Education

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.user.phisheye.R
import com.user.phisheye.databinding.ActivityEducationBinding

class EducationDetailsActivity : AppCompatActivity(){
    private lateinit var binding: ActivityEducationBinding
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_education, menu)
        return super.onCreateOptionsMenu(menu)
    }
}