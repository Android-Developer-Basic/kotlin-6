package ru.otus.homework.mypatterns

class Computer(private val _case: ComputerCase) : ComputerComponent{
    override fun checkCondition(): Condition {
        if (_case.checkCondition() != Condition.GOOD) return Condition.BAD
        if (_case.motherBoard!!.checkCondition() != Condition.GOOD) return Condition.BAD
        if (_case.motherBoard!!.cpu!!.checkCondition() != Condition.GOOD) return Condition.BAD
        if (_case.motherBoard!!.coolingSystem!!.checkCondition() != Condition.GOOD) return Condition.BAD
        if (_case.motherBoard!!.gpu!!.checkCondition() != Condition.GOOD) return Condition.BAD
        if (_case.psu!!.checkCondition() != Condition.GOOD) return Condition.BAD

        return Condition.GOOD
    }

}

interface ComputerBuilder {
    fun setCase(case: ComputerCase)
    fun setMotherBoard(motherBoard: MotherBoard)
    fun setCpu(cpu: Cpu)
    fun setCollingSystem(coolingSystem: CoolingSystem)
    fun setRam(gigabytes: Int)
    fun setGpu(gpu: Gpu)
    fun setPsu(psu: Psu)
    fun build(): Computer
}

abstract class BaseComputerBuilder : ComputerBuilder {
    protected var _case: ComputerCase? = null
    protected var _motherBoard: MotherBoard? = null
    protected var _cpu: Cpu? = null
    protected var _coolingSystem: CoolingSystem? = null
    protected var _ramAmount: Int = 0
    protected var _gpu: Gpu? = null
    protected var _psu: Psu? = null

    override fun build(): Computer {
        if (_case == null) throw Error("Нету корпуса")
        if (_motherBoard == null) throw Error("Нету материнской платы")
        if (_cpu == null) throw Error("Нету процессора")
        if (_coolingSystem == null) throw Error("Нету системы охлаждения")
        if (_ramAmount == 0) throw Error("Нету оперативной памяти")
        if (_gpu == null) throw Error("Нету видеокарты")
        if (_psu == null) throw Error("Нету блока питания")

        _case!!.motherBoard = _motherBoard
        _case!!.motherBoard!!.apply {
            cpu = _cpu
            coolingSystem = _coolingSystem
            ramAmount = _ramAmount
            gpu = _gpu
        }
        _case!!.psu = _psu
        return Computer(_case!!)
    }
}

class ProfessionalComputerBuilder : BaseComputerBuilder() {

    override fun setCase(case: ComputerCase) {
        val condition = case.checkCondition()
        if (condition == Condition.BAD)
            throw Error("Что-то не так с корпусом")
        _case = case
    }

    override fun setMotherBoard(motherBoard: MotherBoard) {
        val condition = motherBoard.checkCondition()
        if (condition == Condition.BAD)
            throw Error("Что-то не так с материнской платой")
        _motherBoard = motherBoard
    }

    override fun setCpu(cpu: Cpu) {
        val condition = cpu.checkCondition()
        if (condition == Condition.BAD)
            throw Error("Что-то не так с процессором")
        _cpu = cpu
    }

    override fun setCollingSystem(coolingSystem: CoolingSystem) {
        val condition = coolingSystem.checkCondition()
        if (condition == Condition.BAD)
            throw Error("Что-то не так с системой охлаждения")
        _coolingSystem = coolingSystem
    }

    override fun setRam(gigabytes: Int) {
        _ramAmount = gigabytes
    }

    override fun setGpu(gpu: Gpu) {
        val condition = gpu.checkCondition()
        if (condition == Condition.BAD)
            throw Error("Что-то не так с видеокартой")
        _gpu = gpu
    }

    override fun setPsu(psu: Psu) {
        val condition = psu.checkCondition()
        if (condition == Condition.BAD)
            throw Error("Что-то не так с блоком питания")
        _psu = psu
    }
}

class DnsComputerBuilder : BaseComputerBuilder() {
    override fun setCase(case: ComputerCase) {
        _case = case
    }

    override fun setMotherBoard(motherBoard: MotherBoard) {
        _motherBoard = motherBoard
    }

    override fun setCpu(cpu: Cpu) {
        _cpu = cpu
    }

    override fun setCollingSystem(coolingSystem: CoolingSystem) {
        _coolingSystem = coolingSystem
    }

    override fun setRam(gigabytes: Int) {
        _ramAmount = gigabytes - 2
    }

    override fun setGpu(gpu: Gpu) {
        _gpu = gpu
    }

    override fun setPsu(psu: Psu) {
        _psu = psu
    }
}


interface ComputerComponent {
    fun checkCondition(): Condition
}

enum class Condition {
    GOOD, BAD
}

sealed class ComputerCase : ComputerComponent{
    var motherBoard: MotherBoard? = null
    var psu: Psu? = null

    class NewCase : ComputerCase() {
        override fun checkCondition(): Condition = Condition.GOOD
    }

    class UsedCase : ComputerCase() {
        override fun checkCondition(): Condition = Condition.BAD
    }
}

sealed class MotherBoard : ComputerComponent {
    var cpu: Cpu? = null
    var ramAmount: Int = 0
    var coolingSystem: CoolingSystem? = null
    var gpu: Gpu? = null

    class AsusMotherBoard : MotherBoard() {
        override fun checkCondition(): Condition = Condition.GOOD
    }

    class AliexpressMotherBoard : MotherBoard() {
        override fun checkCondition(): Condition = Condition.BAD
    }
}

sealed class Cpu : ComputerComponent {
    class AmdCpu : Cpu() {
        override fun checkCondition(): Condition = Condition.GOOD
    }

    class IntelCpu : Cpu() {
        override fun checkCondition(): Condition = Condition.BAD
    }
}

sealed class Gpu : ComputerComponent {
    class NvidiaGpu : Gpu() {
        override fun checkCondition(): Condition = Condition.GOOD
    }

    class AmdCpu : Gpu() {
        override fun checkCondition(): Condition = Condition.BAD
    }
}

sealed class CoolingSystem : ComputerComponent {
    class ExpensiveCoolingSystem : CoolingSystem() {
        override fun checkCondition(): Condition = Condition.GOOD
    }

    class CheapCoolingSystem : CoolingSystem() {
        override fun checkCondition(): Condition = Condition.GOOD
    }
}

sealed class Psu : ComputerComponent {
    class ExpensivePsu : Psu() {
        override fun checkCondition(): Condition = Condition.GOOD
    }

    class CheapPsu : Psu() {
        override fun checkCondition(): Condition = Condition.BAD
    }
}