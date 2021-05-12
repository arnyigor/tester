package com.arny.tester.ui.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arny.tester.databinding.ActivitySimpleBinding
import com.arny.tester.tests.TestType
import com.arny.tester.tests.TestableFabric
import com.arny.tester.tests.TestableFabricImpl
import kotlin.properties.Delegates

class SimpleActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SimpleActivity::class.java))
        }
    }

    private lateinit var binding: ActivitySimpleBinding
    private val testableFabric: TestableFabric = TestableFabricImpl()

    private var testType: TestType by Delegates.observable(TestType.RX) { _, _, newValue ->
        binding.tvTestSwitch.text = "Test Type $newValue"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleBinding.inflate(layoutInflater);
        setContentView(binding.root)

        with(binding) {
            tvTestSwitch.text = TestType.RX.toString()
            btnStartTest.setOnClickListener {
                testableFabric.createTest(testType).runTest(arrayOf(""))
            }
            tvTestSwitch.setOnClickListener {
                testType = testType.next()
            }
        }
    }
}