package com.knz21.numeral.data.repository

import com.knz21.numeral.NumeralType
import com.knz21.numeral.data.largeKanji
import com.knz21.numeral.data.shortScale
import com.knz21.numeral.data.smallKanji
import com.knz21.numeral.model.Numeral
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumeralRepository @Inject constructor() {

    fun getNumerals(type: NumeralType): List<Numeral> = when (type) {
        NumeralType.LargeKanji -> largeKanji
        NumeralType.SmallKanji -> smallKanji
        NumeralType.ShortScale -> shortScale
    }

    fun getNumeral(type: NumeralType, index: Int): Numeral = getNumerals(type)[index]
}