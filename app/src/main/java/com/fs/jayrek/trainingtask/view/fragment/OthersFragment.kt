package com.fs.jayrek.trainingtask.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.databinding.FragmentOthersBinding
import com.fs.jayrek.trainingtask.view.activity.AuthActivity
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel

class OthersFragment : Fragment() {

    private lateinit var binding: FragmentOthersBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_others, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    @SuppressLint("SetTextI18n")
    private fun observeData(){
        viewModel.getUser()
        viewModel.snapShot.observe(viewLifecycleOwner){
            val fName: String = it.get("firstName").toString()
            val lName: String = it.get("lastName").toString()
            binding.tvInfo.text = "$fName $lName"
        }

        binding.tvLogOut.setOnClickListener{
            viewModel.logOut()
        }

        viewModel.isLogout.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(requireActivity(), "You have logged out...", Toast.LENGTH_SHORT).show()
                startActivity(Intent(Intent(requireActivity(), AuthActivity::class.java)))
                requireActivity().finish()
            }
        }

        viewModel.errorMsg.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}