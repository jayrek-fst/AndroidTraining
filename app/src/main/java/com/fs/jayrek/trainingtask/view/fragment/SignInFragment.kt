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
import androidx.navigation.fragment.findNavController
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.databinding.FragmentSigninBinding
import com.fs.jayrek.trainingtask.helper.*
import com.fs.jayrek.trainingtask.view.activity.MainActivity
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun observers() {
        val viewModel: AuthViewModel by viewModels()

        binding.btnSignIn.setOnClickListener {
            HelperClass.closeKeyBoard(requireActivity())

            val email: String = binding.edEmail.text.toString()
            val password: String = binding.edPassword.text.toString()

            if (userInputs(email, password))
                viewModel.signIn(email, password)
            else Toast.makeText(
                requireActivity(),
                StringConstants.SIGN_IN_VALIDATION,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnSignUp.setOnClickListener {
            HelperClass.closeKeyBoard(requireActivity())
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        viewModel.authStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> DialogHelper.showProgressDialog(
                    StringConstants.SIGNING_IN,
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
                        it.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun userInputs(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}