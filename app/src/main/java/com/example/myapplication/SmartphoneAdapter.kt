package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class SmartphoneAdapter(context: Context, smartphones: Array<MainActivity.Smartphone>) :
    ArrayAdapter<MainActivity.Smartphone>(context, R.layout.smartphone_list_item, smartphones) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.smartphone_list_item, parent, false)
        }

        val currentItem = getItem(position)

        val imageView = itemView!!.findViewById<ImageView>(R.id.imageView)
        val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
        val textViewPrice = itemView.findViewById<TextView>(R.id.textViewPrice)

        currentItem?.let {
            imageView.setImageResource(it.imageResource)
            textViewName.text = it.name
            textViewPrice.text = "${it.price}"
        }

        return itemView
    }
}
