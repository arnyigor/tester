package com.arny.tester.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arny.tester.R
import com.arny.tester.databinding.ActivityMainBinding
import com.arny.tester.service.TestService
import com.arny.tester.service.TestService.Companion.EXTRA_PARAM1
import com.arny.tester.service.TestService.Companion.EXTRA_PARAM2
import com.arny.tester.ui.compose.ComposeActivity
import com.arny.tester.ui.first.FirstActivity
import com.arny.tester.ui.simple.SimpleActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        binding.btnSimpleTest.setOnClickListener {
            SimpleActivity.start(this)
        }
        binding.btnServiceTest.setOnClickListener {
            startService(Intent(this, TestService::class.java).apply {
                putExtra(EXTRA_PARAM1, System.currentTimeMillis())
                putExtra(EXTRA_PARAM2, Random(System.currentTimeMillis()).nextInt())
            })
        }
        binding.btnActivityTest.setOnClickListener {
            FirstActivity.start(this)
        }
        binding.btnComposeTest.setOnClickListener {
            ComposeActivity.start(this@MainActivity)
        }
    }
}
