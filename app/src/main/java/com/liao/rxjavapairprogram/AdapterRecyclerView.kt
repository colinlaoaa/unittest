package com.liao.rxjavapairprogram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.new_row.view.*

class AdapterRecyclerView(private var mContext: Context, private var myModule: MyModule) :
    RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.new_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = myModule[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return myModule.size
    }

    fun refresh(myModuleRefreshed: MyModule){
        myModule = myModuleRefreshed
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(myModuleItem: MyModuleItem) {
            itemView.textView.text = myModuleItem.id.toString()
            itemView.textView2.text = myModuleItem.userId.toString()
            itemView.textView3.text = myModuleItem.title
            itemView.textView4.text = myModuleItem.body
        }
    }
}