package com.fs.jayrek.trainingtask.model.repository

import com.fs.jayrek.trainingtask.helper.StringConstants
import com.fs.jayrek.trainingtask.model.model.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository {

    fun checkUser() : FirebaseUser? = FirebaseAuth.getInstance().currentUser

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
        FirebaseFirestore.getInstance().collection(StringConstants.documentUser)
            .document(uid)
            .set(user)
            .await()
    }

    suspend fun getUserInfo(
        uid: String
    ): DocumentSnapshot? {
        return FirebaseFirestore.getInstance().collection(StringConstants.documentUser)
             .document(uid)
             .get().await()
    }
}
