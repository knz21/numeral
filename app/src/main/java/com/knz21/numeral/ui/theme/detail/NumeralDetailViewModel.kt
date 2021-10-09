package com.knz21.numeral.ui.theme.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knz21.numeral.data.repository.NumeralRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumeralDetailViewModel @Inject constructor(
    numeralRepository: NumeralRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {

        const val indexKey = "index"
    }

    private val index: Int = savedStateHandle.get<Int>(indexKey) ?: -1

    private val _numeral = MutableStateFlow(numeralRepository.getNumeral(index))
    val numeral = _numeral.asStateFlow()
}