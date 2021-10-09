package com.knz21.numeral.ui.theme.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumeralDetailScreen(
    numeralDetailViewModel: NumeralDetailViewModel
) {
    val numeral by numeralDetailViewModel.numeral.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = numeral.read,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = numeral.name,
            style = MaterialTheme.typography.h3
        )
        NumberWithExponent(
            exponent = numeral.exponent,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = createNumber(numeral.exponent),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun NumberWithExponent(
    modifier: Modifier = Modifier,
    exponent: Int = 0
) {
    Row(modifier = modifier) {
        Text(
            text = "10",
            style = TextStyle(fontSize = 24.sp)
        )
        Text(
            text = exponent.toString(),
            style = MaterialTheme.typography.body2
        )
    }
}

private fun createNumber(exponent: Int): String =
    "1${mutableListOf<Int>().apply { repeat(exponent) { add(0) } }.joinToString("")}"