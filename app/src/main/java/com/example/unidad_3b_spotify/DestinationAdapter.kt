package com.example.unidad_3b_spotify

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DestinationAdapter(private val mDataSet: List<String>) : RecyclerView.Adapter<DestinationAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_block_destination, parent, false)
        return MainViewHolder(v)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
        holder.bindItems(data)
    }
    override fun getItemCount(): Int {
        return mDataSet.size
    }
    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        fun bindItems(data: String) {
            //v.text1.text = data
            Log.i("prueba", "conseguido amigos")
        }
    }
}