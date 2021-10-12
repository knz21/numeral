package com.knz21.numeral.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.knz21.numeral.data.repository.NumeralRepository
import com.knz21.numeral.data.repository.NumeralType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NumeralListViewModel @Inject constructor(
    numeralRepository: NumeralRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {

        const val typeKey = "type"
    }

    private val type: NumeralType = savedStateHandle.get<NumeralType>(typeKey) ?: NumeralType.LargeKanji

    private val _numerals = MutableStateFlow(numeralRepository.getNumerals(type))
    val numerals = _numerals.asStateFlow()
}