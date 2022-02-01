package com.dev.handlusernew.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.handlusernew.R
import com.dev.handlusernew.activities.MainActivity
import com.dev.handlusernew.adapter.MainScreenFragmentAdapter
import com.dev.handlusernew.databinding.FragmentMainScreenBinding
import com.dev.handlusernew.interfaces.DrawerStateInterface
import com.dev.handlusernew.interfaces.OpenThePopUpMenu
import com.dev.handlusernew.models.CategoryItemModel
import com.dev.handlusernew.network.NetworkClass
import com.dev.handlusernew.network.Response
import com.dev.handlusernew.network.URLApi
import com.dev.handlusernew.utils.generateList


class MainListingFragment : Fragment() {
    private var mCategoryListModel: ArrayList<CategoryItemModel> = ArrayList()
    private var mCategoryListModelBackup: ArrayList<CategoryItemModel> = ArrayList()
    private lateinit var mBinding: FragmentMainScreenBinding
    private var mAdapter: MainScreenFragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        //For getting the arguments
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =
            FragmentMainScreenBinding.inflate(inflater)
        mBinding = view
        // Inflate the layout for this fragment
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        mBinding.swipe.setOnRefreshListener {
            getCategories()
        }
        getCategories()
        mBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                filterSearch(p0?.toString()?.trim()?.lowercase() ?: "")
            }

        })
        if (requireActivity() is MainActivity) {
            //Drop down for addition of address
            (requireActivity() as MainActivity).openMenuListener = object : OpenThePopUpMenu {
                override fun openMenu(view: View) {
                    val popup = PopupMenu(requireActivity(), view)
//                    popup.menuInflater.inflate(R.menu.location_selection, popup.menu)
                    popup.menuInflater.inflate(R.menu.location_selection, popup.menu)

                    popup.setOnMenuItemClickListener { item ->
                        if (item?.itemId == R.id.ivAddNewAddress) {
                            Navigation.findNavController(mBinding.root)
                                .navigate(R.id.action_mainScreenFragment_to_locationPickFragment)
                        }
                        true
                    }
                    popup.show() //showing popup menu

                }

                override fun closeMenu(view: View) {

                }

            }
        }
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
                (requireActivity() as? DrawerStateInterface)?.openDrawer()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //get Category data
    private fun getCategories() {

        if (!mBinding.swipe.isRefreshing) {
            mBinding.swipe.isRefreshing = true
        }
        NetworkClass.callApi(URLApi.getCategory(), object : Response {
            override fun onSuccessResponse(response: String?, message: String) {
                mBinding.swipe.isRefreshing = false
                val array = generateList(
                    response = response.toString(),
                    Array<CategoryItemModel>::class.java
                )
                mCategoryListModelBackup.clear()
                mCategoryListModelBackup.addAll(array)
                mAdapter?.notifyItemRangeRemoved(0, mAdapter?.itemCount ?: 0)
                mCategoryListModel.clear()
                mCategoryListModel.addAll(array)
                mAdapter?.notifyItemRangeInserted(0, mCategoryListModel.count())
            }

            override fun onErrorResponse(error: String?, response: String?) {
                mBinding.swipe.isRefreshing = false
                Toast.makeText(
                    requireContext(),
                    error ?: getString(R.string.network_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    //set Category Recycler
    private fun setRecycler() {
        mBinding.dataRecycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        mAdapter = MainScreenFragmentAdapter(requireContext(), mCategoryListModel)
        mBinding.dataRecycler.adapter = mAdapter

    }

    //for filter data local search
    private fun filterSearch(text: String) {
        if (text.isEmpty()) {
            mAdapter?.notifyItemRangeRemoved(0, mAdapter?.itemCount ?: 0)
            mCategoryListModel.clear()
            mCategoryListModel.addAll(mCategoryListModelBackup)
            mAdapter?.notifyItemRangeInserted(0, mCategoryListModel.count())
        } else {
            mAdapter?.notifyItemRangeRemoved(0, mAdapter?.itemCount ?: 0)
            mCategoryListModel.clear()
            mCategoryListModel.addAll(mCategoryListModelBackup.filter { category ->
                category.nameTitle.lowercase().contains(text) || (category.subcategory?.any {
                    it.title?.lowercase()?.contains(text) == true
                } == true)
            })
            mAdapter?.notifyItemRangeInserted(0, mCategoryListModel.count())
        }
    }

}