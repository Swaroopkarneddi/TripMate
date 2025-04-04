package com.example.tripmate.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tripmate.dataClasses.DashboardPlace
import com.example.tripmate.R
import com.example.tripmate.activities.ExclusiveDealsActivity

class DashboardPlacesAdapter(private val destinations: List<DashboardPlace>) :
    RecyclerView.Adapter<DashboardPlacesAdapter.ViewHolder>() {

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
        Glide.with(holder.itemView.context)
            .load(destination.imageRes)
            .circleCrop()
            .into(holder.imageView)

        // Handle item click to open ExclusiveDealsActivity
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ExclusiveDealsActivity::class.java).apply {
                putExtra("destinationName", destination.name)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = destinations.size
}
