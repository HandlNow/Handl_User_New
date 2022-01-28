package com.example.handlusernew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.handlusernew.R
import com.example.handlusernew.adapter.dto.CategoryModelDTOItem
import com.example.handlusernew.databinding.MainScreenItemBinding

class MainScreenFragmentAdapter(
    private var context: Context,
    private var mData: ArrayList<CategoryModelDTOItem>
) :
    RecyclerView.Adapter<MainScreenVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenVH {
        val view = LayoutInflater.from(context).inflate(R.layout.main_screen_item, parent, false)
        return MainScreenVH(view)

    }

    override fun onBindViewHolder(holder: MainScreenVH, position: Int) {
        val item = mData[position]
        holder.binding.title.text = item.title

        holder.binding.detailRecycle.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val adapter = MainScreenItemDetailAdapter(context, item.subcategory)
        holder.binding.detailRecycle.adapter = adapter

    }

    override fun getItemCount(): Int {
        return mData.count()
    }
}

class MainScreenVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding: MainScreenItemBinding = MainScreenItemBinding.bind(itemView)
}
