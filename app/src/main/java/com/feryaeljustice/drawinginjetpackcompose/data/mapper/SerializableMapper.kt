package com.feryaeljustice.drawinginjetpackcompose.data.mapper

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.feryaeljustice.drawinginjetpackcompose.ui.screens.drawing.PathData
import com.feryaeljustice.drawinginjetpackcompose.data.model.SerializableOffset
import com.feryaeljustice.drawinginjetpackcompose.data.model.SerializablePathData

fun PathData.toSerializable() = SerializablePathData(
    id = id,
    colorArgb = color.toArgb().toLong(),
    path = path.map { SerializableOffset(it.x, it.y) }
)

fun SerializablePathData.toPathData() = PathData(
    id = id ?: "",
    color = Color(colorArgb?.toInt() ?: 0),
    path = path?.map { Offset(it.x!!, it.y!!) } ?: listOf()
)