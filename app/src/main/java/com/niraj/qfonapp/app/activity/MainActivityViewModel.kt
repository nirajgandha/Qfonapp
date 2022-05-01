package com.niraj.qfonapp.app.activity

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.niraj.qfonapp.R
import com.niraj.qfonapp.app.adapter.MenuAdapter
import com.niraj.qfonapp.app.adapter.NavigationDrawersAdapter
import com.niraj.qfonapp.data.model.local.Menus
import com.niraj.qfonapp.data.model.local.NavigationDrawerMenu
import com.niraj.qfonapp.utils.AppConstants

class MainActivityViewModel : ViewModel() {
    var menusArrayList: ArrayList<Menus>? = null
    var navigationDrawerMenuArrayList: ArrayList<NavigationDrawerMenu> = ArrayList()
    var menuAdapter: MenuAdapter? = null
    var resumeFragment: Int = 1
    var selectedFragment: Fragment? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    lateinit var navigationDrawerAdapter: NavigationDrawersAdapter

    fun createBottomNavigationMenuList() {
        menusArrayList = ArrayList()
        var menu = Menus()
        menu.apply {
            menuId = AppConstants.HOME_MENU_ID
            activeIconPath = R.drawable.home_active
            inActiveIconPath = R.drawable.home_inactive
            isSelected = false
        }
        menusArrayList?.add(menu)

        menu = Menus()
        menu.apply {
            menuId = AppConstants.GALLERY_MENU_ID
            activeIconPath = R.drawable.gallery_active
            inActiveIconPath = R.drawable.gallery_inactive
            isSelected = false
        }
        menusArrayList?.add(menu)

        menu = Menus()
        menu.apply {
            menuId = AppConstants.ADD_MENU_ID
            activeIconPath = R.drawable.add_active
            inActiveIconPath = R.drawable.add_inactive
            isSelected = false
        }
        menusArrayList?.add(menu)

        menu = Menus()
        menu.apply {
            menuId = AppConstants.CLIP_MENU_ID
            activeIconPath = R.drawable.clip_active
            inActiveIconPath = R.drawable.clip_inactive
            isSelected = false
        }
        menusArrayList?.add(menu)

        menu = Menus()
        menu.apply {
            menuId = AppConstants.PROFILE_MENU_ID
            activeIconPath = R.drawable.profile_active
            inActiveIconPath = R.drawable.profile_inactive
            isSelected = false
        }
        menusArrayList?.add(menu)
    }

    fun createNavigationDrawerArrayList() {
        navigationDrawerMenuArrayList.clear()
        navigationDrawerMenuArrayList.add(NavigationDrawerMenu("About Us", R.drawable.info))
        navigationDrawerMenuArrayList.add(NavigationDrawerMenu("Privacy Policy", R.drawable.privacy_policy))
        navigationDrawerMenuArrayList.add(NavigationDrawerMenu("My Downloads", R.drawable.my_downloads))
        navigationDrawerMenuArrayList.add(NavigationDrawerMenu("Change Password", R.drawable.password))
        navigationDrawerMenuArrayList.add(NavigationDrawerMenu("Discover People", R.drawable.discover_people))
        navigationDrawerMenuArrayList.add(NavigationDrawerMenu("Logout", R.drawable.logout))
    }
}