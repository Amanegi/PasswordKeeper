package com.example.aman_negi.passwordkeeper.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aman_negi.passwordkeeper.R
import com.example.aman_negi.passwordkeeper.RowLayout

class MyAdapter(val context: Context, val dataSource: MutableList<RowLayout>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.row_layout, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val r = dataSource.get(p1)
        p0.txtSite.setText(r.site)
        p0.txtUsername.setText(r.username)
        p0.txtPassword.setText(r.password)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnCreateContextMenuListener {

        val txtSite = itemView.findViewById<TextView>(R.id.txt_row_Site)
        val txtUsername = itemView.findViewById<TextView>(R.id.txt_row_Username)
        val txtPassword = itemView.findViewById<TextView>(R.id.txt_row_Password)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)

        init {
            cardView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.add(this.layoutPosition, 111, 0, "Copy Username")
            menu?.add(this.layoutPosition, 112, 1, "Edit")
            menu?.add(this.layoutPosition, 113, 2, "Delete")
        }

    }
}