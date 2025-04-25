package com.example.tripmate

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class BookingActivity : AppCompatActivity() {

    private lateinit var destinationText: TextView
    private lateinit var priceText: TextView
    private lateinit var discountSwitch: Switch
    private lateinit var finalPriceText: TextView
    private lateinit var bookButton: Button
    private lateinit var couponInput: TextInputEditText
    private lateinit var personCountInput: EditText

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private var basePrice: Double = 0.0
    private val validCoupons = mapOf(
        "TRIP10" to 0.10,
        "SUMMER15" to 0.15,
        "FLAT20" to 0.20
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        destinationText = findViewById(R.id.destinationText)
        priceText = findViewById(R.id.priceText)
        discountSwitch = findViewById(R.id.discountSwitch)
        finalPriceText = findViewById(R.id.finalPriceText)
        bookButton = findViewById(R.id.bookButton)
        couponInput = findViewById(R.id.couponInput)
        personCountInput = findViewById(R.id.personCountInput)

        val destinationName = intent.getStringExtra("destinationName")
        val price = intent.getDoubleExtra("price", 0.0)
        basePrice = price

        destinationText.text = "Destination: $destinationName"
        priceText.text = "Base Price (per person): ₹${String.format("%.2f", price)}"

        updateFinalPrice()

        discountSwitch.setOnCheckedChangeListener { _, _ -> updateFinalPrice() }

        bookButton.setOnClickListener {
            val numPersons = personCountInput.text.toString().toIntOrNull() ?: 1
            val couponCode = couponInput.text.toString().trim().uppercase()
            val couponDiscount = validCoupons[couponCode] ?: 0.0
            val discountFactor = if (discountSwitch.isChecked) 0.9 else 1.0

            val pricePerPerson = basePrice * discountFactor
            val totalBeforeCoupon = pricePerPerson * numPersons
            val finalPrice = totalBeforeCoupon * (1 - couponDiscount)

            val bookingData = mapOf(
                "user" to auth.currentUser?.email!!,
                "destination" to destinationName,
                "pricePerPerson" to pricePerPerson,
                "totalPrice" to finalPrice,
                "personCount" to numPersons,
                "couponCode" to couponCode,
                "discountApplied" to discountSwitch.isChecked
            )

            val database = FirebaseDatabase.getInstance()
            val bookingRef = database.getReference("bookings")
            bookingRef.push().setValue(bookingData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Booking Confirmed and Saved!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save booking.", Toast.LENGTH_SHORT).show()
                }
        }

        couponInput.setOnFocusChangeListener { _, _ -> updateFinalPrice() }
        personCountInput.setOnFocusChangeListener { _, _ -> updateFinalPrice() }
    }

    private fun updateFinalPrice() {
        val numPersons = personCountInput.text.toString().toIntOrNull() ?: 1
        val couponCode = couponInput.text.toString().trim().uppercase()
        val couponDiscount = validCoupons[couponCode] ?: 0.0
        val discountFactor = if (discountSwitch.isChecked) 0.9 else 1.0

        val pricePerPerson = basePrice * discountFactor
        val totalBeforeCoupon = pricePerPerson * numPersons
        val finalPrice = totalBeforeCoupon * (1 - couponDiscount)

        finalPriceText.text = "Final Price: ₹${String.format("%.2f", finalPrice)}"
    }
}
