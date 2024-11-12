package ru.otus.homework.patterns

import ru.otus.homework.Address

class AddressBuilder {
    private var street1 = ""
    private var street2 = ""
    private var city = ""
    private var country = ""
    private var index = ""

    fun street1(street: String) = apply { this.street1 = street }
    fun street2(street: String) = apply { this.street2 = street }
    fun city(city: String) = apply { this.city = city }
    fun country(country: String) = apply { this.country = country }
    fun index(index: String) = apply { this.index = index }

    fun build() = Address().also {
        it.street1 = street1
        it.street2 = street2
        it.city = city
        it.country = country
        it.index = index
    }
}

fun main() {
    val address = AddressBuilder()
        .street1("Ul. Lenina, d. 10")
        .street2("Kv. 10")
        .city("Borok")
        .country("Russia")
        .index("12345")
        .build()

    println("Address: ${address.street1}, ${address.street2}, ${address.city}, ${address.country}, ${address.index}")
}
