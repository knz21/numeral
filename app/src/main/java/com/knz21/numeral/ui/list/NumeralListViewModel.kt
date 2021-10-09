package com.knz21.numeral.ui.list

import androidx.lifecycle.ViewModel
import com.knz21.numeral.data.repository.NumeralRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NumeralListViewModel @Inject constructor(
    numeralRepository: NumeralRepository
): ViewModel() {

    private val _numerals = MutableStateFlow(numeralRepository.getNumerals())
    val numerals = _numerals.asStateFlow()
}