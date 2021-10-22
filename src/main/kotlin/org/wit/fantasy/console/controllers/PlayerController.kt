package org.wit.fantasy.console.controllers

import mu.KotlinLogging
import org.wit.fantasy.console.models.PlayerMemStorage
import org.wit.fantasy.console.models.PlayerModel
import org.wit.fantasy.console.models.PlayerJSONStore
import org.wit.fantasy.console.views.PlayerView

class PlayerController {
    val PlayerView = PlayerView()
    val Players = PlayerJSONStore()
    val logger = KotlinLogging.logger {}
    //val Players = PlayerMemStorage()

    init {
        logger.info { "Launching Fantasy Premier League App" }
           }
    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App, Bye Bye")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down FPL App. See you soon!" }
    }
    fun menu() :Int { return PlayerView.menu() }

    fun add(){
        var aPlayer = PlayerModel()

        if (PlayerView.addPlayerData(aPlayer))
            Players.create(aPlayer)
        else
            logger.info("Player was not added")
    }

    fun list() {
        PlayerView.showPlayer(Players)
    }

    fun update() {

        PlayerView.showPlayer(Players)
        var searchId = PlayerView.getId()
        val aPlayer = search(searchId)

        if(aPlayer != null) {
            if(PlayerView.updatePlayerData(aPlayer)) {
                Players.update(aPlayer)
                PlayerView.showPlayer(aPlayer)
                logger.info("Player has been changed : [ $aPlayer ]")
            }
            else
                logger.info("Player could not be changed")
        }
        else
            println("Player could not be changed...")
    }

    fun delete() {
        PlayerView.showPlayer(Players)
        var searchId = PlayerView.getId()
        val aPlayer = search(searchId)

        if(aPlayer != null) {
            Players.delete(aPlayer)
            println("Player was removed from the team...")
            PlayerView.showPlayer(Players)
        }
        else
            println("Player was removed from the team...")
    }

    fun search() {
        val aPlayer = search(PlayerView.getId())!!
        PlayerView.showPlayer(aPlayer)
    }


    fun search(id: Long) : PlayerModel? {
        var foundPlayer = Players.findOne(id)
        return foundPlayer
    }

}