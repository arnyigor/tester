package com.arny.tester.ui.main

import com.arny.tester.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    val simpleButton = KButton { withId(R.id.btnSimpleTest) }

    val activityTest = KButton { withId(R.id.btnActivityTest) }

}