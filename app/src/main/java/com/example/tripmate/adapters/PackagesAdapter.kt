package com.example.tripmate.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tripmate.dataClasses.PackageElement
import com.example.tripmate.utility.GoogleSearchApi
import com.example.tripmate.R
import com.example.tripmate.dataClasses.SearchResponse
import com.example.tripmate.activities.TravelPlan
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class PackagesAdapter(
    private val destinations: List<PackageElement>,
    private val destinationName: String?,
    private val startingPlaceName: String?
) :
    RecyclerView.Adapter<PackagesAdapter.DestinationViewHolder3>() {
    private val BASE_URL = "https://www.googleapis.com/"
    private val API_KEY = "AIzaSyAK-cNJhIRlJ9S-lXPrtqUDvKF5C39LFnE"
    private val CX = "564bddb7bc8ef441d"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder3 {
        // Inflate the item layout (item_destination3.xml) for each grid item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination2, parent, false)
        return DestinationViewHolder3(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder3, position: Int) {
        // Get the destination for the current position
        val destination: PackageElement = destinations[position]

        // Set the destination name, image, and price in the respective views
        fetchImages(destination, destinationName, holder)
    }

    private fun fetchImages(
        destination: PackageElement,
        destinationName: String?,
        holder: DestinationViewHolder3
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(GoogleSearchApi::class.java)
        val call = api.getImages("$destinationName-${destination.name.split(" ").joinToString("-")}", CX, API_KEY)

        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val imageUrls = response.body()?.items?.mapNotNull { it.link } ?: emptyList()

                    if (imageUrls.isNotEmpty()) {
                        // Load the first image into ImageView using Glide
                        Glide.with(holder.itemView.context)
                            .load(imageUrls[0])
                            .into(holder.destinationImage)

                        holder.destinationName.text = destination.name
                        holder.destinationPriceValue.text = destination.price

                        // Set up the click listener for the item
                        holder.itemView.setOnClickListener {
                            val context = holder.itemView.context
                            val intent = Intent(context, TravelPlan::class.java).apply {
                                // Pass data to the TravelPlan activity when an item is clicked
                                putExtra("packageName", destination.name)
                                putExtra("packagePrice", destination.price)
                                putExtra("packageImageUrl", imageUrls[0])
                                putExtra("destinationName", destinationName)
                                if (startingPlaceName != null) {
                                    putExtra("startingPlaceName", startingPlaceName)
                                }
                            }
                            context.startActivity(intent)
                        }

                    } else {
                        Toast.makeText(holder.itemView.context, "No images found", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Toast.makeText(holder.itemView.context, "Failed to load images", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getItemCount(): Int = destinations.size

    // ViewHolder class for the destination item
    class DestinationViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destinationImage: ImageView = itemView.findViewById(R.id.destinationImage2)
        val destinationName: TextView = itemView.findViewById(R.id.destinationName2)
        val destinationPriceValue: TextView = itemView.findViewById(R.id.destinationPriceValue2)
    }
}
