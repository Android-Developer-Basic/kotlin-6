package ru.otus.homework.anvol.command

import java.time.LocalDateTime

class Invoker {
    private val transactionHistory: MutableList<TransactionDetails> = mutableListOf()

    fun doTransaction(transaction: Transaction, comment: String? = null) {
        transaction.execute()
        transactionHistory.add(
            TransactionDetails(
                transaction = transaction,
                amount = transaction.getAmount(),
                datetime = LocalDateTime.now(),
                comment = comment
            )
        )
    }

    fun showTransactionHistory() {

        for (transaction in transactionHistory) {
            println(transaction.toString())
        }
    }

    fun undoLastTransaction () {
        if (transactionHistory.isEmpty()) {
            println("Transaction history is empty!")
        }
        else {
            val lastTransaction = transactionHistory.last()

            println(">> Rolling back transaction: $lastTransaction")
            lastTransaction.transaction.undo()

            transactionHistory.removeLast()
        }
    }
}