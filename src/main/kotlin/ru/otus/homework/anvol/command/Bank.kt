package ru.otus.homework.anvol.command

class Bank(val name: String) {
    private var storedAmount: Int = 0

    fun deposit(amount: Int) {
        storedAmount += amount
        println("Deposited: $$amount")
        showStoredAmount()
    }

    fun withdraw(amount: Int) {
        storedAmount -= amount
        println("Withdrawn: $$amount")
        showStoredAmount()
    }

    fun transfer(amount: Int, bankReceiver: String) {
        storedAmount -= amount
        println("Transferred: $$amount to $bankReceiver")
        showStoredAmount()
    }

    fun showStoredAmount() {
        println("> Balance: $storedAmount\n")
    }
}