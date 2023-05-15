package com.example.psikoappws.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psikoappws.presenter.opening.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel@Inject constructor(
    private val repo : DataStoreRepository
): ViewModel() {

    fun saveOnBoardingState(completed: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveOnBoardingState(completed = completed)
        }
    }

}