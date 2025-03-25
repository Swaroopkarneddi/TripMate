package com.example.tripmate

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class TravelPlan : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DayPlanAdapter

    private lateinit var travelPackageName: TextView

    private lateinit var packageImage: ImageView

    private var dayPlans: MutableList<DayPlan> = mutableListOf<DayPlan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_plan)

        val packageName: String? = intent.getStringExtra("packageName")
        val destinationName: String? = intent.getStringExtra("destinationName")
        val packageImageUrl: String? = intent.getStringExtra("packageImageUrl")

        packageImage = findViewById(R.id.headerImage)
        Glide.with(this)
            .load(packageImageUrl)
            .into(packageImage)

        travelPackageName = findViewById(R.id.travelTitle)
        travelPackageName.text = "$destinationName: $packageName"

        lifecycleScope.launch {
            var dayWisePlan: String = GeminiRunner.getTravelPlan(destinationName, packageName)
            dayWisePlan = dayWisePlan.substring(7, dayWisePlan.length - 4)

            val listType = object : TypeToken<List<DayPlan>>() {}.type
            dayPlans = Gson().fromJson(dayWisePlan, listType)

            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this@TravelPlan)
            adapter = DayPlanAdapter(dayPlans)
            recyclerView.adapter = adapter
        }
    }
}
