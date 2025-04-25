package com.example.tripmate.dataClasses

data class Booking(
    val couponCode: String = "",
    val destination: String = "",
    val discountApplied: Boolean = false,
    val personCount: Int = 0,
    val pricePerPerson: Int = 0,
    val totalPrice: Int = 0,
    val user: String = ""
)
