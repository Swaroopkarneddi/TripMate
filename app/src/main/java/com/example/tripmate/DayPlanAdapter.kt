package com.example.tripmate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DayPlanAdapter(private val dayPlans: List<DayPlan>) : RecyclerView.Adapter<DayPlanAdapter.DayPlanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayPlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day_plan, parent, false)
        return DayPlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayPlanViewHolder, position: Int) {
        val dayPlan = dayPlans[position]
        holder.tvDay.text = dayPlan.day
        holder.tvPlan.text = dayPlan.plan
        holder.tvFlightPlan.text = dayPlan.flightPlan
        holder.tvFlightDetails.text = dayPlan.flightDetails
        holder.tvFlightTiming.text = dayPlan.flightTiming
        holder.tvTransferPlan.text = dayPlan.transferPlan
        holder.tvTransferDetails.text = dayPlan.transferDetails
        holder.tvTransferDescription.text = dayPlan.transferDescription
        holder.tvHotelPlan.text = dayPlan.hotelPlan
        holder.tvHotelName.text = dayPlan.hotelName
        holder.tvHotelDescription.text = dayPlan.hotelDescription
        holder.tvActivityPlan.text = dayPlan.activityPlan
        holder.tvActivityName.text = dayPlan.activityName
        holder.tvActivityDescription.text = dayPlan.activityDescription
        holder.tvMenuPlan.text = dayPlan.menuPlan
        holder.tvMenuDetails.text = dayPlan.menuDetails
        holder.tvMenuDescription.text = dayPlan.menuDescription
    }

    override fun getItemCount(): Int = dayPlans.size

    class DayPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
        val tvPlan: TextView = itemView.findViewById(R.id.tvPlan)
        val tvFlightPlan: TextView = itemView.findViewById(R.id.tvflighhtPlan)
        val tvFlightDetails: TextView = itemView.findViewById(R.id.tvflightPlan2)
        val tvFlightTiming: TextView = itemView.findViewById(R.id.tvflightPlan3)
        val tvTransferPlan: TextView = itemView.findViewById(R.id.tvTransferPlan)
        val tvTransferDetails: TextView = itemView.findViewById(R.id.tvTransferPlan2)
        val tvTransferDescription: TextView = itemView.findViewById(R.id.tvTransferPlan3)
        val tvHotelPlan: TextView = itemView.findViewById(R.id.tvHotelPlan)
        val tvHotelName: TextView = itemView.findViewById(R.id.tvHotelPlan2)
        val tvHotelDescription: TextView = itemView.findViewById(R.id.tvHotelPlan3)
        val tvActivityPlan: TextView = itemView.findViewById(R.id.tvActivityPlan)
        val tvActivityName: TextView = itemView.findViewById(R.id.tvActivityPlan2)
        val tvActivityDescription: TextView = itemView.findViewById(R.id.tvActivityPlan3)
        val tvMenuPlan: TextView = itemView.findViewById(R.id.tvMenuePlan)
        val tvMenuDetails: TextView = itemView.findViewById(R.id.tvMenuePlan2)
        val tvMenuDescription: TextView = itemView.findViewById(R.id.tvMenuePlan3)
    }
}
