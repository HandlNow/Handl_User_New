package com.example.handlusernew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handlusernew.R
import com.example.handlusernew.databinding.SideMenuItemBinding

class SideMenuAdapter (private var context : Context , private  var mData : ArrayList<SideMenuModel> , private var onClickCallback :((Int) -> Unit)) : RecyclerView.Adapter<SideMenuVH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SideMenuVH {
        val view = LayoutInflater.from(context).inflate(R.layout.side_menu_item , parent , false)
        return SideMenuVH(view)
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

    override fun getItemCount(): Int {
        return  mData.count()
    }

}
class SideMenuVH (itemView : View) : RecyclerView.ViewHolder(itemView){
    val binding  = SideMenuItemBinding.bind(itemView)
}
class SideMenuModel (
    var title : String ?="",
    var image : Int ?= 0
        )