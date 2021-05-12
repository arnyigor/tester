package com.arny.tester.ui.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arny.tester.ui.compose.components.ComposeScreen
import com.arny.tester.ui.compose.components.Toolbar
import com.arny.tester.ui.ui.theme.TesterTheme

class ComposeActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ComposeActivity::class.java))
        }
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesterTheme { ComposeContent(productViewModel) }
        }
    }

    @ExperimentalFoundationApi
    @Composable
    private fun ComposeContent(productViewModel: ProductViewModel) {
        val title = remember { mutableStateOf("") }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Toolbar(title = title)
            },
            content = {
                ComposeScreen(productViewModel = productViewModel, title)
            })
    }

    @ExperimentalFoundationApi
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeContent(ProductViewModel())
    }
}
