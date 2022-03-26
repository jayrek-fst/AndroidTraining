package com.fs.jayrek.trainingtask.model.repository

import android.util.Log
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

class AuthRepository {

    suspend fun signInWithEmail(
        email: String,
        password: String
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                Resource.Success(
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
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
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .await()
                FirebaseFirestore.getInstance().collection(StringConstants.documentUser)
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
                    FirebaseFirestore.getInstance().collection(StringConstants.documentUser)
                        .document(uid)
                        .get().await()
                )
            }
        }
    }

    fun checkUser(): FirebaseUser? = FirebaseAuth.getInstance().currentUser

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
