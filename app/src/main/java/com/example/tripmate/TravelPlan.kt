package com.example.tripmate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TravelPlan : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DayPlanAdapter

//    private val dayPlans = listOf(
//        DayPlan(
//            "Day 1: Pattaya", "Arrival and leisure time",
//            "Flight", "New Delhi to Bangkok", "Flight 8:00 AM, XYZ123",
//            "Transfer . 2 Hours", "Pattaya Hotel to Bangkok Hotel", "Private transfer with guide",
//            "Hotel . 2 Nights . In Pattaya", "Pattaya Grand Resort", "Luxury hotel near beach",
//            "Activity . 5 Hours", "Island Tour", "Exploring Coral Island",
//            "Menu . Lunch", "Seafood lunch", "Enjoy fresh seafood at a beachside restaurant"
//        ),
//        DayPlan(
//            "Day 2: Pattaya", "Coral Island tour",
//            "Flight", "Bangkok to Krabi", "Flight 10:30 AM, ABC456",
//            "Transfer . 1 Hour", "Airport to Hotel", "Taxi ride",
//            "Hotel . 2 Nights . In Krabi", "Krabi Sea Resort", "Ocean-view stay",
//            "Activity . 3 Hours", "Beach Time", "Relaxing at Ao Nang Beach",
//            "Menu . Dinner", "Thai Dinner", "Authentic Thai cuisine at a local restaurant"
//        )
//        // Add more days here...
//    )


    private val dayPlans = listOf(
        DayPlan(
            "Day 1: Pattaya", "Arrival and leisure time",
            "Flight", "New Delhi to Bangkok", "Flight 8:00 AM, XYZ123",
            "Transfer . 2 Hours", "Pattaya Hotel to Bangkok Hotel", "Private transfer with guide",
            "Hotel . 2 Nights . In Pattaya", "Pattaya Grand Resort", "Luxury hotel near beach",
            "Activity . 5 Hours", "Island Tour", "Exploring Coral Island",
            "Menu . Lunch", "Seafood lunch", "Enjoy fresh seafood at a beachside restaurant"
        ),
        DayPlan(
            "Day 2: Pattaya", "Coral Island tour",
            "Flight", "Bangkok to Krabi", "Flight 10:30 AM, ABC456",
            "Transfer . 1 Hour", "Airport to Hotel", "Taxi ride",
            "Hotel . 2 Nights . In Krabi", "Krabi Sea Resort", "Ocean-view stay",
            "Activity . 3 Hours", "Beach Time", "Relaxing at Ao Nang Beach",
            "Menu . Dinner", "Thai Dinner", "Authentic Thai cuisine at a local restaurant"
        ),
        DayPlan(
            "Day 3: Krabi", "Exploring Krabi",
            "Flight", "", "", // No flight planned for this day
            "Transfer . 1 Hour", "Hotel to Railay Beach", "Private taxi",
            "Hotel . 2 Nights . In Krabi", "Krabi Sea Resort", "Ocean-view stay",
            "Activity . 4 Hours", "Railay Beach", "Explore the limestone cliffs and caves",
            "Food", "", "" // No meal planned for this day
        ),
        DayPlan(
            "Day 4: Krabi", "Phi Phi Islands Tour",
            "Flight", "", "", // No flight planned for this day
            "Transfer . 2 Hours", "Hotel to Phi Phi Islands", "Boat ride",
            "Hotel . 2 Nights . In Krabi", "Krabi Sea Resort", "Ocean-view stay",
            "Activity . 6 Hours", "Snorkeling and Island Tour", "Snorkel and swim in crystal-clear waters",
            "Menu . Dinner", "BBQ Dinner", "Enjoy a BBQ dinner at the resort"
        ),
        DayPlan(
            "Day 5: Phuket", "Arrival and relaxation",
            "Flight", "Krabi to Phuket", "Flight 9:00 AM, DEF789",
            "Transfer . 1 Hour", "Phuket Airport to Hotel", "Private transfer",
            "Hotel . 2 Nights . In Phuket", "Phuket Beach Resort", "Luxury beachfront resort",
            "Activity . 2 Hours", "Beach Relaxation", "Relaxing on the beach",
            "Food", "", "" // No meal planned for this day
        ),
        DayPlan(
            "Day 6: Phuket", "Phuket Sightseeing",
            "Flight", "", "", // No flight planned for this day
            "Transfer . 1 Hour", "Hotel to Phuket Old Town", "Private taxi",
            "Hotel . 2 Nights . In Phuket", "Phuket Beach Resort", "Luxury beachfront resort",
            "Activity . 5 Hours", "Old Town Exploration", "Explore colonial architecture and local shops",
            "Menu . Dinner", "Seafood Feast", "Fresh seafood at a famous restaurant"
        ),
        DayPlan(
            "Day 7: Phuket", "Phi Phi Islands",
            "Flight", "", "", // No flight planned for this day
            "Transfer . 2 Hours", "Hotel to Phi Phi Islands", "Boat ride",
            "Hotel . 1 Night . In Phuket", "Phuket Beach Resort", "Luxury beachfront resort",
            "Activity . 5 Hours", "Phi Phi Islands Snorkeling", "Snorkeling and beach activities",
            "Food", "", "" // No meal planned for this day
        ),
        DayPlan(
            "Day 8: Bangkok", "Shopping and sightseeing",
            "Flight", "Phuket to Bangkok", "Flight 11:00 AM, GHI012",
            "Transfer . 1 Hour", "Airport to Hotel", "Private transfer",
            "Hotel . 2 Nights . In Bangkok", "Bangkok Luxury Hotel", "5-star hotel in the heart of the city",
            "Activity . 4 Hours", "Shopping at MBK", "Explore the popular MBK shopping mall",
            "Menu . Lunch", "Thai Street Food", "Taste the best Thai street food in the market"
        ),
        DayPlan(
            "Day 9: Bangkok", "Cultural Tour",
            "Flight", "", "", // No flight planned for this day
            "Transfer . 1 Hour", "Hotel to Grand Palace", "Private taxi",
            "Hotel . 1 Night . In Bangkok", "Bangkok Luxury Hotel", "5-star hotel in the heart of the city",
            "Activity . 5 Hours", "Grand Palace and Wat Pho", "Explore the Grand Palace and Wat Pho temples",
            "Menu . Dinner", "Traditional Thai Cuisine", "Traditional Thai dinner at a fine restaurant"
        )
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
