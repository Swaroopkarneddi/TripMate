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
import com.example.tripmate.activities.ExclusiveDealsActivity
import com.example.tripmate.dataClasses.DashboardPlace
import com.example.tripmate.dataClasses.SearchResponse
import com.example.tripmate.utility.GoogleSearchApi
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class DashboardPlacesAdapter(private val destinations: List<DashboardPlace>) :
    RecyclerView.Adapter<DashboardPlacesAdapter.ViewHolder>() {

    private val BASE_URL = "https://www.googleapis.com/"

    companion object {
        private val API_KEYS: List<String> = listOf("AIzaSyAK-cNJhIRlJ9S-lXPrtqUDvKF5C39LFnE", "AIzaSyDc1ZpOd3DyPeqHSb2oADA5zgKowM-3dsY", "AIzaSyA_Ae7yOUOaSgvr2TH6c6gGuJHr1xjiJdU")
        private val CX: List<String> = listOf("564bddb7bc8ef441d", "230822a88007d4cdc")

        var apiKeyIndex = 1
        var cxIndex = 1

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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.destinationImage)
        val textView: TextView = view.findViewById(R.id.destinationName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinations[position]
        holder.textView.text = destination.name

        fetchImage(destination.name, holder)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ExclusiveDealsActivity::class.java).apply {
                putExtra("destinationName", destination.name)
            }
            context.startActivity(intent)
        }
    }

    private fun fetchImage(query: String, holder: ViewHolder) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(GoogleSearchApi::class.java)
        val call = api.getImages(query, getCx(), getApiKey())

        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val imageUrls = response.body()?.items?.mapNotNull { it.link } ?: emptyList()
                    if (imageUrls.isNotEmpty()) {
                        Glide.with(holder.itemView.context)
                            .load(imageUrls[0])
                            .circleCrop()
                            .into(holder.imageView)
                    } else {
                        Toast.makeText(holder.itemView.context, "No image found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(holder.itemView.context, "Failed to fetch image", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Toast.makeText(holder.itemView.context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getItemCount() = destinations.size
}