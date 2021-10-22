package org.wit.fantasy.console.main

import mu.KotlinLogging
import org.wit.fantasy.console.controllers.PlayerController
import org.wit.fantasy.console.models.PlayerModel
import org.wit.fantasy.console.models.PlayerJSONStore
import org.wit.fantasy.console.models.PlayerMemStorage
import org.wit.fantasy.console.views.PlayerView

private val logger = KotlinLogging.logger {}


//val Players = PlayerMemStorage()
val PlayerView = PlayerView()
val player = PlayerController()
val Players = PlayerJSONStore()

fun main(args: Array<String>) {
    logger.info { "Launching Fantasy Premier League App" }
    com.github.mm.coloredconsole.println{"Fantasy Premier League App V.1.0".underline}

    var input: Int

    do {
        input = PlayerView.menu()
        when(input) {
            1 -> addPlayer()
            2 -> changePlayer()
            3 -> PlayerView.showPlayer(Players)
            4 -> searchPlayer()
            5 -> removePlayer()
            -1 -> println("Exiting App, Bye Bye!")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down FPL App. See you soon!!" }
}


fun addPlayer(){
    var aPlayer = PlayerModel()

    if (PlayerView.addPlayerData(aPlayer))
        Players.create(aPlayer)
    else
        logger.info("Player was not added")
}
fun changePlayer() {
    PlayerView.showPlayer(Players)
    var searchId = PlayerView.getId()
    val aPlayer = search(searchId)

    if(aPlayer != null) {
        if(PlayerView.updatePlayerData(aPlayer)) {
            Players.update(aPlayer)
            PlayerView.showPlayer(aPlayer)
            logger.info("Player has been changed to : [ $aPlayer ]")
        }
        else
            logger.info("Player has not been changed")
    }
    else
        println("Player has not been changed")
}
fun removePlayer() {
    PlayerView.showPlayer(Players)
    var searchId = PlayerView.getId()
    val aPlayer = search(searchId)

    if(aPlayer != null) {
        Players.delete(aPlayer)
        println("Player was removed from the team...")
        PlayerView.showPlayer(Players)
    }
    else
        println("Player was removed from the team")
}


fun searchPlayer() {

    val aPlayer = search(PlayerView.getId())!!
    PlayerView.showPlayer(aPlayer)
}


fun search(id: Long) : PlayerModel? {
    var foundPlayer: PlayerModel? = Players.findOne(id)
    return foundPlayer
}
