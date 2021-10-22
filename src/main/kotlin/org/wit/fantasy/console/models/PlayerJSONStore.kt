package org.wit.fantasy.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.fantasy.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "Player.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<PlayerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PlayerJSONStore : PlayerStorage {

    var Players = mutableListOf<PlayerModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PlayerModel> {
        return Players
    }

    override fun findOne(id: Long) : PlayerModel? {
        var foundPlayer: PlayerModel? = Players.find { p -> p.id == id }
        return foundPlayer
    }

    override fun create(Player: PlayerModel) {
        Player.id = generateRandomId()
        Players.add(Player)
        serialize()
    }
    internal fun getId(): Long {
        return lastId++
    }

    override fun update(Player: PlayerModel) {
        var foundPlayer = findOne(Player.id!!)
        if (foundPlayer != null) {
            foundPlayer.name = Player.name
            foundPlayer.position = Player.position
        }
        serialize()
    }

    override fun delete(Player: PlayerModel) {
        Players.remove(Player)
        serialize()
    }

    internal fun logAll() {
        Players.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(Players, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        Players = Gson().fromJson(jsonString, listType)
    }
}