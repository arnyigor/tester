package com.arny.tester.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.arny.tester.ui.compose.ProductViewModel
import com.arny.tester.ui.ui.theme.brownGreyColor

@Composable
fun ProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel,
    title: MutableState<String>
) {
    title.value = "Product"
    val sku by productViewModel.sku.observeAsState()
    LazyColumn(content = {
        item { ImageHeader() }
        item {
            Text(
                sku.orEmpty(), modifier = Modifier.padding(top = 24.dp, start = 16.dp),
                style = TextStyle(color = brownGreyColor),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
        item {
            Button(onClick = { navController.navigate(ComposeScreens.DETAILS_SCREEN.toString()) }) {
                Text(text = "Continue")
            }
        }
    })
}

@Composable
fun ImageHeader() {
    Box(
        modifier = Modifier
            .background(color = White)
            .height(300.dp)
            .fillMaxWidth()
    )
}