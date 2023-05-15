package com.example.psikoappws.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.example.psikoappws.domain.util.Resource

interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun signIn(email: String, password: String): Resource<FirebaseUser>

    suspend fun signUp(name: String, email: String, password: String): Resource<FirebaseUser>

    fun signOut()
}