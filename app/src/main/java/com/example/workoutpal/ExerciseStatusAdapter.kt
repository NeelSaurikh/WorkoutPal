package com.example.workoutpal

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutpal.databinding.ItemExeciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExceriseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

        class ViewHolder(binding: ItemExeciseStatusBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val tvItem = binding.tvItem
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExeciseStatusBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExceriseModel = items[position]
        holder.tvItem.text = model.getId().toString()

        val context = holder.itemView.context

    when {
        model.getIsSelected() -> {
            holder.tvItem.background = ContextCompat.getDrawable(
                context, R.drawable.item_circular_thin_color_accent_border
            )
            holder.tvItem.setTextColor(ContextCompat.getColor(context, R.color.black)) // Dark text
        }
        model.getIsCompleted() -> {
            holder.tvItem.background = ContextCompat.getDrawable(
                context, R.drawable.item_circular_thin_color_accent_border
            )

            // Automatically choose text color based on theme
            val nightMode = context.resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
            if (nightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                holder.tvItem.setTextColor(ContextCompat.getColor(context, R.color.white)) // White text for dark mode
            } else {
                holder.tvItem.setTextColor(ContextCompat.getColor(context, R.color.black)) // Black text for light mode
            }
        }
        else -> {
            holder.tvItem.background = ContextCompat.getDrawable(
                context, R.drawable.item_circular_color_grey_background
            )
            holder.tvItem.setTextColor(ContextCompat.getColor(context, R.color.black)) // Dark text
        }
    }
}


    override fun getItemCount(): Int {
        return items.size
    }
}
