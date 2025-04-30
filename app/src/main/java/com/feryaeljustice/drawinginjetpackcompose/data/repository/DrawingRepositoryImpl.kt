package com.feryaeljustice.drawinginjetpackcompose.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.feryaeljustice.drawinginjetpackcompose.data.mapper.toPathData
import com.feryaeljustice.drawinginjetpackcompose.data.mapper.toSerializable
import com.feryaeljustice.drawinginjetpackcompose.data.model.SerializablePathData
import com.feryaeljustice.drawinginjetpackcompose.domain.repository.DrawingRepository
import com.feryaeljustice.drawinginjetpackcompose.ui.screens.drawing.PathData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DrawingRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : DrawingRepository {
    private object Keys {
        val PATHS = stringPreferencesKey("paths_json")
    }

    override suspend fun save(paths: List<PathData>) {
        val serializables = paths.map(PathData::toSerializable)
        val json =
            Json.encodeToString(ListSerializer(SerializablePathData.serializer()), serializables)
       dataStore.edit { prefs ->
            prefs[Keys.PATHS] = json
        }
    }

    override fun loadFlow(): Flow<List<PathData>> =
        dataStore.data
            .map { prefs ->
                prefs[Keys.PATHS]?.let { json ->
                    val list = Json.decodeFromString(
                        ListSerializer(SerializablePathData.serializer()), json
                    )
                    list.map(SerializablePathData::toPathData)
                } ?: emptyList()
            }
}