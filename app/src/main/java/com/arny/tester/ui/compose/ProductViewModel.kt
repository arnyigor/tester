package com.arny.tester.ui.compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {
    private val _sku: MutableLiveData<String> = MutableLiveData("Артикул: 80133659")
    val sku: LiveData<String> = _sku
}