package com.example.hien_android_final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide

class RecycleAdapter (private var name: List<String>, private var id: List<String>, private var price: List<String>
, private var priceLast24h: List<String>, private var imageURL: List<String>): RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.coinName)
        val itemID: TextView = itemView.findViewById(R.id.coinID)
        val itemPrice: TextView = itemView.findViewById(R.id.coinPrice)
        val itemLast24h: TextView = itemView.findViewById(R.id.coinLast24h)
        val itemImg: ImageView = itemView.findViewById(R.id.coinImg)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You just clicked on item #${position+1}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return name.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = name[position]
        holder.itemID.text = id[position]
        holder.itemPrice.text = price[position]
        holder.itemLast24h.text = priceLast24h[position]
//        holder.itemImg.text = imageURL[position]
        Glide.with(holder.itemView.context)
            .load(imageURL?.get(position))
            .into(holder.itemImg)
    }

}