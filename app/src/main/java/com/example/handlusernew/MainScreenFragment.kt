package com.example.handlusernew

import android.os.Bundle
import android.view.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.handlusernew.adapter.ItemDetailModel
import com.example.handlusernew.adapter.MainScreenFragmentAdapter
import com.example.handlusernew.adapter.MainScreenModel
import com.example.handlusernew.databinding.FragmentMainScreenBinding
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMainScreenBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)

        _binding = FragmentMainScreenBinding.bind(view)

        setRecycler()
        // _binding

//        topAppBar.setNavigationOnClickListener {
//            drawerLayout.open()
//        }
//
//        navigationView.setNavigationItemSelectedListener { menuItem ->
//            // Handle menu item selected
//            menuItem.isChecked = true
//            drawerLayout.close()
//            true
//        }

        // Inflate the layout for this fragment
        return view

//        ComposeView(requireContext()).apply {
//            setContent {
//                MainContentDisplay()
//            }
//        }

        /// return view
    }

    private fun setRecycler() {
        val array : ArrayList<ItemDetailModel> = ArrayList()
        array.add(ItemDetailModel("Hey Babe" , R.drawable.bg_gradient))
        array.add(ItemDetailModel("Hey Babe" , R.drawable.bg_gradient))
        array.add(ItemDetailModel("Hey Babe" , R.drawable.bg_gradient))
        array.add(ItemDetailModel("Hey Babe" , R.drawable.bg_gradient))
        array.add(ItemDetailModel("Hey Babe" , R.drawable.bg_gradient))
        array.add(ItemDetailModel("Hey Babe" , R.drawable.bg_gradient))
        val arrayFinal : ArrayList<MainScreenModel> = ArrayList()
        arrayFinal.clear()
        arrayFinal.add(MainScreenModel("Kids" , array))
        arrayFinal.add(MainScreenModel("Boys" , array))
        arrayFinal.add(MainScreenModel("Girls" , array))
        arrayFinal.add(MainScreenModel("Gays" , array))
        arrayFinal.add(MainScreenModel("Afrahs" , array))
        _binding?.dataRecycler?.layoutManager = LinearLayoutManager(requireContext())
        val adapter  = MainScreenFragmentAdapter(requireContext() , arrayFinal)
        _binding?.dataRecycler?.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_app_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.notiIV -> {

            }
            R.id.menuIV -> {

//                GlobalScope.launch(MonotonicFrameClock()) {
//                    scaffoldState?.drawerState?.open()
//                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private var scaffoldState: ScaffoldState? = null

    @Composable
    fun MainContentDisplay() {
        val result = remember { mutableStateOf("") }
        val selectedItem = remember { mutableStateOf("favorite") }
        val fabShape = RoundedCornerShape(50)
        scaffoldState = rememberScaffoldState(
            rememberDrawerState(DrawerValue.Closed)
        )
        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState!!,

            drawerContent = {
                Column(
                    modifier = Modifier
                        // .background(Color(0xFFFFFAF0))
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "APP Title",
                            // fontSize = 30.sp,
                            // fontFamily = FontFamily.Cursive,
                            textAlign = TextAlign.Center
                        )
                    }

                    Column(
                        modifier = Modifier
                    ) {
                        Row(modifier = Modifier
                            .clickable {
                                scope.launch {
                                    scaffoldState?.drawerState?.close()
                                    result.value = "Refresh clicked"
                                }
                            }
                            .fillMaxWidth()
                            .padding(8.dp)
                            .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Filled.Refresh, contentDescription = "")
                            Text(
                                text = "Refresh",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(modifier = Modifier
                            .clickable {
                                scope.launch {
                                    scaffoldState?.drawerState?.close()
                                    result.value = "Cloud upload clicked"
                                }
                            }
                            .fillMaxWidth()
                            .padding(8.dp)
                            .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Cloud Upload",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Divider()
                        Row(modifier = Modifier
                            .clickable {
                                scope.launch {
                                    scaffoldState?.drawerState?.close()
                                    result.value = "Search clicked"
                                }
                            }
                            .fillMaxWidth()
                            .padding(8.dp)
                            .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Filled.Search, contentDescription = "")
                            Text(
                                text = "Search",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            },

            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Handl")
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {
                                scope.launch {
                                    scaffoldState?.drawerState?.open()
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "")
                        }
                    },

                    //   backgroundColor = Color(0xFF5FA777),
                    elevation = AppBarDefaults.TopAppBarElevation
                )
            },

            content = {
                Box(
                    Modifier
                        //.background(Color(0XFFE3DAC9))
                        .padding(16.dp)
                        .fillMaxSize(),
                ) {
                    Text(
                        text = result.value,
                        fontSize = 22.sp,
                        //  fontFamily = FontFamily.Serif,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },

//            floatingActionButton = {
//                FloatingActionButton(
//                    onClick = {result.value = "FAB clicked"},
//                    shape = fabShape,
//                    backgroundColor = Color(0xFFFF3800)
//                ) {
//                    Icon(Icons.Filled.Add,"")
//                }
//            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,

//            bottomBar = {
//                BottomAppBar(
//                    cutoutShape = fabShape,
//                    content = {
//                        BottomNavigation() {
//                            BottomNavigationItem(
//                                icon = {
//                                    Icon(Icons.Filled.Favorite , "")
//                                },
//                                label = { Text(text = "Favorite")},
//                                selected = selectedItem.value == "favorite",
//                                onClick = {
//                                    result.value = "Favorite icon clicked"
//                                    selectedItem.value = "favorite"
//                                },
//                                alwaysShowLabel = false
//                            )
//
//                            BottomNavigationItem(
//                             icon = {R.drawable.avd_delete},
//                                label = { Text(text = "Upload")},
//                                selected = selectedItem.value == "upload",
//                                onClick = {
//                                    result.value = "Upload icon clicked"
//                                    selectedItem.value = "upload"
//                                },
//                                alwaysShowLabel = false
//                            )
//                        }
//                    }
//                )
//            }
        )
    }

}