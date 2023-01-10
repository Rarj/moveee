package com.example.movee.feature.caster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.caster.CasterRepositoryImpl
import com.example.movee.data.repository.caster.CasterResponse
import com.example.movee.network.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CasterViewModel(
    private val repo: CasterRepositoryImpl
) : ViewModel() {

    private var _casters = MutableStateFlow<ViewState<CasterResponse>>(ViewState.Loading(null))
    val casters get() = _casters

    fun casters(movieID: Int) {
        viewModelScope.launch {
            val response = repo.casters(movieID)
            response.collect { casters ->
                _casters.value = ViewState.Success(casters)
            }
            response.catch { throwable ->
                _casters.value = ViewState.Error(null, throwable.message.toString())
            }
        }
    }

    fun detailCasters(movieID: Int) {
        viewModelScope.launch {
            val response = repo.detailCasters(movieID)
            response.collect { casters ->
                _casters.value = ViewState.Success(casters)
            }
            response.catch { throwable ->
                _casters.value = ViewState.Error(null, throwable.message.toString())
            }
        }
    }

}