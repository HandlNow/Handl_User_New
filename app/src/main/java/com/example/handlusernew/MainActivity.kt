package com.example.handlusernew

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import com.example.handlusernew.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity(), DrawerStateInterface {

    lateinit var binding: ActivityMainBinding

    @Preview
    @Composable
    fun ComposablePreview() {
        //MainContent()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.drawerLayout?.openDrawer()
//        setTheme(R.style.Theme_HandlUserNew)
        //
        setContent {
            //   SimpleComposable(this)

            // Hide the status bar.


            MaterialAlertDialogBuilder(this)
                .setTitle(resources.getString(R.string.booking))
                .setMessage(resources.getString(R.string.email_id))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to neutral button press
                    setContentView(binding.root)
                        
//                    this@MainActivity.supportActionBar?.setDr

                }
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                    // Respond to negative button press
                    setContentView(binding.root)


                }
                .setPositiveButton(resources.getString(R.string.enter_emial_id)) { dialog, which ->

                    setContentView(binding.root)


                    // Respond to positive button press
                }
                .show()

        }


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

