package com.feryaeljustice.drawinginjetpackcompose.domain.repository

import com.feryaeljustice.drawinginjetpackcompose.ui.screens.drawing.PathData
import kotlinx.coroutines.flow.Flow

interface DrawingRepository {
    suspend fun save(paths: List<PathData>): Unit
    fun loadFlow(): Flow<List<PathData>>
}