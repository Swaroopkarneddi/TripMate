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
import com.example.tripmate.R
import com.example.tripmate.activities.TravelPlan
import com.example.tripmate.dataClasses.ExamplePackage
import com.example.tripmate.dataClasses.SearchResponse
import com.example.tripmate.utility.GoogleSearchApi
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class DashboardExamplePackagesAdapter(private val destinations: List<ExamplePackage>) :
    RecyclerView.Adapter<DashboardExamplePackagesAdapter.DestinationViewHolder2>() {

    private val BASE_URL = "https://www.googleapis.com/"
    private val API_KEY = "AIzaSyAK-cNJhIRlJ9S-lXPrtqUDvKF5C39LFnE"
    private val CX = "564bddb7bc8ef441d"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder2 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination2, parent, false)
        return DestinationViewHolder2(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder2, position: Int) {
        val destination = destinations[position]

        holder.destinationName.text = destination.name
        holder.destinationPriceValue.text = destination.price

        fetchImage(destination, holder)
    }

    private fun fetchImage(destination: ExamplePackage, holder: DestinationViewHolder2) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(GoogleSearchApi::class.java)
        val call = api.getImages(destination.name, CX, API_KEY)

        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val imageUrls = response.body()?.items?.mapNotNull { it.link } ?: emptyList()
                    if (imageUrls.isNotEmpty()) {
                        val imageUrl = imageUrls[0]

                        Glide.with(holder.itemView.context)
                            .load(imageUrl)
                            .into(holder.destinationImage)

                        holder.itemView.setOnClickListener {
                            val context = holder.itemView.context
                            val intent = Intent(context, TravelPlan::class.java).apply {
                                putExtra("packageName", destination.name)
                                putExtra("packagePrice", destination.price)
                                putExtra("packageImageUrl", imageUrl)
                                putExtra("destinationName", destination.name)
                            }
                            context.startActivity(intent)
                        }

                    } else {
                        Toast.makeText(holder.itemView.context, "No image found", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Toast.makeText(holder.itemView.context, "Failed to load image", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getItemCount(): Int = destinations.size

    class DestinationViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destinationImage: ImageView = itemView.findViewById(R.id.destinationImage2)
        val destinationName: TextView = itemView.findViewById(R.id.destinationName2)
        val destinationPriceValue: TextView = itemView.findViewById(R.id.destinationPriceValue2)
    }
}
