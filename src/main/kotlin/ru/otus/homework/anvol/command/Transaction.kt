package ru.otus.homework.anvol.command

import java.time.LocalDateTime

interface Transaction {
    fun execute()
    fun getAmount(): Int
    fun undo()
}

data class TransactionDetails(
    val transaction: Transaction,
    val datetime: LocalDateTime,
    val amount: Int,
    val comment: String? = null
)