package com.knz21.numeral.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@Composable
fun NumeralDetailScreen(
    numeralDetailViewModel: NumeralDetailViewModel
) {
    val numeral by numeralDetailViewModel.numeral.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        if (numeral.read.isNotEmpty()) {
            Text(
                text = numeral.read,
                style = MaterialTheme.typography.body1
            )
        }
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
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
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
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(start = 2.dp)
        )
    }
}

private fun createNumber(exponent: Int): String =
    if (exponent >= 0) {
        "1${
            mutableListOf<String>().apply {
                repeat(exponent) {
                    if ((exponent + 1 - it) % 3 == 1) add(",")
                    add("0")
                }
            }.joinToString("")
        }"
    } else {
        "${
            mutableListOf<String>().apply {
                repeat(abs(exponent)) {
                    add("0")
                    if (it == 0) add(".")
                }
            }.joinToString("")
        }1"
    }

