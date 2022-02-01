package com.dev.handlusernew.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dev.handlusernew.R
import com.dev.handlusernew.databinding.FragmentUserLoginBinding


class UserLoginFragment : Fragment() {


    private var _binding: FragmentUserLoginBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_login, container, false)

        //  _binding = FragmentBlankBinding.inflate(inflater,container,false)
        _binding = FragmentUserLoginBinding.bind(view)


        _binding?.loginTV?.setOnClickListener {
            Log.d("hello", "world !!")
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_mainScreenFragment)
        }

        // Inflate the layout for this fragment
        return view
    }


}