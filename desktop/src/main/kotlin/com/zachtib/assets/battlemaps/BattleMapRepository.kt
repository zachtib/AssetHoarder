package com.zachtib.assets.battlemaps

import com.zachtib.assets.uuid.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

interface BattleMapRepository {

    suspend fun insertOrUpdate(battleMap: BattleMap)

    suspend fun getAll(): List<BattleMap>
    suspend fun getBattleMap(id: UUID): BattleMap?
    suspend fun getTaggedBattleMaps(vararg tags: String): List<BattleMap>

    suspend fun delete(battleMap: BattleMap)
    suspend fun delete(id: UUID)
}

class JsonFileBattleMapRepository(
    private val filename: String,
) : BattleMapRepository {

    private var hasLoaded: Boolean = false

    private val battleMaps = mutableMapOf<UUID, BattleMap>()

    fun load() {
        try {
            val file = File(filename)
            if (!file.isFile) {
                return
            }
            val inputStream = file.inputStream()
            val text = inputStream.bufferedReader().use { it.readText() }
            val loadedMaps = Json.decodeFromString<List<BattleMap>>(text)
            battleMaps.clear()
            battleMaps.putAll(loadedMaps.associateBy { it.id })
            hasLoaded = true
        } catch (e: SecurityException) {
            // log exception
        }
    }

    fun save() {
        try {
            val file = File(filename)
            if (!file.exists()) {
                file.createNewFile()
            }
            val encodedMaps = Json.encodeToString(battleMaps.values.toList())
            file.bufferedWriter().use { writer ->
                writer.write(encodedMaps)
            }
        } catch (e: IOException) {
            // log
        } catch (e: SecurityException) {
            // log
        }
    }

    private suspend fun checkLoad() {
        if (!hasLoaded) {
            withContext(Dispatchers.IO) {
                load()
            }
        }
    }

    override suspend fun insertOrUpdate(battleMap: BattleMap) {
        checkLoad()
        battleMaps[battleMap.id] = battleMap
    }

    override suspend fun getAll(): List<BattleMap> {
        checkLoad()
        return battleMaps.values.toList()
    }

    override suspend fun getBattleMap(id: UUID): BattleMap? {
        checkLoad()
        return battleMaps[id]
    }

    override suspend fun getTaggedBattleMaps(vararg tags: String): List<BattleMap> {
        checkLoad()
        val tagList = tags.toList()
        return battleMaps.values.filter { map ->
            map.tags.containsAll(tagList)
        }
    }

    override suspend fun delete(battleMap: BattleMap) {
        battleMaps.remove(battleMap.id)
    }

    override suspend fun delete(id: UUID) {
        battleMaps.remove(id)
    }
}
