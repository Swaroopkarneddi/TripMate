package com.example.tripmate

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ExclusiveDealsActivity : AppCompatActivity() {
    private var packages: List<Package> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exclusive_deals)

        // Find the RecyclerView from the layout
        val destinationRecyclerView: RecyclerView = findViewById(R.id.destinationRecyclerView)

        val destinationName = intent.getStringExtra("destinationName")
        val destinationImageRes = intent.getIntExtra("destinationImageRes", 0)

        lifecycleScope.launch {
            var packageList = GeminiRunner.getPackages(destinationName)
            packageList = packageList.substring(7, packageList.length - 4)

            val listType = object : TypeToken<List<Package>>() {}.type
            packages = Gson().fromJson(packageList, listType)

            // Set GridLayoutManager with 2 columns
            val gridLayoutManager = GridLayoutManager(this@ExclusiveDealsActivity, 2) // 2 columns in the grid
            destinationRecyclerView.layoutManager = gridLayoutManager

            // Sample data (Replace these with actual destination data)
            val destinations: MutableList<Destination3> = mutableListOf<Destination3>()
            for (package1 in packages) {
                destinations.add(Destination3(package1.name, R.drawable.kerala, package1.price))
            }

            // Create and set the adapter
            val adapter = DestinationAdapter3(destinations, destinationName)
            destinationRecyclerView.adapter = adapter
        }
    }


}
