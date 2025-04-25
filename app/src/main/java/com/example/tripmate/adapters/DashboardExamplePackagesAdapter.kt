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

    companion object {
        private val API_KEYS: List<String> = listOf("AIzaSyBsTmd529pk02fq6VDpPMhGh3YTZQk7lAg","AIzaSyCRmlqIjS-ArWdCfPGn4TTI3F2S6SnGHRE","AIzaSyD43c5Yi5ErYc8ZR9wAx0cjiZBQ2hM2uT0","AIzaSyAK-cNJhIRlJ9S-lXPrtqUDvKF5C39LFnE", "AIzaSyDc1ZpOd3DyPeqHSb2oADA5zgKowM-3dsY", "AIzaSyA_Ae7yOUOaSgvr2TH6c6gGuJHr1xjiJdU")
        private val CX: List<String> = listOf("62d9ac354a8994f28","f348478e6f2654566","b5e9d1bd94b554a6d","d18bc3ac32e234e99","564bddb7bc8ef441d", "230822a88007d4cdc")

        var apiKeyIndex = 0
        var cxIndex = 0

        fun getApiKey(): String {
            val apiKey = API_KEYS[apiKeyIndex]
            apiKeyIndex = (apiKeyIndex + 1) % API_KEYS.size
            return apiKey
        }

        fun getCx(): String {
            val cx = CX[cxIndex]
            cxIndex = (cxIndex + 1) % CX.size
            return cx
        }
    }

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
        val call = api.getImages(destination.name, getCx(), getApiKey())

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