package com.arny.tester.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.arny.tester.R
import com.arny.tester.databinding.ActivityMainBinding
import com.arny.tester.service.TestService
import com.arny.tester.service.TestService.Companion.EXTRA_PARAM1
import com.arny.tester.service.TestService.Companion.EXTRA_PARAM2
import com.arny.tester.tests.TestType
import com.arny.tester.tests.TestableFabric
import com.arny.tester.tests.TestableFabricImpl
import com.arny.tester.ui.compose.ComposeActivity
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    val TAG = MainActivity::class.java.canonicalName

    private val testableFabric: TestableFabric = TestableFabricImpl()

    private var testType: TestType by Delegates.observable(TestType.RX) { _, _, newValue ->
        binding.tvTestSwitch.text = newValue.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        binding.tvStartService.setOnClickListener {
            startService(Intent(this, TestService::class.java).apply {
                putExtra(EXTRA_PARAM1, System.currentTimeMillis())
                putExtra(EXTRA_PARAM2, Random(System.currentTimeMillis()).nextInt())
            })
        }
        with(binding) {
            tvTestSwitch.text = TestType.RX.toString()
            tvText.setOnClickListener {
                testableFabric.createTest(testType).runTest(arrayOf(""))
            }
            tvCompose.setOnClickListener {
                ComposeActivity.start(this@MainActivity)
            }
            tvTestSwitch.setOnClickListener {
                testType = testType.next()
            }
            pbLoader.isVisible = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}
