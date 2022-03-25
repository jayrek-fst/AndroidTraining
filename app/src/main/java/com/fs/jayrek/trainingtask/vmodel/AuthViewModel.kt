package com.fs.jayrek.trainingtask.vmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.jayrek.trainingtask.model.model.User
import com.fs.jayrek.trainingtask.model.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {

    private val _user: MutableLiveData<FirebaseUser> = MutableLiveData()
    val user: LiveData<FirebaseUser> = _user

    private val _err: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _err

    fun checkUserLogIn() {
        viewModelScope.launch {
            try {
                val repository = AuthRepository().checkUser()
                _user.postValue(repository)
            } catch (e: Exception) {
                _err.postValue(e.message)
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val repository = AuthRepository().signInWithEmail(email, password)
                _user.postValue(repository?.user)
            } catch (e: Exception) {
                _err.postValue(e.message)
            }
        }
    }

    fun signUp(email: String, fName: String, lName: String, password: String) {
        viewModelScope.launch {
            try {
                val repository = AuthRepository().signUpWithEmail(email, password)
                try {
                    val uid = repository?.user?.uid
                    val user = User(fName, lName)
                    AuthRepository().saveUserToFirestore(uid.toString(), user)
                    _user.postValue(repository?.user)
                } catch (e: Exception) {
                    _err.postValue(e.message)
                }
            } catch (e: Exception) {
                _err.postValue(e.message)
            }
        }
    }

    private val _snapShot : MutableLiveData<DocumentSnapshot> = MutableLiveData()
    val snapShot : LiveData<DocumentSnapshot> = _snapShot

    fun getUser() {
        viewModelScope.launch {
            try{
                val user = FirebaseAuth.getInstance().currentUser
                val snap = AuthRepository().getUserInfo(user!!.uid)
                _snapShot.postValue(snap)
            }catch (e: Exception) {
                _err.postValue(e.message)
            }
        }
    }

}