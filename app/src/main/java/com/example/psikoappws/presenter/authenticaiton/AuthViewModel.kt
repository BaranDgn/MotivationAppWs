package com.example.psikoappws.presenter.authenticaiton

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psikoappws.domain.repository.AuthRepository
import com.example.psikoappws.domain.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel(){


    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow : StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get()= repo.currentUser

    //DataStore
    private lateinit var dataStore: DataStore<Preferences>



    init {
        if(repo.currentUser != null){
            _loginFlow.value = Resource.Success(repo.currentUser!!)
        }

    }

    fun logInUser(email : String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading<FirebaseUser>()
        val result = repo.signIn(email, password)
        _loginFlow.value = result
    }

    fun signUpUser(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading<FirebaseUser>()
        val result = repo.signUp(name, email, password)
        _signupFlow.value = result
    }

    fun logOut(){
        repo.signOut()
        _loginFlow.value = null
        _signupFlow.value = null
    }





}