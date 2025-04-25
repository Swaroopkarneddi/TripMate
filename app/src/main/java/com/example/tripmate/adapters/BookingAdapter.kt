package com.example.tripmate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmate.R
import com.example.tripmate.dataClasses.Booking

class BookingAdapter(private val bookings: List<Booking>) :
    RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    class BookingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val destination: TextView = view.findViewById(R.id.destinationText)
        val totalPrice: TextView = view.findViewById(R.id.totalPriceText)
        val personCount: TextView = view.findViewById(R.id.personCountText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.booking_item, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.destination.text = "Destination: ${booking.destination}"
        holder.totalPrice.text = "Total Price: ₹${booking.totalPrice}"
        holder.personCount.text = "Persons: ${booking.personCount}"
    }

    override fun getItemCount() = bookings.size
}
