package com.example.tripmate.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tripmate.dataClasses.DayPlan
import com.example.tripmate.adapters.DayPlanAdapter
import com.example.tripmate.utility.GeminiRunner
import com.example.tripmate.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class TravelPlan : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DayPlanAdapter
    private lateinit var travelPackageName: TextView
    private lateinit var packageImage: ImageView
    private lateinit var loadingTextView: TextView // Loading text

    private var dayPlans: MutableList<DayPlan> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_plan)

        val packageName: String? = intent.getStringExtra("packageName")
        val destinationName: String? = intent.getStringExtra("destinationName")
        val packageImageUrl: String? = intent.getStringExtra("packageImageUrl")
        val startingPlaceName: String? = intent.getStringExtra("startingPlaceName")

        packageImage = findViewById(R.id.headerImage)
        Glide.with(this)
            .load(packageImageUrl)
            .into(packageImage)

        travelPackageName = findViewById(R.id.travelTitle)
        loadingTextView = findViewById(R.id.loadingTextView) // Make sure to add this in XML

        travelPackageName.text = "$destinationName: $packageName"

        recyclerView = findViewById(R.id.recyclerView)

        // Show loading text and hide RecyclerView
        loadingTextView.text = "Loading..."
        loadingTextView.visibility = TextView.VISIBLE
        recyclerView.visibility = RecyclerView.GONE

        lifecycleScope.launch {
            var dayWisePlan: String = GeminiRunner.getTravelPlan(
                destinationName,
                packageName,
                if (startingPlaceName != null) "starting from $startingPlaceName" else ""
            )
            dayWisePlan = dayWisePlan.substring(7, dayWisePlan.length - 4)

            val listType = object : TypeToken<List<DayPlan>>() {}.type
            dayPlans = Gson().fromJson(dayWisePlan, listType)

            recyclerView.layoutManager = LinearLayoutManager(this@TravelPlan)
            adapter = DayPlanAdapter(dayPlans)
            recyclerView.adapter = adapter

            // Hide loading text and show RecyclerView after fetching data
            loadingTextView.visibility = TextView.GONE
            recyclerView.visibility = RecyclerView.VISIBLE
        }
    }
}
