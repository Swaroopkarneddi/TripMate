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

        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerViewDestinations2)
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val destinations2 = listOf(
            Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),
            Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),Destination("Kerala", R.drawable.kerala),
            Destination("Dubai", R.drawable.dubai),
            Destination("Kashmir", R.drawable.kashmir)
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
