package com.fs.jayrek.trainingtask.view.fragment

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
import com.fs.jayrek.trainingtask.helper.*
import com.fs.jayrek.trainingtask.view.activity.MainActivity
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel
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
            HelperClass.closeKeyBoard(requireActivity())

            val email: String = binding.edEmail.text.toString()
            val fName: String = binding.edFirstName.text.toString()
            val lName: String = binding.edLastName.text.toString()
            val password: String = binding.edPassword.text.toString()

            if (userInputs(email, fName, lName, password))
                viewModel.signUp(email, fName, lName, password)
            else
                Toast.makeText(
                    requireActivity(),
                    StringConstants.SIGN_UP_VALIDATION,
                    Toast.LENGTH_SHORT
                ).show()
        }

        viewModel.authStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> DialogHelper.showProgressDialog(
                    StringConstants.SIGNING_UP,
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
                    Toast.makeText(
                        requireActivity(),
                        it.message.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun userInputs(email: String, fName: String, lName: String, password: String): Boolean {
        return email.isNotEmpty() && fName.isNotEmpty() &&
                lName.isNotEmpty() && password.isNotEmpty()
    }
}