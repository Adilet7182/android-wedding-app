package com.example.weddingguesttracker.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingguesttracker.R
import com.example.weddingguesttracker.data.models.Gender
import com.example.weddingguesttracker.data.models.Side
import com.example.weddingguesttracker.data.models.Table

class TableAdapter : RecyclerView.Adapter<TableAdapter.ViewHolder>(){

    private var tables: List<Table> = emptyList()

    interface OnTableClickListener{
        fun onTableClick(table: Table)
    }

    private var onTableClickListener: OnTableClickListener? = null

    fun setOnTableClickListener(listener: OnTableClickListener){
        onTableClickListener = listener
    }

    fun updateTables(newTables: List<Table>){
        tables = newTables
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val table = tables[position]
        holder.bind(table)
    }

    override fun getItemCount(): Int = tables.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val guestsContainer: LinearLayout = view.findViewById(R.id.guestsContainer)

        @SuppressLint("SetTextI18n")
        fun bind(table: Table){
            textViewTitle.text = table.name
            guestsContainer.removeAllViews()

            //сворачивание и разворачивание
            textViewTitle.setOnClickListener {
                table.isExpanded = !table.isExpanded
                notifyDataSetChanged()
            }

            itemView.setOnClickListener {
                onTableClickListener?.onTableClick(table)
            }

            if (table.isExpanded) {
                for (item in table.guests){
                    val guestTextView = TextView(itemView.context)

                    val genderText = if (item.gender == Gender.MALE)  "M" else "Ж"

                    guestTextView.text = item.name + ", " + item.age + " лет, " + genderText

                    if (item.side == Side.BRIDE){
                        guestTextView.setBackgroundColor("#FDE8F5".toColorInt())
                    } else{
                        guestTextView.setBackgroundColor("#E8F5E8".toColorInt())
                    }
                    guestsContainer.addView(guestTextView)
                }
            }
        }
    }
}


























