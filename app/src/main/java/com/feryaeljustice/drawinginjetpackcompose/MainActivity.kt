package com.feryaeljustice.drawinginjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.feryaeljustice.drawinginjetpackcompose.ui.screens.drawing.DrawingScreen
import com.feryaeljustice.drawinginjetpackcompose.ui.theme.DrawingInJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawingInJetpackComposeTheme {
               DrawingScreen()
            }
        }
    }
}