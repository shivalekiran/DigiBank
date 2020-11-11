package com.fixsolservices.digibank.ui.language

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.util.OnItemClick

class LanguageRecycleAdapter(val itemsList: Array<String>, val onItemClick: OnItemClick) :
    RecyclerView.Adapter<LanguageRecycleAdapter.MyViewHolder>() {

    lateinit var context: Context
    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val languageItem =
            LayoutInflater.from(parent.context).inflate(R.layout.language_item, parent, false)
        return MyViewHolder(languageItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var text: TextView = view.findViewById(R.id.language)
        val viewTop: View = view.findViewById(R.id.view)
        val viewBottom: View = view.findViewById(R.id.view2)
        val selectionImg: ImageView = view.findViewById(R.id.language_selection)

        fun bindView() {
            text.text = itemsList[adapterPosition]
            if (adapterPosition == selectedPosition) {
                selectItem()
            } else {
                deSelectItem()
            }
            text.setOnClickListener {
                onItemClick.onItemClick(adapterPosition)
                selectedPosition = adapterPosition
                notifyDataSetChanged()
            }
        }

        private fun deSelectItem() {

            viewTop.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.language_divider
                )
            )
            viewBottom.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.language_divider
                )
            )
            selectionImg.visibility = View.GONE
        }

        private fun selectItem() {
            viewTop.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimaryDark
                )
            )
            viewBottom.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimaryDark
                )
            )
            selectionImg.visibility = View.VISIBLE
        }
    }
}