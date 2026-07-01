package ru.otus.homework.homework

import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.Assertions.*

class ProcessListTest {
    @Test
    fun testSkipThreeAndPrint() {
        val output = ByteArrayOutputStream()
        System.setOut(PrintStream(output))

        skipThreeAndPrint(listOf(1, 2, 3, 4))

        val expected = "Processing 1\r\nProcessing 2\r\nProcessing 4\r\n"
        assertEquals(expected, output.toString())
    }
}
