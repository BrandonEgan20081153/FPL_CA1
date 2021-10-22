package org.wit.fantasy.console.models

interface PlayerStorage {
    fun findAll(): List<PlayerModel>
    fun findOne(id: Long): PlayerModel?
    fun create(Player: PlayerModel)
    fun update(Player: PlayerModel)
    fun delete(Player: PlayerModel)
}