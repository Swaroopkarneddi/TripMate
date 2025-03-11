package com.example.tripmate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExclusiveDealsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exclusive_deals)

        // Find the RecyclerView from the layout
        val destinationRecyclerView: RecyclerView = findViewById(R.id.destinationRecyclerView)

        // Set GridLayoutManager with 2 columns
        val gridLayoutManager = GridLayoutManager(this, 2) // 2 columns in the grid
        destinationRecyclerView.layoutManager = gridLayoutManager

        // Sample data (Replace these with actual destination data)
        val destinations = listOf(
            Destination3("Paris", R.drawable.dubai, "₹90,000"),
            Destination3("London", R.drawable.kerala, "₹80,000"),
            Destination3("New York", R.drawable.kashmir, "₹120,000"),
            Destination3("Paris", R.drawable.dubai, "₹90,000"),
            Destination3("London", R.drawable.kerala, "₹80,000"),
            Destination3("New York", R.drawable.kashmir, "₹120,000"),
            Destination3("Paris", R.drawable.dubai, "₹90,000"),
            Destination3("London", R.drawable.kerala, "₹80,000"),
            Destination3("New York", R.drawable.kashmir, "₹120,000"),Destination3("Paris", R.drawable.dubai, "₹90,000"),
            Destination3("London", R.drawable.kerala, "₹80,000"),
            Destination3("New York", R.drawable.kashmir, "₹120,000"),

            Destination3("Tokyo", R.drawable.dubai, "₹100,000")
        )

        // Create and set the adapter
        val adapter = DestinationAdapter3(destinations)
        destinationRecyclerView.adapter = adapter
    }
}
