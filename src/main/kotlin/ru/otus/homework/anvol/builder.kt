package ru.otus.homework.anvol

fun main() {
    val myComputer = Builder("Вася")
        .addMotherboard("MSI")
        .addCpu("i7")
        .addCpuCooler("Noctua")
        .addRam(listOf("16", "16", "16", "16"))
        .addCase("Deepcool")
        .addStorage(listOf("1TB", "1TB"))
        .addPeripherals(
            listOf(
                "Монитор Acer",
                "Мышь Razer",
                "Клавиатура Razer",
                "Колонки Defender"
            )
        )
        .installSoftware(listOf("Windows 11"))
        .build()

    val dummyComputer = Builder("тестовая сборка")
        .addCase("adidas")
        .addExtras(listOf("Кардридер", "Blu-ray drive"))
        .addSoundCard("SoundBlaster 3.0")
        .build()

    println(myComputer)
    println(dummyComputer)
}

class Builder(val builderName: String) {
    private var cpu: String = "Без CPU"
    private var motherboard: String = "Без материнки"
    private var psu: String = "Без блока питания"
    private var case: String = "Без корпуса"
    private var cpuCooler: String = "Без кулера"
    private var ram: List<String> = emptyList()
    private var storage: List<String> = emptyList()
    private var soundCard: String = "Без звуковой карты"
    private var extras: List<String> = emptyList()
    private var peripherals: List<String> = emptyList()
    private var software: List<String> = emptyList()

    fun addMotherboard(motherboard: String) = apply { this.motherboard = motherboard }
    fun addCpu(cpu: String) = apply { this.cpu = cpu }
    fun addCpuCooler(cpuCooler: String) = apply { this.cpuCooler = cpuCooler }
    fun addRam(ram: List<String>) = apply { this.ram = ram }
    fun addCase(case: String) = apply { this.case = case }
    fun addStorage(storage: List<String>) = apply { this.storage = storage }
    fun addSoundCard(soundCard: String) = apply { this.soundCard = soundCard }
    fun addExtras(extras: List<String>) = apply { this.extras = extras }
    fun addPeripherals(peripherals: List<String>) = apply { this.peripherals = peripherals }
    fun installSoftware(software: List<String>) = apply { this.software = software }

    fun build(): Computer {
        return Computer(
            builderName,
            cpu,
            motherboard,
            psu,
            case,
            cpuCooler,
            ram,
            storage,
            soundCard,
            extras,
            peripherals,
            software
        )
    }

}

data class Computer(
    val builderName: String,
    val cpu: String,
    val motherboard: String,
    val psu: String,
    val case: String,
    val cpuCooler: String,
    val ram: List<String>,
    val storage: List<String>,
    val soundCard: String,
    val extras: List<String>,
    val peripherals: List<String>,
    val software: List<String>
)