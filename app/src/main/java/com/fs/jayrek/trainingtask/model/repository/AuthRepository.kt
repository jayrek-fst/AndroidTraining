package com.fs.jayrek.trainingtask.model.repository

import android.util.Log
import com.fs.jayrek.trainingtask.helper.StringHelper
import com.fs.jayrek.trainingtask.model.model.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository {

    suspend fun signInWithEmail(
        email: String,
        password: String
    ): AuthResult? = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()

    suspend fun signUpWithEmail(
        email: String,
        password: String
    ): AuthResult? =
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()

    suspend fun saveUserToFirestore(
        uid: String,
        user: User
    ) {
        FirebaseFirestore.getInstance().collection(StringHelper.documentUser)
            .document(uid)
            .set(user)
            .await()
    }
}