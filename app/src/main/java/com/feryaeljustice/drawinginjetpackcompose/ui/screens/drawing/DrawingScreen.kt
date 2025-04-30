package com.feryaeljustice.drawinginjetpackcompose.ui.screens.drawing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.feryaeljustice.drawinginjetpackcompose.ui.components.CanvasControls
import com.feryaeljustice.drawinginjetpackcompose.ui.components.DrawingCanvas

@Composable
fun DrawingScreen(viewModel: DrawingViewModel = hiltViewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val state by viewModel.state.collectAsStateWithLifecycle()

            DrawingCanvas(
                paths = state.paths,
                currentPath = state.currentPath,
                onAction = viewModel::onAction,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            CanvasControls(
                selectedColor = state.selectedColor,
                colors = allColors,
                onSelectColor = {
                    viewModel.onAction(DrawingAction.OnSelectColor(it))
                },
                onSave = {
                    viewModel.saveDrawing()
                },
                onClearCanvas = {
                    viewModel.onAction(DrawingAction.OnClearCanvasClick)
                }
            )
        }
    }
}