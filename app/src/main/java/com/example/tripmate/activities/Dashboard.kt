package com.example.tripmate.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.tripmate.dataClasses.DashboardPlace
import com.example.tripmate.dataClasses.ExamplePackage
import com.example.tripmate.adapters.DashboardPlacesAdapter
import com.example.tripmate.adapters.DashboardExamplePackagesAdapter
import com.example.tripmate.R

class Dashboard : AppCompatActivity() {
    private lateinit var startingPlaceEditText: EditText
    private lateinit var destinationPlaceEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)
//        val animationView = findViewById<LottieAnimationView>(R.id.animation_view)
////        animationView.playAnimation()


        destinationPlaceEditText = findViewById(R.id.destinationPlaceEditText)
        startingPlaceEditText = findViewById(R.id.startingPlaceEditText)
        searchButton = findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            val startingPlace = startingPlaceEditText.text.toString()
            val destinationPlace = destinationPlaceEditText.text.toString()

            if (startingPlace.isNotEmpty() && destinationPlace.isNotEmpty()) {
                val intent = Intent(this, ExclusiveDealsActivity::class.java).apply {
                    putExtra("destinationName", destinationPlace)
                    putExtra("startingPlaceName", startingPlace)
                }
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Please enter both starting and destination places", Toast.LENGTH_SHORT).show()
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDestinations)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val destinations = listOf(
            DashboardPlace("Kerala", R.drawable.kerala),
            DashboardPlace("Dubai", R.drawable.dubai),
            DashboardPlace("Kashmir", R.drawable.kerala),
            DashboardPlace("Paris", R.drawable.dubai),
            DashboardPlace("Meghalaya", R.drawable.kerala),
            DashboardPlace("Venice", R.drawable.dubai),
            DashboardPlace("Coorg", R.drawable.kerala),
            DashboardPlace("Hyderabad", R.drawable.dubai),
            DashboardPlace("Darjeeling", R.drawable.kerala),
            DashboardPlace("Delhi", R.drawable.dubai),
            DashboardPlace("Bankok", R.drawable.kashmir)
        )

        recyclerView.adapter = DashboardPlacesAdapter(destinations)

        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerViewDestinations2)
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val destinations2 = listOf(
            DashboardPlace("Kerala", R.drawable.kerala),
            DashboardPlace("Dubai", R.drawable.dubai),
            DashboardPlace("Kashmir", R.drawable.kerala),
            DashboardPlace("Paris", R.drawable.dubai), DashboardPlace("Coorg", R.drawable.kerala),
            DashboardPlace("Venice", R.drawable.dubai), DashboardPlace("Darjeeling", R.drawable.kerala),
            DashboardPlace("Hyderabad", R.drawable.dubai), DashboardPlace("Meghalaya", R.drawable.kerala),
            DashboardPlace("Delhi", R.drawable.dubai),
            DashboardPlace("Bankok", R.drawable.kashmir)
        )
        val recyclerView3: RecyclerView = findViewById(R.id.recyclerViewExclusiveDeals)


        val exclusiveDeals = listOf(
            ExamplePackage("Bali", R.drawable.kerala, "₹39,900"),
            ExamplePackage("Singapore", R.drawable.dubai, "₹54,500"),
            ExamplePackage("Japan", R.drawable.kashmir, "₹1,45,000"),
            ExamplePackage("Australia", R.drawable.kashmir, "₹1,30,000")
        )
        recyclerView3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView3.adapter = DashboardExamplePackagesAdapter(exclusiveDeals)

        recyclerView2.adapter = DashboardPlacesAdapter(destinations2)
    }
}
