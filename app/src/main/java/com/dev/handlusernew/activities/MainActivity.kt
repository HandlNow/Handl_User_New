package com.dev.handlusernew.activities


import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.handlusernew.R
import com.dev.handlusernew.adapter.SideMenuAdapter
import com.dev.handlusernew.databinding.ActivityMainBinding
import com.dev.handlusernew.interfaces.DrawerStateInterface
import com.dev.handlusernew.interfaces.OpenThePopUpMenu
import com.dev.handlusernew.models.SideMenuModel


class MainActivity : AppCompatActivity(), DrawerStateInterface {

    private lateinit var binding: ActivityMainBinding
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var openMenuListener: OpenThePopUpMenu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            binding.drawerLayout, binding.toolBar,
            0,
            0
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        binding.rlDropDown.setOnClickListener {
            openMenuListener?.openMenu(it)
        }
        setNavData()
    }

    //Set side menu navigation data
    private fun setNavData() {
        val arraySideMenu = ArrayList<SideMenuModel>()
        arraySideMenu.clear()
        arraySideMenu.add(SideMenuModel("My Details", R.drawable.detailed_icon))
        arraySideMenu.add(SideMenuModel("Messages", R.drawable.message_icon))
        arraySideMenu.add(SideMenuModel("Appointments", R.drawable.appointment_icon))
        arraySideMenu.add(SideMenuModel("My Calender", R.drawable.calender_icon))
        arraySideMenu.add(SideMenuModel("Help", R.drawable.help_icon))
        arraySideMenu.add(SideMenuModel("Promos", R.drawable.promos_icon))
        arraySideMenu.add(SideMenuModel("Legal", R.drawable.legal_icon))
        arraySideMenu.add(SideMenuModel("Logout", R.drawable.logout_icon))
        binding.sideMenuNav.sideRecycler.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = SideMenuAdapter(this, arraySideMenu) { position: Int ->
            //redirection from position
        }
        binding.sideMenuNav.sideRecycler.adapter = adapter
    }

    //interface call back from fragments to open or close the side menu
    override fun openDrawer(direction: Int) {
        if (binding.drawerLayout.isDrawerOpen(direction)) {
            closeDrawer(direction)
        } else {
            binding.drawerLayout.openDrawer(direction, true)
        }
    }

    //interface call back from fragments to open or close the side menu
    override fun closeDrawer(direction: Int) {
        binding.drawerLayout.closeDrawer(direction, true)
    }


}


