package com.arny.tester.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.Executors

class TestService : Service() {
    private val executor = Executors.newSingleThreadExecutor()

    companion object {
        const val EXTRA_PARAM1 = "param1"
        const val EXTRA_PARAM2 = "param2"
        const val TAG = "TestService"
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val extras = intent?.extras
        val time = extras?.getLong(EXTRA_PARAM1) ?: 0
        val rand = extras?.getInt(EXTRA_PARAM2) ?: 0
        val l = System.currentTimeMillis() - time
        Log.i(
            TAG,
            "onStartCommand thread:${Thread.currentThread().name}, onStartCommand: diff:$l,rand:$rand"
        )
        executor.submit {
            while (true) {
                Log.i(
                    TAG,
                    "onStartCommand thread:${Thread.currentThread().name}, onStartCommand: diff:$l,rand:$rand"
                )
                Thread.sleep(1000)
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}