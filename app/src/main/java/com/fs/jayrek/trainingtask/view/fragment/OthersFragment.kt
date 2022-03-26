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
import com.fs.jayrek.trainingtask.helper.Resource
import com.fs.jayrek.trainingtask.helper.StringConstants
import com.fs.jayrek.trainingtask.model.model.User
import com.fs.jayrek.trainingtask.view.activity.AuthActivity
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel

class OthersFragment : Fragment() {

    private lateinit var binding: FragmentOthersBinding

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
    private fun observeData() {
        val viewModel: AuthViewModel by viewModels()

        viewModel.getUser()
        viewModel.snapShot.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val model = it.data?.toObject(User::class.java)
                    binding.tvInfo.text = "${model!!.firstName} ${model.lastName}"
                }
                is Resource.Error -> Toast.makeText(
                    requireActivity(),
                    it.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvLogOut.setOnClickListener {
            viewModel.logOut()
        }

        viewModel.isLogout.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(Intent(Intent(requireActivity(), AuthActivity::class.java)))
                requireActivity().finish()
                Toast.makeText(requireActivity(), StringConstants.signingOut, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}