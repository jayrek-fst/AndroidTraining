package com.fs.jayrek.trainingtask.model.repository

import com.fs.jayrek.trainingtask.helper.Resource
import com.fs.jayrek.trainingtask.helper.StringConstants
import com.fs.jayrek.trainingtask.helper.safeApiCall
import com.fs.jayrek.trainingtask.model.model.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(private val _auth: FirebaseAuth, private val _fireStore: FirebaseFirestore) {

    suspend fun signInWithEmail(
        email: String,
        password: String
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                Resource.Success(
                    _auth.signInWithEmailAndPassword(email, password).await()
                )
            }
        }
    }

    suspend fun signUpWithEmail(
        email: String,
        password: String, fName: String, lName: String
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                val user = User(fName, lName)
                val auth =
                    _auth.createUserWithEmailAndPassword(email, password)
                        .await()
                _fireStore.collection(StringConstants.documentUser)
                    .document(auth.user!!.uid)
                    .set(user)
                    .await()
                Resource.Success(auth)
            }
        }
    }

    suspend fun getUserInfo(
        uid: String
    ): Resource<DocumentSnapshot> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                Resource.Success(
                    _fireStore.collection(StringConstants.documentUser)
                        .document(uid)
                        .get().await()
                )
            }
        }
    }

    fun checkUser(): FirebaseUser? = _auth.currentUser

    /* suspend fun saveUserToFirestore(
         uid: String,
         user: User
     ) {
         FirebaseFirestore.getInstance().collection(StringConstants.documentUser)
             .document(uid)
             .set(user)
             .await()
     }*/
}
