package com.niraj.qfonapp.app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.niraj.qfonapp.R
import com.niraj.qfonapp.app.adapter.MenuAdapter
import com.niraj.qfonapp.app.adapter.NavigationDrawersAdapter
import com.niraj.qfonapp.app.fragment.add_fragment.AddFragment
import com.niraj.qfonapp.app.fragment.clip_fragment.ClipFragment
import com.niraj.qfonapp.app.fragment.gallery_fragment.GalleryFragment
import com.niraj.qfonapp.app.fragment.home_fragment.HomeFragment
import com.niraj.qfonapp.app.fragment.profile_fragment.ProfileFragment
import com.niraj.qfonapp.data.model.local.NavigationDrawerMenu
import com.niraj.qfonapp.databinding.ActivityMainBinding
import com.niraj.qfonapp.interfaces.ItemClickListener
import com.niraj.qfonapp.interfaces.NavigationDrawerItemClickListener
import com.niraj.qfonapp.utils.AppConstants
import com.niraj.qfonapp.utils.SpanningLinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemClickListener, NavigationDrawerItemClickListener {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var itemClickListener: ItemClickListener? = null
    private var navigationDrawerItemClickListener: NavigationDrawerItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
    }

    private fun loadData() {
        initializeDrawer()
        createMenuArrayList()
        onItemClick(AppConstants.HOME_MENU_ID)
    }
    private fun initializeDrawer() {
        navigationDrawerItemClickListener = this
        mainActivityViewModel.createNavigationDrawerArrayList()

        binding.navigationDrawerRecyclerview.layoutManager = LinearLayoutManager(this)
        mainActivityViewModel.navigationDrawerAdapter = NavigationDrawersAdapter(mainActivityViewModel.navigationDrawerMenuArrayList, navigationDrawerItemClickListener!!, this)
        binding.navigationDrawerRecyclerview.adapter = mainActivityViewModel.navigationDrawerAdapter

        mainActivityViewModel.actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_open, R.string.drawer_close)
        binding.drawerLayout.addDrawerListener(mainActivityViewModel.actionBarDrawerToggle!!)
        binding.toolbar.toolbarNavButton.setOnClickListener { binding.drawerLayout.open() }
        binding.closeDrawer.setOnClickListener { binding.drawerLayout.closeDrawers() }
        binding.settings.setOnClickListener { binding.drawerLayout.closeDrawers() }
    }

    private fun createMenuArrayList() {
        itemClickListener = this
        mainActivityViewModel.createBottomNavigationMenuList()

        mainActivityViewModel.menuAdapter = MenuAdapter(mainActivityViewModel.menusArrayList, itemClickListener, this@MainActivity)

        val spanningLinearLayoutManager = SpanningLinearLayoutManager(this, mainActivityViewModel.menusArrayList?.size)
        spanningLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        spanningLinearLayoutManager.setScrollHorizontally(false)
        spanningLinearLayoutManager.setMaxItemsToShowInScreen(mainActivityViewModel.menusArrayList!!.size)
        binding.bottomNavigationBar.layoutManager = spanningLinearLayoutManager
        binding.bottomNavigationBar.adapter = mainActivityViewModel.menuAdapter
        onItemClick(mainActivityViewModel.resumeFragment)
    }

    override fun onItemClick(menu_id: Int) {
        when(menu_id) {
            AppConstants.HOME_MENU_ID -> {
                if (mainActivityViewModel.selectedFragment !is HomeFragment) {
                    openFragment(HomeFragment())
                }
            }
            AppConstants.GALLERY_MENU_ID -> {
                if (mainActivityViewModel.selectedFragment !is GalleryFragment) {
                    openFragment(GalleryFragment())
                }
            }
            AppConstants.ADD_MENU_ID -> {
                if (mainActivityViewModel.selectedFragment !is AddFragment) {
                    openFragment(AddFragment())
                }
            }
            AppConstants.CLIP_MENU_ID -> {
                if (mainActivityViewModel.selectedFragment !is ClipFragment) {
                    openFragment(ClipFragment())
                }
            }
            AppConstants.PROFILE_MENU_ID -> {
                if (mainActivityViewModel.selectedFragment !is ProfileFragment) {
                    openFragment(ProfileFragment())
                }
            }
        }
        setSelected(menu_id)
    }

    /**
     * Open Fragment
     *
     * @param fragment Fragment whichever has to be opened
     * @param addToStack boolean whether fragment has to be added to back-stack
     */
    private fun openFragment(fragment: Fragment, addToStack: Boolean = false) {
        mainActivityViewModel.selectedFragment = fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentContainer.id, fragment)
        if (addToStack) {
            transaction.addToBackStack(fragment.javaClass.name)
        }
        transaction.commit()
    }

    /**
     * Item Selected Color Change
     *
     * @param index selected item index
     */
    private fun setSelected(index: Int) {
        if (mainActivityViewModel.menusArrayList != null && mainActivityViewModel.menusArrayList!!.size > 0) {
            for (i in mainActivityViewModel.menusArrayList!!.indices) {
                val menu = mainActivityViewModel.menusArrayList!![i]
                menu.isSelected = i == index
                mainActivityViewModel.menusArrayList!![i] = menu
            }
            mainActivityViewModel.menuAdapter?.refreshList(mainActivityViewModel.menusArrayList!!)
        }
    }

    override fun onNavigationDrawerItemClick(menu_item: NavigationDrawerMenu) {
        binding.drawerLayout.closeDrawers()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mainActivityViewModel.actionBarDrawerToggle?.syncState()
    }
}