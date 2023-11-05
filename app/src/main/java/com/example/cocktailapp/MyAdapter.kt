package com.example.cocktailapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Context,val userList:List<Drink>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var cocktailName:TextView
        var cocktailImage: ImageView

        init {
            cocktailName=itemView.findViewById(R.id.cocktailName)
            cocktailImage = itemView.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cocktailName.text = userList[position].strDrink

        Picasso.get()
            .load(userList[position].strDrinkThumb) // Assuming "strDrinkThumb" is the URL to the image
            .into(holder.cocktailImage)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}