package com.example.handlusernew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.handlusernew.R
import com.example.handlusernew.dto.Subcategory
import com.example.handlusernew.databinding.MainScreenDetailItemBinding

class MainScreenItemDetailAdapter(var context : Context , private  var mData : ArrayList<Subcategory>) : RecyclerView.Adapter<MainSceenDetailVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSceenDetailVH {
        val view  = LayoutInflater.from(context).inflate(R.layout.main_screen_detail_item , parent, false)
        return MainSceenDetailVH(view)
    }

    override fun onBindViewHolder(holder: MainSceenDetailVH, position: Int) {
        val item = mData[position]
        Glide.with(context).load("http://45.56.122.34/${item.image}").placeholder(R.drawable.bg_gradient).into(holder.binding.image)
        holder.binding.title.text = item.title
    }

    override fun getItemCount(): Int {
        return  mData.count()
    }
}
class MainSceenDetailVH(itemView : View) : RecyclerView.ViewHolder(itemView){
    val binding : MainScreenDetailItemBinding = MainScreenDetailItemBinding.bind(itemView)
}