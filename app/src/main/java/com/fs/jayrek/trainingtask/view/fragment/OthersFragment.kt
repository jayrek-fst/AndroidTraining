package com.fs.jayrek.trainingtask.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel

class OthersFragment : Fragment() {

    private var uid: String = ""

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_others, container, false)
        val tvInfo = view.findViewById<TextView>(R.id.tvInfo)

        val viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        viewModel.getUser()
        viewModel.snapShot.observe(viewLifecycleOwner){
            val fName: String = it.get("firstName").toString()
            val lName: String = it.get("lastName").toString()
            tvInfo.text = "$fName $lName"
        }
        return view
    }
}