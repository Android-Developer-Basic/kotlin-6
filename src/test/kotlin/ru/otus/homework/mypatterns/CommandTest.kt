package ru.otus.homework.mypatterns

import kotlin.test.Test
import kotlin.test.assertEquals

class CommandTest {
    @Test
    fun moveForward(){
        val tank = Tank()
        val controlPanel = TankControlPanel(tank)

        controlPanel.refuelButtonClick()
        controlPanel.joystickUp()

        assertEquals(1, tank.location.y)
    }

    @Test
    fun moveBack(){
        val tank = Tank()
        val controlPanel = TankControlPanel(tank)

        controlPanel.refuelButtonClick()
        controlPanel.joystickDown()

        assertEquals(-1, tank.location.y)
    }

    @Test
    fun turnLeft(){
        val tank = Tank()
        val controlPanel = TankControlPanel(tank)

        controlPanel.refuelButtonClick()
        controlPanel.joystickLeft()

        assertEquals(Direction.LEFT, tank.direction)
    }

    @Test
    fun turnRight(){
        val tank = Tank()
        val controlPanel = TankControlPanel(tank)

        controlPanel.refuelButtonClick()
        controlPanel.joystickRight()

        assertEquals(Direction.RIGHT, tank.direction)
    }

    @Test
    fun fire(){
        val tank = Tank()
        val controlPanel = TankControlPanel(tank)

        controlPanel.rearmButtonClick()
        controlPanel.fireButtonClick()

        assertEquals(9, tank.ammoAmount)
    }

    @Test
    fun fireWithoutAmmo(){
        val tank = Tank()
        val controlPanel = TankControlPanel(tank)

        controlPanel.fireButtonClick()

        assertEquals(0, tank.ammoAmount)
    }
}