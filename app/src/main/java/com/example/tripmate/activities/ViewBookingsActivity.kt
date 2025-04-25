package com.example.tripmate.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmate.R
import com.example.tripmate.adapters.BookingAdapter
import com.example.tripmate.dataClasses.Booking
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ViewBookingsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookingAdapter
    private val bookings = mutableListOf<Booking>()
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_bookings)

        recyclerView = findViewById(R.id.recyclerViewBookings)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BookingAdapter(bookings)
        recyclerView.adapter = adapter

        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email

        if (currentUserEmail == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            return
        }

        databaseRef = FirebaseDatabase.getInstance().getReference("bookings")

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bookings.clear()
                for (bookingSnap in snapshot.children) {
                    val booking = bookingSnap.getValue(Booking::class.java)
                    if (booking != null && booking.user == currentUserEmail) {
                        bookings.add(booking)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewBookingsActivity, "Failed to load bookings", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
