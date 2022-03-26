package com.fs.jayrek.trainingtask.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.databinding.FragmentSignupBinding
import com.fs.jayrek.trainingtask.view.activity.MainActivity
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel
import android.view.inputmethod.InputMethodManager
import com.fs.jayrek.trainingtask.helper.DialogHelper
import com.fs.jayrek.trainingtask.helper.Resource
import com.fs.jayrek.trainingtask.helper.StringConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun observers() {
        val viewModel: AuthViewModel by viewModels()

        binding.btnConfirm.setOnClickListener {
            closeKeyBoard()
            viewModel.signUp(
                binding.edEmail.text.toString(),
                binding.edFirstName.text.toString(),
                binding.edLastName.text.toString(),
                binding.edPassword.text.toString()
            )
        }

        viewModel.authStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> DialogHelper.showProgressDialog(
                    StringConstants.signingUp,
                    requireActivity(),
                    false
                )
                is Resource.Success -> {
                    DialogHelper.dismissProgressDialog()
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    requireActivity().finish()
                }
                is Resource.Error -> {
                    DialogHelper.dismissProgressDialog()
                    Toast.makeText(requireActivity(),
                        it.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun closeKeyBoard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}