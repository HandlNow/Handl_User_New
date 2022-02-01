package com.dev.handlusernew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.handlusernew.adapter.viewholders.MainScreenVH
import com.dev.handlusernew.databinding.MainScreenItemBinding
import com.dev.handlusernew.models.CategoryItemModel

class MainScreenFragmentAdapter(
    private var mContext: Context,
    private var mData: ArrayList<CategoryItemModel>
) :
    RecyclerView.Adapter<MainScreenVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenVH {
        return MainScreenVH(
            MainScreenItemBinding.inflate(
                LayoutInflater.from(mContext),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: MainScreenVH, position: Int) {
        val item = mData[position]
        holder.binding.title.text = item.nameTitle
        holder.binding.detailRecycle.layoutManager =
            LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        val adapter = MainScreenItemDetailAdapter(mContext, item.subcategory ?: ArrayList())
        holder.binding.detailRecycle.adapter = adapter

    }

    override fun getItemCount(): Int = mData.count()
}

