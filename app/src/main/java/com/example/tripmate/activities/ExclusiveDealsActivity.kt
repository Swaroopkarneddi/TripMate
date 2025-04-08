package com.example.tripmate.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.tripmate.dataClasses.PackageElement
import com.example.tripmate.adapters.PackagesAdapter
import com.example.tripmate.utility.GeminiRunner
import com.example.tripmate.dataClasses.Package
import com.example.tripmate.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ExclusiveDealsActivity : AppCompatActivity() {
    private var packages: List<Package> = emptyList()
    private lateinit var journeyTextView: TextView
//    private lateinit var loadingTextView: TextView // Loading text view
private lateinit var loadingAnimationView: LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exclusive_deals)

        val destinationRecyclerView: RecyclerView = findViewById(R.id.destinationRecyclerView)
        journeyTextView = findViewById(R.id.journeyDetails)
//        loadingTextView = findViewById(R.id.loadingTextView) // Make sure this is defined in XML

        val destinationName: String? = intent.getStringExtra("destinationName")
        val startingPlaceName: String? = intent.getStringExtra("startingPlaceName")

        journeyTextView.text = if (startingPlaceName != null) "$startingPlaceName -> $destinationName" else "$destinationName"

        // Show loading text
//        loadingTextView.text = "Loading..."
//        loadingTextView.visibility = TextView.VISIBLE
        loadingAnimationView = findViewById(R.id.loadingAnimationView)
        loadingAnimationView.visibility = LottieAnimationView.VISIBLE
        destinationRecyclerView.visibility = RecyclerView.GONE

        lifecycleScope.launch {
            var packageList = GeminiRunner.getPackages(destinationName)
            packageList = packageList.substring(7, packageList.length - 4)

            val listType = object : TypeToken<List<Package>>() {}.type
            packages = Gson().fromJson(packageList, listType)

            val gridLayoutManager = GridLayoutManager(this@ExclusiveDealsActivity, 2)
            destinationRecyclerView.layoutManager = gridLayoutManager

            val destinations: MutableList<PackageElement> = mutableListOf()
            for (package1 in packages) {
                destinations.add(PackageElement(package1.name, R.drawable.kerala, package1.price))
            }

            val adapter = PackagesAdapter(destinations, destinationName, startingPlaceName)
            destinationRecyclerView.adapter = adapter

            // Hide loading text and show RecyclerView after fetching data
//            loadingTextView.visibility = TextView.GONE
            loadingAnimationView.visibility = LottieAnimationView.GONE
            destinationRecyclerView.visibility = RecyclerView.VISIBLE
        }
    }
}

