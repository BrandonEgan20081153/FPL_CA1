package org.wit.fantasy.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlayerMemStorage : PlayerStorage {
    val Players = ArrayList<PlayerModel>()

    override fun findAll(): List<PlayerModel> {
        return Players
    }

    override fun findOne(id: Long) : PlayerModel? {
        var foundPlayer: PlayerModel? = Players.find { p -> p.id == id }
        return foundPlayer
    }

    override fun create(Player: PlayerModel) {
        Player.id = getId()
        Players.add(Player)
        logAll()
    }

    override fun update(Player: PlayerModel) {
        var foundPlayer = findOne(Player.id!!)
        if (foundPlayer != null) {
            foundPlayer.name = Player.name
            foundPlayer.position = Player.position
        }
    }
    override fun delete(Player: PlayerModel) {
        Players.remove(Player)
    }

    internal fun logAll() {
        Players.forEach { logger.info("${it}") }
    }
}