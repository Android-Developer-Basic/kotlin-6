package ru.otus.homework.patterns

data class City(var name: String?, var area: Int?, var population: Int?, var timeZone: String? ) {
    class Builder {
        private var name: String? = null
        private var area: Int? = null
        private var population: Int? = null
        private var timeZone: String? = null

        fun name(name: String?) = apply { this.name = name }
        fun area(area: Int?) = apply { this.area = area }
        fun population(population: Int?) = apply { this.population = population }
        fun timeZone(timeZone: String?) = apply { this.timeZone = timeZone }
        fun build(): City = City(name, area, population, timeZone)
    }
}

fun main() {
val city = City.Builder()
    .name("Saint-Petersburg")
    .area(1439)
    .population(5601911)
    .timeZone("UTC+3")
    .build()

    print(city)
}
