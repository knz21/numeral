package com.knz21.numeral.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.knz21.numeral.data.repository.NumeralRepository
import com.knz21.numeral.data.repository.NumeralType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NumeralDetailViewModel @Inject constructor(
    numeralRepository: NumeralRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {

        const val typeKey = "type"

        const val indexKey = "index"
    }

    private val type: NumeralType = savedStateHandle.get<NumeralType>(typeKey) ?: NumeralType.LargeKanji

    private val index: Int = savedStateHandle.get<Int>(indexKey) ?: -1

    private val _numeral = MutableStateFlow(numeralRepository.getNumeral(type, index))
    val numeral = _numeral.asStateFlow()
}