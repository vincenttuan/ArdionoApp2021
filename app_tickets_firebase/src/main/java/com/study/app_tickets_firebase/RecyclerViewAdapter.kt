package com.study.app_tickets_firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.order.view.*

// 適配器(配置每一筆紀錄的擺放方式)
class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var orderList: List<String> = ArrayList<String>()
    fun setOrders(orderList: List<String>) {
        this.orderList = orderList
    }
    // View 配置方式
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val record = view.tv_record
        fun bind(data: String) {
            record.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(orderList[position])
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}