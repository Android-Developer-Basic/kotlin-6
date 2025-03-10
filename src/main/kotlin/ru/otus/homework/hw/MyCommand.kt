package ru.otus.homework.hw

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    val player = Player("MyFilm")
    val playerInvoker = PlayerInvoker()
    launch {
        playerInvoker.pressButtonCommand(PlayerPlayCommand(player))
    }

    delay(4000L)
    launch {
        playerInvoker.pressButtonCommand(PlayerStopCommand(player))
    }

    println("main end")
}


interface Command {
    suspend fun pressButton()
    suspend fun releaseButton()
}


class Player(var filmName: String)  {
    var isPlaying : Boolean = false

    suspend fun play()  {
        isPlaying = true
        var counter  = 0
            while(isPlaying) {
                println("play film $filmName ${counter++}")
                delay(1000L)
            }
        }

    fun stop()  {
            isPlaying = false
            println("stop film $filmName")

    }

    fun pause() {
        isPlaying = false
        println("pause film $filmName")
    }

}


class PlayerPlayCommand(private val receiver: Player) : Command {

    override suspend fun pressButton() {
        receiver.play()
    }

    override suspend fun releaseButton() {
        receiver.pause()
    }
}


class PlayerStopCommand(private val receiver: Player) : Command {

    override suspend fun pressButton() {
        receiver.stop()
    }

    override suspend fun releaseButton(): Unit = coroutineScope {
        receiver.play()
    }
}


class PlayerPauseCommand(private val receiver: Player) : Command {

    override suspend fun pressButton() {
        receiver.pause()
    }

    override suspend fun releaseButton() {
        receiver.play()
    }
}


class PlayerInvoker {

    suspend fun pressButtonCommand(command: Command) {
        command.pressButton()
    }

    suspend fun releaseButtonCommand(command: Command) {
        command.releaseButton()
    }

}