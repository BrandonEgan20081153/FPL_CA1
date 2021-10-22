package org.wit.fantasy.console.views

import org.wit.fantasy.console.main.PlayerView
import org.wit.fantasy.console.main.Players
import org.wit.fantasy.console.models.PlayerJSONStore
import org.wit.fantasy.console.models.PlayerModel

class PlayerView {
    fun menu() : Int {

        var option : Int
        var input: String?


        com.github.mm.coloredconsole.println{"MAIN MENU".red.bg.faint}
        com.github.mm.coloredconsole.println{" 1. Add a Player".yellow}
        com.github.mm.coloredconsole.println{" 2. Change a Player".blue}
        com.github.mm.coloredconsole.println{" 3. Show the Lineup".green}
        com.github.mm.coloredconsole.println{" 4. Search Player".purple}
        com.github.mm.coloredconsole.println{" 5. Delete Player".red}
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun showPlayer(Players : PlayerJSONStore) {
        println("Show all Players")
        println()
        Players.logAll()
        println()
    }

    fun showPlayer(Player : PlayerModel) {
        if(Player != null)
            println("Player info [ $Player ]")
        else
            println("Player was not found...")
    }

    fun addPlayerData(Player : PlayerModel) : Boolean {

        println()
        print("Enter their Name : ")
        Player.name = readLine()!!
        print("Enter their Position : ")
        Player.position = readLine()!!

        return Player.name.isNotEmpty() && Player.position.isNotEmpty()
    }

    fun updatePlayerData(Player : PlayerModel) : Boolean {

        var tempName: String?
        var tempPosition: String?

        if (Player != null) {
            print("Enter the player you would like to swap for [ " + Player.name + " ] : ")
            tempName = readLine()!!
            print("Enter the position you would like to swap from [ " + Player.position + " ] : ")
            tempPosition = readLine()!!

            if (!tempName.isNullOrEmpty() && !tempPosition.isNullOrEmpty()) {
                Player.name = tempName
                Player.position = tempPosition
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}