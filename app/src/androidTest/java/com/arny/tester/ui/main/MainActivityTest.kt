package com.arny.tester.ui.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() =
        run {
            step("Open Simple Screen") {
                activityTestRule.launchActivity(null)
                testLogger.i("I am testLogger")
                device.screenshots.take("Additional_screenshot")
                MainScreen {
                    simpleButton {
                        isVisible()
                        click()
                    }
                }
            }
        }
}