package com.example.tripmate

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DestinationAdapter3(private val destinations: List<Destination3>, private val destinationName: String?) :
    RecyclerView.Adapter<DestinationAdapter3.DestinationViewHolder3>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder3 {
        // Inflate the item layout (item_destination3.xml) for each grid item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination2, parent, false)
        return DestinationViewHolder3(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder3, position: Int) {
        // Get the destination for the current position
        val destination = destinations[position]

        // Set the destination name, image, and price in the respective views
        holder.destinationName.text = destination.name
        holder.destinationImage.setImageResource(destination.imageResId)
        holder.destinationPriceValue.text = destination.price

        // Set up the click listener for the item
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, TravelPlan::class.java).apply {
                // Pass data to the TravelPlan activity when an item is clicked
                putExtra("packageName", destination.name)
                putExtra("packagePrice", destination.price)
                putExtra("packageImageResId", destination.imageResId)
                putExtra("destinationName", destinationName)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = destinations.size

    // ViewHolder class for the destination item
    class DestinationViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destinationImage: ImageView = itemView.findViewById(R.id.destinationImage2)
        val destinationName: TextView = itemView.findViewById(R.id.destinationName2)
        val destinationPriceValue: TextView = itemView.findViewById(R.id.destinationPriceValue2)
    }
}
