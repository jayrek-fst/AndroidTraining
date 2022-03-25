package com.fs.jayrek.trainingtask.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fs.jayrek.trainingtask.R
import com.fs.jayrek.trainingtask.view.activity.MainActivity
import com.fs.jayrek.trainingtask.vmodel.AuthViewModel

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        val btnConfirm = view.findViewById<Button>(R.id.btnConfirm)
        val edEmail = view.findViewById<EditText>(R.id.edEmail)
        val edFName = view.findViewById<EditText>(R.id.edFirstName)
        val edLName = view.findViewById<EditText>(R.id.edLastName)
        val edPassword = view.findViewById<EditText>(R.id.edPassword)

        val viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        btnConfirm.setOnClickListener {
            viewModel.signUp(
                edEmail.text.toString(),
                edFName.text.toString(),
                edLName.text.toString(),
                edPassword.text.toString()
            )
        }

        viewModel.user.observe(viewLifecycleOwner){
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }

        viewModel.errorMsg.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
        }
        return view
    }

}