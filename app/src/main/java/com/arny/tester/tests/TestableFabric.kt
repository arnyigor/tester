package com.arny.tester.tests

interface TestableFabric {
    fun createTest(type: TestType): Testable
}