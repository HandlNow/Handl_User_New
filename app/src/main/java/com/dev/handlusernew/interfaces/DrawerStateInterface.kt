package com.dev.handlusernew.interfaces

import androidx.core.view.GravityCompat

interface DrawerStateInterface {
    //@EdgeGravity
//    @IntDef(
//        value = [Gravity.LEFT, Gravity.RIGHT, GravityCompat.START, GravityCompat.END, Gravity.NO_GRAVITY],
//        flag = true
//    )
    fun openDrawer(direction: Int = GravityCompat.END)
    fun closeDrawer(direction: Int = GravityCompat.START)

}