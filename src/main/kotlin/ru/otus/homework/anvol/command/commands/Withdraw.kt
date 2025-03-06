package ru.otus.homework.anvol.command.commands

import ru.otus.homework.anvol.command.Bank
import ru.otus.homework.anvol.command.Transaction

class Withdraw(private val receiver: Bank, private val amount: Int) : Transaction {
    override fun execute() {
        receiver.withdraw(amount)
    }

    override fun toString(): String {
        return "withdrawn: $$amount"
    }

    override fun getAmount(): Int {
        return amount
    }

    override fun undo() {
        receiver.deposit(amount)
    }
}