package com.user.phisheye.View.Education

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.user.phisheye.Data.Model.Infographic
import com.user.phisheye.Data.Model.InfographicDataSource
import com.user.phisheye.R
import com.user.phisheye.databinding.ActivityEducationBinding

class EducationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEducationBinding
    private val infographicAdapter = ListEducationAdapter(InfographicDataSource.dataInfographic) { infographic ->
        onInfographicClick(infographic)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_education, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        binding.rvInfographics.apply {
            layoutManager = LinearLayoutManager(this@EducationActivity)
            adapter = infographicAdapter
        }
    }
    private fun onInfographicClick(infographic: Infographic) {
        val intent = Intent(this, EducationDetailsActivity::class.java)
        intent.putExtra("INFOTOGRAFIK", infographic)
        startActivity(intent)
    }
}