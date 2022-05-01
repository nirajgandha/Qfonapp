package com.niraj.qfonapp.interfaces

import com.niraj.qfonapp.data.model.local.NavigationDrawerMenu

interface NavigationDrawerItemClickListener {
    fun onNavigationDrawerItemClick(menu_item: NavigationDrawerMenu)
}