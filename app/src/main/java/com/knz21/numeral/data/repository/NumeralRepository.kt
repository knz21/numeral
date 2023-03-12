package com.knz21.numeral.data.repository

import com.knz21.numeral.NumeralType
import com.knz21.numeral.data.decimalNumbers
import com.knz21.numeral.data.largeKanjis
import com.knz21.numeral.data.largeNumbers
import com.knz21.numeral.data.smallKanjis
import com.knz21.numeral.model.Numeral
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumeralRepository @Inject constructor() {

    fun getNumerals(type: NumeralType): List<Numeral> = when (type) {
        NumeralType.LargeKanji -> largeKanjis
        NumeralType.SmallKanji -> smallKanjis
        NumeralType.LargeNumber -> largeNumbers
        NumeralType.DecimalNumber -> decimalNumbers
    }

    fun getNumeral(type: NumeralType, index: Int): Numeral = getNumerals(type)[index]
}