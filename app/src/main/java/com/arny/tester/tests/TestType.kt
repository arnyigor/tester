package com.arny.tester.tests

sealed class TestType {
    object RX : TestType()
    object THREAD : TestType()

    override fun toString(): String {
        return when (this) {
            is RX -> "RX"
            is THREAD -> "THREAD"
        }
    }

    fun next(): TestType {
        return when (this) {
            is RX -> THREAD
            THREAD -> RX
        }
    }
}
