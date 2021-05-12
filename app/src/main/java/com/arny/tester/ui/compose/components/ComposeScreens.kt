package com.arny.tester.ui.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arny.tester.ui.compose.ProductViewModel

enum class ComposeScreens {
    PRODUCT_SCREEN,
    DETAILS_SCREEN
}

@Composable
fun ComposeScreen(productViewModel: ProductViewModel, title: MutableState<String>) {
    val navController = rememberNavController()
    Column {
        NavHost(navController, startDestination = ComposeScreens.PRODUCT_SCREEN.toString()) {
            composable(ComposeScreens.PRODUCT_SCREEN.toString()) {
                ProductScreen(
                    navController,
                    productViewModel,
                    title
                )
            }
            composable(ComposeScreens.DETAILS_SCREEN.toString()) {
                SecondScreen(
                    productViewModel,
                    title
                )
            }
        }
    }
}
