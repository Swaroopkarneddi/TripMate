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
            var packageList = runGemini(destinationName)
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
            val adapter = DestinationAdapter3(destinations)
            destinationRecyclerView.adapter = adapter
        }
    }

    suspend fun runGemini(destinationName: String?): String {
        val prompt =
            "data class Destination2(val name: String, val imageResId: Int, val price: String) this is my data class, I want data in this format, leave imageResId for all the different packages available for location $destinationName, give me only the package name and prices as a json object no other text also price should be in INR, give me just the json and no other text"
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-pro",
            apiKey = "AIzaSyCjAivvfgZa2_UG5Vs3P_sAdxtUmllQE8U"
        )

        val promptContent = content {
            text(prompt)
        }

        try {
            val response: GenerateContentResponse = generativeModel.generateContent(promptContent)
            Toast.makeText(this, response.text, Toast.LENGTH_SHORT).show()
            return response.text.toString()
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("error", e.message.toString())
            return e.message.toString()
        }
    }
}
