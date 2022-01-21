package com.example.handlusernew

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.OnMapReadyCallback

import com.google.android.gms.maps.GoogleMap

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Transformations.map
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.handlusernew.databinding.FragmentBlankBinding
import com.example.handlusernew.databinding.FragmentSecondBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import java.security.AccessController.getContext
import java.util.*





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment(),OnMapReadyCallback {
    private var param1: String? = null
    private var param2: String? = null

    private var fusedLocationClient: FusedLocationProviderClient?=null

    private var myLocation:Location?=null

    private var googleMap:GoogleMap?=null

    private var geocoder:Geocoder?=null

    val REQUEST_LOCATION = 199

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }



    private var _binding: FragmentSecondBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.example.handlusernew.R.layout.fragment_second, container, false)


        myLocation = Location("maps")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())


        requestUserLocationForMainScreen()

//
        val mapFragment = childFragmentManager.findFragmentById(com.example.handlusernew.R.id.frg) as SupportMapFragment

        mapFragment.getMapAsync(this)


        _binding = FragmentSecondBinding.bind(view)



        geocoder = Geocoder(context, Locale.ENGLISH)



        _binding?.shareTV?.setOnClickListener {
            Log.d("hello","world !!")
           Navigation.findNavController(view).navigate(com.example.handlusernew.R.id.action_secondFragment_to_loginFragment)
        }





        // initialize map fragment



        // getLastLocation()


//        _binding = FragmentSecondBinding.inflate(inflater, container, false)
//
//        val view = binding.root




        return view

        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun isLocationEnabled(): Boolean {
        var locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

//    fun createLocationRequest() {
//        val locationRequest = LocationRequest.create()?.apply {
//            interval = 10000
//            fastestInterval = 5000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }
//    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap) {

        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireContext())


       // val mypos = LatLng(myLocation!!.latitude, myLocation!!.longitude)

      //  val Sydney = LatLng(-122.412,37.7827)
//
//
        googleMap=p0
//
        googleMap?.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireActivity().applicationContext, com.example.handlusernew.R.raw.styled))

        if (isLocationEnabled()){
            getUserLocation()

        }
        else{
            GPSUtils(requireActivity()).turnOnGPS()
            getUserLocation()

        }
//
//        googleMap?.addMarker(
//            MarkerOptions()
//                .position(Sydney)
//                .title("Your Location")
//        )
//
//        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
//
//
//        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(Sydney, 12f))
//
//
       //Log.d("place", myLocation?.longitude.toString())


       // GetUserLocation()

     //   var location = fusedLocationClient.requestLocationUpdates()

//        val mypos = LatLng(0.0,0.0)
//        p0.addMarker(
//            MarkerOptions()
//                .position(mypos)
//                .title("Your Location")
//
//        )
//        p0.moveCamera(CameraUpdateFactory.newLatLngZoom(mypos,10f))

        Log.d("TAG,","map added")




//
//        p0.setOnMapClickListener {
//            getLastLocation()
//        }


    }


// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.

//        val locationPermissionRequest = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) { permissions ->
//            when {
//                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
//                    // Precise location access granted.
//                    Log.d("permission","permission granted")
//                }
//                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
//                    // Only approximate location access granted.
//                } else -> {
//                // No location access granted.
//                Log.d("permission","permission not granted")
//
//            }
//            }
//        }

// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.
//        locationPermissionRequest.launch(arrayOf(
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION))
//
    val REQUESTCODE = 123

    @SuppressLint("MissingPermission")
    fun requestUserLocationForMainScreen() {

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

        //getLastLocation()


        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
//        Log.d("requesting", "permissions")
//        requestPermissionLauncher
//


//        Log.d("requesting", "GOT PERMISSIONS")
//        //GetUserLocation()
//


    }

//    @SuppressLint("MissingPermission")
//    fun getLastLocation(){
//        if (checkPermissions()) {
//            fusedLocationClient.lastLocation
//                .addOnSuccessListener { location: Location? ->
//                    // Got last known location. In some rare situations this can be null.
//                    myLocation = location
//                }
//        }
//        else
//        {
//           //requestPermissionLauncher
//            requestPermissions()
//        }
//    }


    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            REQUESTCODE
        )
    }

//    @SuppressLint("MissingPermission")
//    private fun getLastLocation() {
//        if (checkPermissions()) {
//            if (isLocationEnabled()) {
//
//                fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
//                    var location: Location? = task.result
//                    if (location == null) {
//                        requestNewLocationData()
//                    } else {
//                        findViewById<TextView>(R.id.latTextView).text = location.latitude.toString()
//                        findViewById<TextView>(R.id.lonTextView).text = location.longitude.toString()
//                    }
//                }
//            } else {
//                Toast.makeText(requireActivity(), "Turn on location", Toast.LENGTH_LONG).show()
//                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(intent)
//            }
//        } else {
//            requestPermissions()
//        }
//    }


