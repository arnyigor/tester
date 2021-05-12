package com.arny.tester.ui.compose.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.arny.tester.ui.compose.ProductViewModel

@Composable
fun SecondScreen(productViewModel: ProductViewModel, title: MutableState<String>) {
    title.value = "Details"
    val sku by productViewModel.sku.observeAsState()
    Text(text = "Second screen! sku:$sku")
}