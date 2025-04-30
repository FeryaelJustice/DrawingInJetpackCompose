package com.feryaeljustice.drawinginjetpackcompose.data.model

import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class SerializableOffset(val x: Float? = null, val y: Float? = null)

@Serializable
data class SerializablePathData(
    val id: String? = null,
    val colorArgb: Long? = null,
    val path: List<SerializableOffset>? = listOf()
)

object SerializableOffsetSerializer : Serializer<SerializableOffset> {
    override val defaultValue: SerializableOffset
        get() = SerializableOffset()

    override suspend fun readFrom(input: InputStream): SerializableOffset {
        return try {
            Json.decodeFromString(
                deserializer = SerializableOffset.serializer(),
                string = input.readBytes().toString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: SerializableOffset,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(serializer = SerializableOffset.serializer(), value = t)
                .encodeToByteArray()
        )
    }

}

object PathDataSerializer : Serializer<SerializablePathData> {
    override val defaultValue: SerializablePathData
        get() = SerializablePathData()

    override suspend fun readFrom(input: InputStream): SerializablePathData {
        return try {
            Json.decodeFromString(
                deserializer = SerializablePathData.serializer(),
                string = input.readBytes().toString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: SerializablePathData,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(serializer = SerializablePathData.serializer(), value = t)
                .encodeToByteArray()
        )
    }

}