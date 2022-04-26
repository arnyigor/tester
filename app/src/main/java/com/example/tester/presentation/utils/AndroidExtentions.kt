package com.example.tester.presentation.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope

val Fragment.viewLifecycleScope
    get() = viewLifecycleOwner.lifecycle.coroutineScope
