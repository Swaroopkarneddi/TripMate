// DayPlanAdapter.kt
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
    }

    override fun getItemCount(): Int = dayPlans.size

    class DayPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
        val tvPlan: TextView = itemView.findViewById(R.id.tvPlan)
    }
}
