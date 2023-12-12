package org.katas.domain.katas.kata6

class Account(private val dateProvider: DateProvider, private val transactions: MutableList<Transaction>) {
    fun deposit(amount: Int) {
        transactions.add(Transaction(dateProvider.now(), amount))
    }
    fun withdraw(amount: Int) {
        transactions.add(Transaction(dateProvider.now(), -amount))
    }
    fun printStatement() {
    }
}