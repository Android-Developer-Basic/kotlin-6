package ru.otus.homework.anvol.command.commands

import ru.otus.homework.anvol.command.Bank
import ru.otus.homework.anvol.command.Transaction

class Transfer(
    private val receiver: Bank,
    private val amount: Int,
    private val bankReceiver: String
) : Transaction {
    override fun execute() {
        receiver.transfer(amount, bankReceiver)
    }

    override fun toString(): String {
        return "transfered $$amount to $bankReceiver"
    }

    override fun getAmount(): Int {
        return amount
    }

    override fun undo() {
        receiver.transfer(-amount, receiver.name)
    }
}