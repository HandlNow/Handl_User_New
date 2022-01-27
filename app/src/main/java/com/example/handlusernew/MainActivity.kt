package com.example.handlusernew


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.handlusernew.adapter.SideMenuAdapter
import com.example.handlusernew.adapter.SideMenuModel
import com.example.handlusernew.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), DrawerStateInterface {

    lateinit var binding: ActivityMainBinding
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var openMenuListener:OpenThePopUpMenu? = null
    @Preview
    @Composable
    fun ComposablePreview() {
        //MainContent()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.drawerLayout?.openDrawer()
//        setTheme(R.style.Theme_HandlUserNew)
        //
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        //to toggle actionbar
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
//        setContent {
//            //   SimpleComposable(this)
//
//            // Hide the status bar.
//
//
//            MaterialAlertDialogBuilder(this)
//                .setTitle(resources.getString(R.string.booking))
//                .setMessage(resources.getString(R.string.email_id))
//                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
//                    // Respond to neutral button press
//
////                    this@MainActivity.supportActionBar?.setDr
//
//                }
//                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
//                    // Respond to negative button press
//                    setContentView(binding.root)
//                    setSupportActionBar(binding.toolBar)
//                    actionBarDrawerToggle = ActionBarDrawerToggle(
//                        this@MainActivity,
//                        binding.drawerLayout,binding.toolBar,
//                        R.string.app_name,
//                        R.string.app_name
//                    )
//                    binding.drawerLayout.setDrawerListener(actionBarDrawerToggle)
//
//                }
//                .setPositiveButton(resources.getString(R.string.enter_emial_id)) { dialog, which ->
//
//                    setContentView(binding.root)
//                    setSupportActionBar(binding.toolBar)
//                    actionBarDrawerToggle = ActionBarDrawerToggle(
//                        this@MainActivity,
//                        binding.drawerLayout,binding.toolBar,
//                        R.string.app_name,
//                        R.string.app_name
//                    )
//                    binding.drawerLayout.setDrawerListener(actionBarDrawerToggle)
//
//                    // Respond to positive button press
//                }
//                .show()
//
//        }


    }

    //Set side menu navigation data
    private fun setNavData() {
        val arraySideMenu = ArrayList<SideMenuModel>()
        arraySideMenu.clear()
        arraySideMenu.add(SideMenuModel("My Details" , R.drawable.detailed_icon))
        arraySideMenu.add(SideMenuModel("Messages" , R.drawable.message_icon))
        arraySideMenu.add(SideMenuModel("Appointments" , R.drawable.appointment_icon))
        arraySideMenu.add(SideMenuModel("My Calender" , R.drawable.calender_icon))
        arraySideMenu.add(SideMenuModel("Help" , R.drawable.help_icon))
        arraySideMenu.add(SideMenuModel("Promos" , R.drawable.promos_icon))
        arraySideMenu.add(SideMenuModel("Legal" , R.drawable.legal_icon))
        arraySideMenu.add(SideMenuModel("Logout" , R.drawable.logout_icon))
        binding.sideMenuNav.sideRecycler.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)
        val adapter = SideMenuAdapter(this , arraySideMenu){
        }
        binding.sideMenuNav.sideRecycler.adapter = adapter
    }

    override fun openDrawer(direction: Int) {
        if (binding.drawerLayout.isDrawerOpen(direction)) {
            closeDrawer(direction)
        } else {
            binding.drawerLayout.openDrawer(direction, true)
        }
    }

    override fun closeDrawer(direction: Int) {
        binding.drawerLayout.closeDrawer(direction, true)
    }

}

@Composable
fun SimpleComposable(context: Context) {
    Text("Hello World")
    Scaffold(
        drawerContent = {
            Text("Drawer title", modifier = Modifier.padding(16.dp))
            Divider()
            // Drawer items
        }
    ) {
        // Screen content
    }
}

interface DrawerStateInterface {
    //@EdgeGravity
//    @IntDef(
//        value = [Gravity.LEFT, Gravity.RIGHT, GravityCompat.START, GravityCompat.END, Gravity.NO_GRAVITY],
//        flag = true
//    )
    fun openDrawer(direction: Int = GravityCompat.END)
    fun closeDrawer(direction: Int = GravityCompat.START)

}

interface OpenThePopUpMenu {
    fun openMenu(view: View)
    fun closeMenu(view: View)
}

