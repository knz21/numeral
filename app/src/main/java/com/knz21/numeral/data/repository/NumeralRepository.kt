package com.knz21.numeral.data.repository

import com.knz21.numeral.model.Numeral
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumeralRepository @Inject constructor() {

    fun getNumerals(): List<Numeral> = numerals

    fun getNumeral(index: Int): Numeral = numerals[index]
}

private val numerals: List<Numeral> = listOf(
    Numeral("一", "いち", 0),
    Numeral("十", "じゅう", 1),
    Numeral("百", "ひゃく", 2),
    Numeral("千", "せん", 3),
    Numeral("万", "まん", 4),
    Numeral("億", "おく", 8),
    Numeral("兆", "ちょう", 12),
    Numeral("京", "けい", 16),
    Numeral("垓", "がい", 20),
    Numeral("𥝱", "じょ", 24),
    Numeral("穣", "じょう", 28),
    Numeral("溝", "こう", 32),
    Numeral("澗", "かん", 36),
    Numeral("正", "せい", 40),
    Numeral("載", "さい", 44),
    Numeral("極", "ごく", 48),
    Numeral("恒河沙", "ごうがしゃ", 52),
    Numeral("阿僧祇", "あそうぎ", 56),
    Numeral("那由他", "なゆた", 60),
    Numeral("不可思議", "ふかしぎ", 64),
    Numeral("無量大数", "むりょうたいすう", 68)
)