//    when {
//        ContextCompat.checkSelfPermission(
//            requireActivity(),
//            Manifest.permission.ACCESS_COARSE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED -> {
//            // You can use the API that requires the permission.
//            performAction(...)
//        }
//        shouldShowRequestPermissionRationale(...) -> {
//        // In an educational UI, explain to the user why your app requires this
//        // permission for a specific feature to behave as expected. In this UI,
//        // include a "cancel" or "no thanks" button that allows the user to
//        // continue using your app without granting the permission.
//        showInContextUI(...)
//    }
//        else -> {
//            // You can directly ask for the permission.
//            requestPermissions(requireActivity(),
//                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
//                REQUESTCODE)
//        }
//    }


    @SuppressLint("MissingPermission")
//    private fun GetUserLocation() {
////        var myLocation = MyLocationListener()
////        var locationManager =
////            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
////        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,0.5f,myLocation)
//
//        val mythread = myThread()
//        mythread.start()
//
//        mythread.stop()
//    }

//
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)


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

    //var location:Location?=null


//    inner class MyLocationListener:LocationListener{
//        constructor(){
//            myLocation = Location("Start")
//            myLocation!!.longitude = 0.0
//            myLocation!!.latitude = 0.0
//        }
//
//        override fun onLocationChanged(location: Location) {
//            myLocation = location
//        }
//
//    }


//    inner class myThread: Thread() {
//        @SuppressLint("MissingPermission")
//        override fun run() {
//            Log.d("thread","started")
//            super.run()
//            var locationFound = false
//
//            while (!locationFound){
//                Log.d("thread","started2")
//
//                try {
//                    Log.d("thread","started3")
//
//                    var cancellationToken = CancellationTokenSource()
//
//
////                    fusedLocationClient?.getCurrentLocation(234,cancellationToken.token)?.addOnSuccessListener { location: Location? ->
////                            myLocation = location
////                            Log.d("thread","started5")
////                            Log.d("country", myLocation.toString())
////
////                        }
//
//                    val currentLocationTask: Task<Location> = fusedLocationClient!!.getCurrentLocation(
//                        PRIORITY_HIGH_ACCURACY,
//                        cancellationToken.token
//                    )
//
//                    currentLocationTask.addOnCompleteListener { task: Task<Location> ->
//                        val result = if (task.isSuccessful) {
//                            val result: Location = task.result
//                            "Location (success): ${result.latitude}, ${result.longitude}"
//                            myLocation = result
//                        } else {
//                            val exception = task.exception
//                          //  "Location (failure): $exception"
//                        }
//
//                        Log.d("hello", "getCurrentLocation() result: $result")
//                    }
//
//
//
//
//
//                    activity?.runOnUiThread{
//
//                                if(googleMap!=null) {
//                                    Log.d("thread","started4")
//
//                                    Log.d("country", myLocation!!.latitude.toString())
//
//                                    val lc = LatLng(myLocation!!.latitude,myLocation!!.longitude)
//                                    googleMap?.clear()
//
//                                    googleMap?.addMarker(
//                                        MarkerOptions()
//                                            .position(lc)
//                                            .title("Your Location")
//
//                                    )
//                                    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(lc, 5f))
////
//                                    Log.d("country", myLocation!!.latitude.toString())
////
//                                    var adressList = geocoder?.getFromLocation(myLocation!!.latitude,myLocation!!.longitude,1)
//
//
//                                    if (adressList != null && adressList.count() > 0) {
//                                        val locality: String = adressList.get(0).getLocality()
//                                        var country :String = adressList.get(0).countryName
//
//                                         binding.cityET.setText(locality)
//                                        binding.codeET.setText(country)
//                                        locationFound=true
//
//                                        Log.d("country", locality)
//
//
//                                    }
//
//
//
//                                }
//
//                    }
//
//                    sleep(300)
//                }
//                catch (e:Exception){
//
//                }
//            }
//        }
//
//    }

    @SuppressLint("MissingPermission")
    fun getUserLocation(){

        if (checkPermissions()) {
            var cancellationToken = CancellationTokenSource()


            val currentLocationTask: Task<Location> = fusedLocationClient!!.getCurrentLocation(
                PRIORITY_HIGH_ACCURACY,
                cancellationToken.token
            )

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


                    if (adressList != null && adressList.count() > 0) {
                        val locality: String = adressList.get(0).getLocality()
                        val country: String = adressList.get(0).countryName
                        val city: String = adressList.get(0).adminArea

                        binding.cityET.setText("$locality,$city")
                        binding.codeET.setText(country)


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

//    override fun onRequestPermissionsResult(requestCode: Int,
//                                            permissions: Array<String>, grantResults: IntArray) {
//        when (requestCode) {
//            REQUESTCODE -> {
//                // If request is cancelled, the result arrays are empty.
//                if ((grantResults.isNotEmpty() &&
//                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//
//                                getUserLocation()
//                    // Permission is granted. Continue the action or workflow
//                    // in your app.
//
//                        Log.d("permissions","GRANTED")
//
//                   // getLastLocation()
//
//                } else {
//                    // Explain to the user that the feature is unavailable because
//                    // the features requires a permission that the user has denied.
//                    // At the same time, respect the user's decision. Don't link to
//                    // system settings in an effort to convince the user to change
//                    // their decision.
//                }
//                return
//            }
//
//            // Add other 'when' lines to check for other
//            // permissions this app might request.
//            else -> {
//                // Ignore all other requests.
//            }
//        }
//    }
}