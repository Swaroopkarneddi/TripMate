package com.example.tripmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmate.R

class Dashbord : AppCompatActivity() {
    private lateinit var startingPlaceEditText: EditText
    private lateinit var destinationPlaceEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)

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
            Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),
            Destination("Kashmir", R.drawable.kerala),
            Destination("Paris", R.drawable.dubai),Destination("Meghalaya", R.drawable.kerala),
            Destination("Venice", R.drawable.dubai),Destination("Coorg", R.drawable.kerala),
            Destination("Hyderabad", R.drawable.dubai),Destination("Darjeeling", R.drawable.kerala),
            Destination("Delhi", R.drawable.dubai),
            Destination("Bankok", R.drawable.kashmir)
        )

        recyclerView.adapter = DestinationAdapter(destinations)

        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerViewDestinations2)
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val destinations2 = listOf(
            Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),
            Destination("Kashmir", R.drawable.kerala),
            Destination("Paris", R.drawable.dubai),Destination("Coorg", R.drawable.kerala),
            Destination("Venice", R.drawable.dubai),Destination("Darjeeling", R.drawable.kerala),
            Destination("Hyderabad", R.drawable.dubai),Destination("Meghalaya", R.drawable.kerala),
            Destination("Delhi", R.drawable.dubai),
            Destination("Bankok", R.drawable.kashmir)
        )
        val recyclerView3: RecyclerView = findViewById(R.id.recyclerViewExclusiveDeals)


        val exclusiveDeals = listOf(
            Destination2("Bali", R.drawable.kerala, "₹39,900"),
            Destination2("Singapore", R.drawable.dubai, "₹54,500"),
            Destination2("Japan", R.drawable.kashmir, "₹1,45,000"),
            Destination2("Australia", R.drawable.kashmir, "₹1,30,000")
        )
        recyclerView3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView3.adapter = DestinationAdapter2(exclusiveDeals)

        recyclerView2.adapter = DestinationAdapter(destinations2)
    }
}
