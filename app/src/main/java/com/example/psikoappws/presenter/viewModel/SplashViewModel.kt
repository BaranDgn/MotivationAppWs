package com.example.psikoappws.presenter.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psikoappws.BottomBarScreen
import com.example.psikoappws.Screens
import com.example.psikoappws.domain.repository.AuthRepository
import com.example.psikoappws.presenter.authenticaiton.AuthViewModel
import com.example.psikoappws.presenter.graph.AuthScreen
import com.example.psikoappws.presenter.graph.Graph
import com.example.psikoappws.presenter.opening.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repo: DataStoreRepository,
    private val repositoryOfAuth : AuthRepository
) :ViewModel() {
    //when to close the splash screen
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading : State<Boolean> = _isLoading

    private var _startDestination: MutableState<String> = mutableStateOf(AuthScreen.Welcome.route)
    val startDestination : State<String> = _startDestination

    init{
        viewModelScope.launch {
            repo.readOnBoardingState().collect{completed ->
                if(completed){
                    _startDestination.value = AuthScreen.Login.route
                   // _startDestination.value = BottomBarScreen.Home.route
                }
                else{
                    _startDestination.value = AuthScreen.Welcome.route
                    //not welcome screen in auth, try in root navgraph
                }

               /* if(completed && repositoryOfAuth.currentUser !=null){
                    _startDestination.value = Graph.HOME
                }
                else if(completed){
                    _startDestination.value= Graph.AUTHENTICATION
                }
                else{
                    _startDestination.value = Graph.WELCOME
                }*/
            }
            _isLoading.value = false
        }
    }




}