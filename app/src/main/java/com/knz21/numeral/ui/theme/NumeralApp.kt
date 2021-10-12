package com.knz21.numeral.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.knz21.numeral.data.repository.NumeralType
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
            startDetail?.index?.let {
                navController.navigate("${Destinations.detail}/${NumeralType.LargeKanji.name}/$it")
            }
            NavHost(
                navController = navController,
                startDestination = "${Destinations.list}/{${NumeralDetailViewModel.typeKey}}"
            ) {
                composable(
                    route = "${Destinations.list}/{${NumeralDetailViewModel.typeKey}}",
                    arguments = listOf(
                        navArgument(NumeralDetailViewModel.typeKey) { type = NavType.EnumType(NumeralType::class.java) }
                    )) {
                    val viewModel = hiltViewModel<NumeralListViewModel>()
                    NumeralListScreen(viewModel)
                }
                composable(
                    route = "${Destinations.detail}/{${NumeralDetailViewModel.typeKey}}/{${NumeralDetailViewModel.indexKey}}",
                    arguments = listOf(
                        navArgument(NumeralDetailViewModel.typeKey) { type = NavType.EnumType(NumeralType::class.java) },
                        navArgument(NumeralDetailViewModel.indexKey) { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val viewModel = hiltViewModel<NumeralDetailViewModel>(backStackEntry)
                    NumeralDetailScreen(viewModel)
                }
            }
        }
    }
}

object Destinations {
    const val list = "list"
    const val detail = "detail"
}