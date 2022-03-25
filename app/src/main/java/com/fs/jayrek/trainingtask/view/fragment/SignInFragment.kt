package com.fs.jayrek.trainingtask.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.view.activity.MainActivity
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signin, container, false)
        val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)
        val btnSignUp = view.findViewById<Button>(R.id.btnSignUp)
        val edEmail = view.findViewById<EditText>(R.id.edEmail)
        val edPassword = view.findViewById<EditText>(R.id.edPassword)

        val viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        btnSignIn.setOnClickListener {
            viewModel.signIn(edEmail.text.toString(), edPassword.text.toString())

        }
        viewModel.user.observe(viewLifecycleOwner) {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
        }

        btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        return view
    }

}