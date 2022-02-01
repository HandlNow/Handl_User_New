package com.dev.handlusernew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.handlusernew.R
import com.dev.handlusernew.adapter.viewholders.MainScreenDetailVH
import com.dev.handlusernew.databinding.MainScreenDetailItemBinding
import com.dev.handlusernew.models.SubCategoryModel
import com.dev.handlusernew.network.URLApi

class MainScreenItemDetailAdapter(
    private var mContext: Context,
    private var mData: ArrayList<SubCategoryModel>
) :
    RecyclerView.Adapter<MainScreenDetailVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenDetailVH {
        return MainScreenDetailVH(
            MainScreenDetailItemBinding.inflate(
                LayoutInflater.from(mContext),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainScreenDetailVH, position: Int) {
        val item = mData[position]
        Glide.with(holder.binding.image)
            .load("${URLApi.BaseImageLink}${item.image}")
            .centerCrop()
            .placeholder(R.drawable.splash_icon)
            .into(holder.binding.image)
        holder.binding.title.text = item.title
    }

    override fun getItemCount(): Int = mData.count()
}

