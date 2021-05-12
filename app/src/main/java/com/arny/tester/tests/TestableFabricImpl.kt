package com.arny.tester.tests

class TestableFabricImpl : TestableFabric {
    override fun createTest(type: TestType): Testable {
        return when (type) {
            TestType.RX -> SingleTestsRunnable()
            TestType.THREAD -> MultiThreadTest()
        }
    }
}