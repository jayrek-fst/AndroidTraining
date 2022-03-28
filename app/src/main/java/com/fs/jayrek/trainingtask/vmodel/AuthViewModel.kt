package com.fs.jayrek.trainingtask.vmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fs.jayrek.trainingtask.helper.Resource
import com.fs.jayrek.trainingtask.model.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val _repo: AuthRepository) : ViewModel() {

    private val _authStatus = MutableLiveData<Resource<AuthResult>>()
    val authStatus: LiveData<Resource<AuthResult>> = _authStatus

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                _authStatus.postValue(Resource.Loading())
                val response = _repo.signInWithEmail(email, password)
                _authStatus.postValue(response)
            } catch (e: Exception) {
                _authStatus.postValue(Resource.Error(e.message.toString()))
            }
        }
    }

    fun signUp(email: String, fName: String, lName: String, password: String) {
        viewModelScope.launch {
            try {
                _authStatus.postValue(Resource.Loading())
                val response = _repo.signUpWithEmail(email, password, fName, lName)
                _authStatus.postValue(response)
            } catch (e: Exception) {
                _authStatus.postValue(Resource.Error(e.message.toString()))
            }
        }
    }

    private val _snapShot = MutableLiveData<Resource<DocumentSnapshot>>()
    val snapShot: LiveData<Resource<DocumentSnapshot>> = _snapShot

    fun getUser() {
        viewModelScope.launch {
            try {
                _snapShot.postValue(Resource.Loading())
                val uid = FirebaseAuth.getInstance().currentUser!!.uid
                val snap = _repo.getUserInfo(uid)
                _snapShot.postValue(snap)
            } catch (e: Exception) {
                _snapShot.postValue(Resource.Error(e.message.toString()))
            }
        }
    }

    private val _isLogOut: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val isLogout: LiveData<Resource<Boolean>> = _isLogOut

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    fun checkUserLogIn() {
        viewModelScope.launch {
            try {
                val repository = _repo.checkUser()
                _user.postValue(repository!!)
            } catch (e: Exception) {
                _user.postValue(null)
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            try {
                _isLogOut.postValue(Resource.Loading())
                FirebaseAuth.getInstance().signOut()
                _isLogOut.postValue(Resource.Success(true))
            } catch (e: Exception) {
                _isLogOut.postValue(Resource.Error(e.message.toString()))
            }
        }
    }
}