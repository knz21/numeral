package com.knz21.numeral.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.knz21.numeral.data.repository.NumeralRepository
import com.knz21.numeral.data.repository.NumeralType
import com.knz21.numeral.ui.theme.Key
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NumeralDetailViewModel @Inject constructor(
    numeralRepository: NumeralRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val type: NumeralType = savedStateHandle.get<NumeralType>(Key.type) ?: NumeralType.LargeKanji

    private val index: Int = savedStateHandle.get<Int>(Key.index) ?: -1

    private val _numeral = MutableStateFlow(numeralRepository.getNumeral(type, index))
    val numeral = _numeral.asStateFlow()
}