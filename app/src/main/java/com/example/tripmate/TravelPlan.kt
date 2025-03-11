// TravelPlan.kt
package com.example.tripmate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TravelPlan : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DayPlanAdapter

    private val dayPlans = listOf(
        DayPlan("Day 1: Pattaya", "Arrival and leisure time"),
        DayPlan("Day 2: Pattaya", "Coral Island tour"),
        DayPlan("Day 3: Bangkok", "Temple tour and city exploration"),
        DayPlan("Day 4: Bangkok", "Shopping and dinner cruise"),
        DayPlan("Day 5: Krabi", "Beach time and sunset"),
        DayPlan("Day 6: Krabi", "Island hopping tour"),
        DayPlan("Day 7: Phuket", "City tour and cultural show"),
        DayPlan("Day 8: Phuket", "Relaxation and departure")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_plan)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DayPlanAdapter(dayPlans)
        recyclerView.adapter = adapter
    }
}
