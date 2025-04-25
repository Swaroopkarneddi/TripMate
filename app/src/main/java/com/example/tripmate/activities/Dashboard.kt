package com.example.tripmate.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmate.R
import com.example.tripmate.adapters.DashboardExamplePackagesAdapter
import com.example.tripmate.adapters.DashboardPlacesAdapter
import com.example.tripmate.dataClasses.DashboardPlace
import com.example.tripmate.dataClasses.ExamplePackage
import com.google.firebase.auth.FirebaseAuth

class Dashboard : AppCompatActivity() {
    private lateinit var startingPlaceEditText: EditText
    private lateinit var destinationPlaceEditText: EditText
    private lateinit var searchButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        startingPlaceEditText = findViewById(R.id.startingPlaceEditText)
        destinationPlaceEditText = findViewById(R.id.destinationPlaceEditText)
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
            } else {
                Toast.makeText(this, "Please enter both starting and destination places", Toast.LENGTH_SHORT).show()
            }
        }

        val recyclerView1 = findViewById<RecyclerView>(R.id.recyclerViewDestinations)
        recyclerView1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerViewDestinations2)
        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val recyclerView3 = findViewById<RecyclerView>(R.id.recyclerViewExclusiveDeals)
        recyclerView3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val destinations = listOf(
            DashboardPlace("Kerala", R.drawable.kerala),
            DashboardPlace("Dubai", R.drawable.dubai),
            DashboardPlace("Kashmir", R.drawable.kashmir),
            DashboardPlace("Paris", R.drawable.dubai),
            DashboardPlace("Meghalaya", R.drawable.kerala),
            DashboardPlace("Venice", R.drawable.dubai),
            DashboardPlace("Coorg", R.drawable.kerala),
            DashboardPlace("Hyderabad", R.drawable.dubai),
            DashboardPlace("Darjeeling", R.drawable.kerala),
            DashboardPlace("Delhi", R.drawable.dubai),
            DashboardPlace("Bangkok", R.drawable.kashmir)
        )

        val exclusiveDeals = listOf(
            ExamplePackage("Bali", R.drawable.kerala, "₹39,900"),
            ExamplePackage("Singapore", R.drawable.dubai, "₹54,500"),
            ExamplePackage("Japan", R.drawable.kashmir, "₹1,45,000"),
            ExamplePackage("Australia", R.drawable.kashmir, "₹1,30,000")
        )

        recyclerView1.adapter = DashboardPlacesAdapter(destinations)
        recyclerView2.adapter = DashboardPlacesAdapter(destinations)
        recyclerView3.adapter = DashboardExamplePackagesAdapter(exclusiveDeals)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            R.id.view_bookings -> {
                startActivity(Intent(this, ViewBookingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
