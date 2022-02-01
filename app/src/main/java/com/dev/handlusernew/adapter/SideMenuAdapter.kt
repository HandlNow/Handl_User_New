package com.dev.handlusernew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.handlusernew.adapter.viewholders.SideMenuVH
import com.dev.handlusernew.databinding.SideMenuItemBinding
import com.dev.handlusernew.models.SideMenuModel

class SideMenuAdapter(
    private var mContext: Context,
    private var mData: ArrayList<SideMenuModel>,
    private var onClickCallback: ((position: Int) -> Unit)
) : RecyclerView.Adapter<SideMenuVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SideMenuVH {
        return SideMenuVH(SideMenuItemBinding.inflate(LayoutInflater.from(mContext), parent, false))
    }

    override fun onBindViewHolder(holder: SideMenuVH, position: Int) {
        val item = mData[position]
        holder.binding.image.setImageResource(item.image ?: 0)
        holder.binding.title.text = item.title
        holder.itemView.let {
            it.setOnClickListener {
                onClickCallback.invoke(position)
            }
        }
    }

    override fun getItemCount(): Int = mData.count()

}

