package com.example.handlusernew

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {



    @Preview
    @Composable
    fun ComposablePreview(){
        //MainContent()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          installSplashScreen()


        setTheme(R.style.Theme_HandlUserNew)
       //

        setContent {
         //   SimpleComposable(this)

            // Hide the status bar.


            setContentView(R.layout.activity_main)

//
//            MaterialAlertDialogBuilder(this)
//                .setTitle(resources.getString(R.string.booking))
//                .setMessage(resources.getString(R.string.email_id))
//                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
//                    // Respond to neutral button press
//                    setContentView(R.layout.activity_main)
//
//                }
//                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
//                    // Respond to negative button press
//                    setContentView(R.layout.activity_main)
//
//                }
//                .setPositiveButton(resources.getString(R.string.enter_emial_id)) { dialog, which ->
//
//                    setContentView(R.layout.activity_main)
//
//                    // Respond to positive button press
//                }
//                .show()

        }


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


