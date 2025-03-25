package com.example.tripmate.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmate.dataClasses.ExamplePackage
import com.example.tripmate.R
import com.example.tripmate.activities.TravelPlan

class DashboardExamplePackagesAdapter(private val destinations: List<ExamplePackage>) :
    RecyclerView.Adapter<DashboardExamplePackagesAdapter.DestinationViewHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder2 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination2, parent, false)
        return DestinationViewHolder2(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder2, position: Int) {
        val destination = destinations[position]
        holder.destinationName.text = destination.name
        holder.destinationImage.setImageResource(destination.imageResId)
        holder.destinationPriceValue.text = destination.price

        // Handle click event to open TravelPlane Activity
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, TravelPlan::class.java).apply {
                putExtra("destinationName", destination.name)
                putExtra("destinationPrice", destination.price)
                // You can also pass more details like the image if needed
                putExtra("destinationImageResId", destination.imageResId)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = destinations.size

    class DestinationViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destinationImage: ImageView = itemView.findViewById(R.id.destinationImage2)
        val destinationName: TextView = itemView.findViewById(R.id.destinationName2)
        val destinationPriceValue: TextView = itemView.findViewById(R.id.destinationPriceValue2)
    }
}
