package ru.otus.homework.anvol.command.commands

import ru.otus.homework.anvol.command.Bank
import ru.otus.homework.anvol.command.Transaction

class Deposit(private val receiver: Bank, private val amount: Int) : Transaction {
    override fun execute() {
        receiver.deposit(amount)
    }

    override fun toString(): String {
        return "deposited: $$amount"
    }

    override fun getAmount(): Int {
        return amount
    }

    override fun undo() {
        receiver.withdraw(amount)
    }
}