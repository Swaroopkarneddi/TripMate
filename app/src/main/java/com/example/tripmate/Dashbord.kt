package com.example.tripmate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmate.R

class Dashbord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDestinations)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val destinations = listOf(
            Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),
            Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),
            Destination("Kashmir", R.drawable.kashmir)
        )

        recyclerView.adapter = DestinationAdapter(destinations)
    }
}
