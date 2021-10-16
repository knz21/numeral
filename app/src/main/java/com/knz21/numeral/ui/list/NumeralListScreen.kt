package com.knz21.numeral.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.knz21.numeral.event.EventBus
import com.knz21.numeral.event.StartDetail
import com.knz21.numeral.model.Numeral
import kotlinx.coroutines.launch

@Composable
fun NumeralListScreen(
    numeralListViewModel: NumeralListViewModel
) {
    val numerals by numeralListViewModel.numerals.collectAsState()
    val onClick: (index: Int) -> Unit = {
        numeralListViewModel.viewModelScope.launch { EventBus.post(StartDetail(numeralListViewModel.type, it)) }
    }
    LazyColumn {
        items(numerals.size, key = { numerals[it].name }) { index ->
            NumeralRow(index, numerals[index], onClick)
        }
    }
}

@Preview
@Composable
fun NumeralRow(
    index: Int = -1,
    numeral: Numeral = Numeral("一", "いち", 0),
    onClick: (index: Int) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = { onClick(index) },
            )
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = numeral.name,
            style = MaterialTheme.typography.h5
        )
        if (numeral.read.isNotEmpty()) {
            Text(
                text = "（${numeral.read}）",
                style = MaterialTheme.typography.body1
            )
        }
    }
}