package com.example.tester.presentation.first

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class FirstViewModel : ViewModel() {
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading = _loading.asStateFlow()
    private val _state1: MutableStateFlow<String?> = MutableStateFlow(null)
    val state1 = _state1.asStateFlow()
    fun run() {
        flow { emit("State1") }
            .map {
                "Result is :$it"
            }
            .flowOn(Dispatchers.IO)
            .onStart { _loading.value = true }
            .onStart { delay(3000) }
            .onCompletion { _loading.value = false }
            .onEach {
                println("each:$it,thread:${Thread.currentThread().name}")
                _state1.value = it
            }
    }
}