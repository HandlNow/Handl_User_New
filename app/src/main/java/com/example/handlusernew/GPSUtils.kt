package com.example.handlusernew

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.location.LocationManager
import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception
import com.google.android.gms.location.LocationSettingsStatusCodes

import android.content.IntentSender
import android.content.IntentSender.SendIntentException
import com.google.android.gms.common.api.Status

import com.google.android.gms.location.LocationSettingsStates

import com.google.android.gms.location.LocationSettingsResult
import com.google.android.gms.tasks.Task
import android.content.Intent
import com.google.android.gms.common.api.PendingResult


class GPSUtils(context: Context) {

    private val TAG = "GPS"
    private val mContext: Context = context


     var mSettingClient: SettingsClient? = null
     var mLocationSettingsRequest: LocationSettingsRequest? = null

     var mLocationManager: LocationManager? = null
     var mLocationRequest: LocationRequest? = null

     var task:Task<LocationSettingsResponse>?=null

    var result: PendingResult<LocationSettingsResult>? = null


    init {
        mLocationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as? LocationManager

        mSettingClient = LocationServices.getSettingsClient(mContext)

        mLocationRequest = LocationRequest.create()
        mLocationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        mLocationRequest?.interval = 1000
        mLocationRequest?.fastestInterval = 500


        if (mLocationRequest != null) {
            val builder: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
            builder.addLocationRequest(mLocationRequest!!)
            mLocationSettingsRequest = builder.build()



        }
    }

    private var GPSCODE = 123

    /**
     this function check if the GPS it turned off and turns it on
     */
    fun turnOnGPS() {
        if (mLocationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
            mLocationSettingsRequest?.let {
               task = mSettingClient?.checkLocationSettings(it)
                    ?.addOnSuccessListener(mContext as Activity) {
                        Log.d(TAG, "turnOnGPS: Already Enabled")


                    }
                    ?.addOnFailureListener { ex ->
                        if ((ex as ApiException).statusCode
                            == LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                        ) {
                            try {
                                val resolvableApiException = ex as ResolvableApiException
                                resolvableApiException.startResolutionForResult(
                                    mContext,
                                    GPSCODE
                                )

                            } catch (e: Exception) {
                                Log.d(TAG, "turnOnGPS: Unable to start default functionality of GPS")
                            }

                        } else {
                            if (ex.statusCode == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE) {
                                val errorMessage =
                                    "Location settings are inadequate, and cannot be " +
                                            "fixed here. Fix in Settings."
                                Log.e(TAG, errorMessage)
                                Toast.makeText(
                                    mContext,
                                    errorMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }


            }
        }
    }







}