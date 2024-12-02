package ru.otus.homework.mypatterns

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class SingletonTest {
    @Test
    fun objectTest() {
        val instance1 = MySingletonObject
        val instance2 = MySingletonObject
        assertTrue(instance1 === instance2)
    }

    @Test
    fun syncInitTest() {
        val instance1 = MySingletonSyncInit.getInstance()
        val instance2 = MySingletonSyncInit.getInstance()
        assertTrue(instance1 === instance2)
    }

    @Test
    fun lazyInitTest() {
        val instance1 = MySingletonLazyInit.instance
        val instance2 = MySingletonLazyInit.instance
        assertTrue(instance1 === instance2)
    }

    @Test
    fun doubleLockTest() {
        val instance1 = MySingletonDoubleLock.getInstance()
        val instance2 = MySingletonDoubleLock.getInstance()
        assertTrue(instance1 === instance2)
    }

    @Test
    fun enumTest() {
        val instance1 = MySingletonEnum.INSTANCE
        val instance2 = MySingletonEnum.INSTANCE
        assertTrue(instance1 === instance2)
    }
}

