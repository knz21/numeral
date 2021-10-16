package com.knz21.numeral.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.knz21.numeral.NumeralType
import com.knz21.numeral.data.repository.NumeralRepository
import com.knz21.numeral.ui.theme.Key
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NumeralListViewModel @Inject constructor(
    numeralRepository: NumeralRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val type: NumeralType = savedStateHandle.get<NumeralType>(Key.type) ?: NumeralType.LargeKanji

    private val _numerals = MutableStateFlow(numeralRepository.getNumerals(type))
    val numerals = _numerals.asStateFlow()
}