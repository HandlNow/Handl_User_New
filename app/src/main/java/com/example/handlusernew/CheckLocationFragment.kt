package com.example.handlusernew

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.handlusernew.databinding.FragmentChecklocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import java.util.*
import com.google.android.gms.location.LocationSettingsStatusCodes

import android.content.IntentSender
import android.content.IntentSender.SendIntentException
import androidx.compose.foundation.lazy.rememberLazyListState
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status

import com.google.android.gms.location.LocationSettingsResult





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckLocationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckLocationFragment : Fragment(), OnMapReadyCallback {


    private val REQUESTCODE = 123

    private var _binding: FragmentChecklocationBinding? = null

    private val binding get() = _binding!!

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private var myLocation: Location? = null

    private var googleMap: GoogleMap? = null

    private var geocoder: Geocoder? = null

    private var gpsUtils:GPSUtils?=null


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CheckLocationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CheckLocationFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_checklocation, container, false)


        myLocation = Location("maps")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        geocoder = Geocoder(context, Locale.ENGLISH)

        _binding = FragmentChecklocationBinding.bind(view)


        _binding!!.shareTV.isEnabled = false

        checkIfLocationPermissionsAlreadyGranted()

        initGoogleMap()


        _binding?.shareTV?.setOnClickListener {
            Log.d("hello", "world !!")
            Navigation.findNavController(view)
                .navigate(com.example.handlusernew.R.id.action_secondFragment_to_loginFragment)
        }

        return view

    }

    private fun initGoogleMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.frg) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    private fun isLocationEnabled(): Boolean {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }



    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap) {

        googleMap = p0 // assign to global google map object when the p0 is ready

        googleMap!!.setOnCameraMoveListener {
            tryGetLocation()
        }

        tryGetLocation()


    }

    fun tryGetLocation(){
        /**
        check if the location is enabled. If it is enabled it gets user location
        otherwise turns on GPS and then getUserLocation
         */
        if (!isLocationEnabled()) {
            gpsUtils = GPSUtils(requireActivity())
            gpsUtils?.turnOnGPS()
            getUserLocation()

        } else {
            Log.d("location","location GPS check")
            getUserLocation()

        }

    }


    @SuppressLint("MissingPermission")
    fun checkIfLocationPermissionsAlreadyGranted() {

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Log.d("location", "FINE granted")
                    getUserLocation()


                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                    getUserLocation()
                    Log.d("location", "COARSE granted")
                }
                else -> {

                    Log.d("location", "NOT granted")
                    // No location access granted.
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )


    }





    @SuppressLint("MissingPermission")
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true

    }

  //  var result: PendingResult<LocationSettingsResult>? = null





    @SuppressLint("MissingPermission")
    fun getUserLocation() {

        Log.d("check",checkPermissions().toString())
        Log.d("check",isLocationEnabled().toString())

        if (checkPermissions() && isLocationEnabled()) {
            val cancellationToken = CancellationTokenSource()

            // get current location from fusedLocationClient
            val currentLocationTask: Task<Location> = fusedLocationClient!!.getCurrentLocation(
                PRIORITY_HIGH_ACCURACY,
                cancellationToken.token
            )

            // once location is obtained, fill in the text boxes in the screen
            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                if (task.isSuccessful) {
                    val result: Location = task.result
                    "Location (success): ${result.latitude}, ${result.longitude}"
                    myLocation = result


                    val lc = LatLng(myLocation!!.latitude, myLocation!!.longitude)
                    googleMap?.clear()

                    googleMap?.addMarker(
                        MarkerOptions()
                            .position(lc)
                            .title("Your Location")

                    )
                    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(lc, 5f))
//
                    Log.d("country", myLocation!!.latitude.toString())
//
                    val adressList =
                        geocoder?.getFromLocation(myLocation!!.latitude, myLocation!!.longitude, 1)


                    // display the current city and country
                    if (adressList != null && adressList.count() > 0) {
                        val locality: String = adressList.get(0).getLocality()
                        val country: String = adressList.get(0).countryName
                        val city: String = adressList.get(0).adminArea

                        binding.cityET.setText("$locality,$city")
                        binding.codeET.setText(country)
                        _binding!!.shareTV.isEnabled = true


                    } else {


                    }

                } else {
                    val exception = task.exception
                    //"Location (failure): $exception"
                }

//            Log.d("hello", "getCurrentLocation() result: $result")
            }
        }
    }



}