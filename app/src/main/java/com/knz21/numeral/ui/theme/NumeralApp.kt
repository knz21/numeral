package com.knz21.numeral.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.knz21.numeral.NumeralType
import com.knz21.numeral.event.EventBus
import com.knz21.numeral.event.StartDetail
import com.knz21.numeral.ui.detail.NumeralDetailScreen
import com.knz21.numeral.ui.detail.NumeralDetailViewModel
import com.knz21.numeral.ui.list.NumeralListScreen
import com.knz21.numeral.ui.list.NumeralListViewModel

@Composable
fun NumeralApp() {
    NumeralTheme {
        Surface(color = MaterialTheme.colors.background) {
            val navController = rememberNavController()
            val startDetail by EventBus.events<StartDetail>().collectAsState(null)
            startDetail?.let {
                navController.navigate("${Destinations.detail}/${it.type}/${it.index}")
            }
            Column {
                LazyRow {
                    items(NumeralType.values().size) {
                        val type = NumeralType.values()[it]
                        Box(
                            modifier = Modifier.clickable(
                                onClick = { navController.navigate("${Destinations.list}/${type.name}") },
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = type.displayName,
                                style = MaterialTheme.typography.h5
                            )
                        }
                    }
                }
                NavHost(
                    navController = navController,
                    startDestination = "${Destinations.list}/{${Key.type}}"
                ) {
                    composable(
                        route = "${Destinations.list}/{${Key.type}}",
                        arguments = listOf(
                            navArgument(Key.type) { type = NavType.EnumType(NumeralType::class.java) }
                        )) {
                        val viewModel = hiltViewModel<NumeralListViewModel>()
                        NumeralListScreen(viewModel)
                    }
                    composable(
                        route = "${Destinations.detail}/{${Key.type}}/{${Key.index}}",
                        arguments = listOf(
                            navArgument(Key.type) { type = NavType.EnumType(NumeralType::class.java) },
                            navArgument(Key.index) { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val viewModel = hiltViewModel<NumeralDetailViewModel>(backStackEntry)
                        NumeralDetailScreen(viewModel)
                    }
                }
            }
        }
    }
}

object Destinations {
    const val list = "list"
    const val detail = "detail"
}

object Key {
    const val type = "type"
    const val index = "index"
}