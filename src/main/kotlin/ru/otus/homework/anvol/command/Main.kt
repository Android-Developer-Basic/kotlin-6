package ru.otus.homework.anvol.command

import ru.otus.homework.anvol.command.commands.Deposit
import ru.otus.homework.anvol.command.commands.Transfer
import ru.otus.homework.anvol.command.commands.Withdraw

fun main() {
    val someBank = Bank("MyBank")
    val invoker = Invoker()

    someBank.showStoredAmount()

    val deposit = Deposit(someBank, 322)
    val withdraw = Withdraw(someBank, 100)
    val transfer = Transfer(someBank, 19, "SberBank")

    invoker.doTransaction(deposit)
    invoker.doTransaction(withdraw)
    invoker.undoLastTransaction()

    invoker.doTransaction(transfer, "My second account")


    println(">> showing transaction history:")

    invoker.showTransactionHistory()
}