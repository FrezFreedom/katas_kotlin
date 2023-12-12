package org.katas.domain.katas.kata6

class Account(private val date: Date,private val accountTransactions: AccountTransactions) {
    fun deposit(amount: Int) {
        accountTransactions.transactions.add(Transaction(date.now(), amount))
    }
    fun withdraw(amount: Int) {}
    fun printStatement() {}
}