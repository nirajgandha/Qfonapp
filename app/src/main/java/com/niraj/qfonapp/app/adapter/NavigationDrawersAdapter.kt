package com.niraj.qfonapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.niraj.qfonapp.data.model.local.NavigationDrawerMenu
import com.niraj.qfonapp.databinding.NavigationDrawerRecyclerviewItemBinding
import com.niraj.qfonapp.interfaces.NavigationDrawerItemClickListener
import java.util.*

class NavigationDrawersAdapter(
    private var productCategoryArrayList: ArrayList<NavigationDrawerMenu>,
    private val navigationDrawerItemClickListener: NavigationDrawerItemClickListener,
    private val context: Context
) : RecyclerView.Adapter<NavigationDrawersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = NavigationDrawerRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(productCategoryArrayList[position]) {
                navigationDrawerRecyclerviewItemBinding.menuTitle.text = menu_name
                navigationDrawerRecyclerviewItemBinding.imgIcon.setImageDrawable(AppCompatResources.getDrawable(context, menu_icon))
                navigationDrawerRecyclerviewItemBinding.root.setOnClickListener {
                    navigationDrawerItemClickListener.onNavigationDrawerItemClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return productCategoryArrayList.size
    }

    class ViewHolder(val navigationDrawerRecyclerviewItemBinding: NavigationDrawerRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(navigationDrawerRecyclerviewItemBinding.root)

}