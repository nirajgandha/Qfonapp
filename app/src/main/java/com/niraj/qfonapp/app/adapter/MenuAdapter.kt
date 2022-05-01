package com.niraj.qfonapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.niraj.qfonapp.data.model.local.Menus
import com.niraj.qfonapp.databinding.BottomNavigationItemBinding
import com.niraj.qfonapp.interfaces.ItemClickListener
import java.util.*

class MenuAdapter(private var menusArrayList: ArrayList<Menus>?, private val itemClickListener: ItemClickListener?, private val context: Context) : RecyclerView.Adapter<MenuAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = BottomNavigationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        menusArrayList ?: return
        itemClickListener ?: return
        with(holder){
            with(menusArrayList!![position]){
                bottomNavigationItemBinding.layMainItem.setOnClickListener { itemClickListener.onItemClick(menuId) }
                bottomNavigationItemBinding.imgMenuActive.setImageResource(activeIconPath)
                bottomNavigationItemBinding.imgMenuInactive.setImageResource(inActiveIconPath)
                if (isSelected) {
                    holder.bottomNavigationItemBinding.imgMenuInactive.visibility = View.GONE
                    holder.bottomNavigationItemBinding.imgMenuActive.visibility = View.VISIBLE
                } else {
                    holder.bottomNavigationItemBinding.imgMenuInactive.visibility = View.VISIBLE
                    holder.bottomNavigationItemBinding.imgMenuActive.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return menusArrayList?.size ?: 0
    }

    fun refreshList(menusArrayList: ArrayList<Menus>) {
        this.menusArrayList = menusArrayList
        notifyDataSetChanged()
    }

    class ViewHolder(val bottomNavigationItemBinding: BottomNavigationItemBinding) : RecyclerView.ViewHolder(bottomNavigationItemBinding.root)

}