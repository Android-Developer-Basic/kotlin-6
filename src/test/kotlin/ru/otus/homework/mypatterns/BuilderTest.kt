package ru.otus.homework.mypatterns

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class BuilderTest {
    @Test
    fun testProfessionalGood() {
        val professionalBuilder: ComputerBuilder = ProfessionalComputerBuilder()

        professionalBuilder.setCase(ComputerCase.NewCase())
        professionalBuilder.setMotherBoard(MotherBoard.AsusMotherBoard())
        professionalBuilder.setCpu(Cpu.AmdCpu())
        professionalBuilder.setCollingSystem(CoolingSystem.ExpensiveCoolingSystem())
        professionalBuilder.setRam(16)
        professionalBuilder.setGpu(Gpu.NvidiaGpu())
        professionalBuilder.setPsu(Psu.ExpensivePsu())
        val computer = professionalBuilder.build()

        assertEquals(Condition.GOOD, computer.checkCondition())
    }

    @Test
    fun testProfessionalBad() {
        val professionalBuilder: ComputerBuilder = ProfessionalComputerBuilder()

        professionalBuilder.setCase(ComputerCase.NewCase())
        professionalBuilder.setCpu(Cpu.AmdCpu())
        professionalBuilder.setCollingSystem(CoolingSystem.ExpensiveCoolingSystem())
        professionalBuilder.setRam(16)
        professionalBuilder.setGpu(Gpu.NvidiaGpu())
        professionalBuilder.setPsu(Psu.ExpensivePsu())

        assertFails { professionalBuilder.setMotherBoard(MotherBoard.AliexpressMotherBoard()) } // Bad component
    }

    @Test
    fun testDnsGood() {
        val professionalBuilder: ComputerBuilder = DnsComputerBuilder()

        professionalBuilder.setCase(ComputerCase.NewCase())
        professionalBuilder.setMotherBoard(MotherBoard.AsusMotherBoard())
        professionalBuilder.setCpu(Cpu.AmdCpu())
        professionalBuilder.setCollingSystem(CoolingSystem.ExpensiveCoolingSystem())
        professionalBuilder.setRam(16)
        professionalBuilder.setGpu(Gpu.NvidiaGpu())
        professionalBuilder.setPsu(Psu.ExpensivePsu())
        val computer = professionalBuilder.build()

        assertEquals(Condition.GOOD, computer.checkCondition())
    }

    @Test
    fun testDnsBad() {
        val professionalBuilder: ComputerBuilder = DnsComputerBuilder()

        professionalBuilder.setCase(ComputerCase.NewCase())
        professionalBuilder.setMotherBoard(MotherBoard.AliexpressMotherBoard()) // Bad component
        professionalBuilder.setCpu(Cpu.AmdCpu())
        professionalBuilder.setCollingSystem(CoolingSystem.ExpensiveCoolingSystem())
        professionalBuilder.setRam(16)
        professionalBuilder.setGpu(Gpu.NvidiaGpu())
        professionalBuilder.setPsu(Psu.ExpensivePsu())
        val computer = professionalBuilder.build()

        assertEquals(Condition.BAD, computer.checkCondition())
    }
}