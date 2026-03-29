package com.pipingisodraw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pipingisodraw.ui.PipingIsoDrawApp
import com.pipingisodraw.ui.theme.PipingIsoDrawTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PipingIsoDrawTheme {
                PipingIsoDrawApp()
            }
        }
    }
}
