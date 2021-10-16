package com.knz21.numeral.event

import com.knz21.numeral.data.repository.NumeralType

class StartDetail(val type: NumeralType, val index: Int): EventBus.Event