package com.example.handlusernew

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.handlusernew.databinding.FragmentTestBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
This fragment contains commented out code from other scripts.
 These could be used later on
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding:FragmentTestBinding?=null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }


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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_test, container, false)

      //  _binding = FragmentBlankBinding.inflate(inflater,container,false)
        _binding = FragmentTestBinding.bind(view)
//

      //  val view = _binding?.root


//        _binding?.button?.setOnClickListener {
//            Log.d("hello","world !!")
//            Navigation.findNavController(view).navigate(R.id.navigateToSecond)
//        }



        // Inflate the layout for this fragment
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

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

//    @Composable
//    fun SimpleComposable(context: Context) {
//        Text("Hello World")
//
//
//        Scaffold(
//            drawerContent = {
//                Text("Drawer title", modifier = Modifier.padding(16.dp))
//                Divider()
//                // Drawer items
//            }
//        ) {
//            // Screen content
//        }
//    }

    //    fun createLocationRequest() {
//        val locationRequest = LocationRequest.create()?.apply {
//            interval = 10000
//            fastestInterval = 5000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }
//    }

    //        googleMap?.addMarker(
//            MarkerOptions()
//                .position(Sydney)
//                .title("Your Location")
//        )
//
//        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL

    //        googleMap?.setMapStyle(
//            MapStyleOptions.loadRawResourceStyle(
//                requireActivity().applicationContext, com.example.handlusernew.R.raw.styled))

    //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireContext())


    // val mypos = LatLng(myLocation!!.latitude, myLocation!!.longitude)

    //  val Sydney = LatLng(-122.412,37.7827)
//
//

//    @Preview
//    @Composable
//    fun ComposablePreview(){
//        //MainContent()
//    }


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

//
//        p0.setOnMapClickListener {
//            getLastLocation()
//        }

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

    //    private fun requestPermissions() {
//        ActivityCompat.requestPermissions(
//            requireActivity(),
//            arrayOf(
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ),
//            REQUESTCODE
//        )
//    }

}