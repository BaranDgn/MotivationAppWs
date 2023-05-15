package com.example.psikoappws.data.repository

import com.example.psikoappws.domain.repository.AuthRepository
import com.example.psikoappws.domain.util.Resource
import com.example.psikoappws.domain.util.await

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository{

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun signIn(email: String, password: String): Resource<FirebaseUser> {
        return try{
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result?.user!!)
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error("Error")
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Resource<FirebaseUser> {
       return try{
           val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
           result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
           return Resource.Success(result?.user!!)
       }catch(e: Exception){
           return Resource.Error("Error")
       }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

}