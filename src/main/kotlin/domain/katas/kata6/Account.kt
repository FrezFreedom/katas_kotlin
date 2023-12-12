package org.katas.domain.katas.kata6

class Account(private val dateProvider: DateProvider,
              private val transactions: MutableList<Transaction>,
              private val printer: Printer) {
    fun deposit(amount: Int) {
        transactions.add(Transaction(dateProvider.now(), amount))
    }
    fun withdraw(amount: Int) {
        transactions.add(Transaction(dateProvider.now(), -amount))
    }
    fun printStatement() {
        var balance = 0
        var statement = ""
        transactions.forEach { transaction: Transaction ->
            balance += transaction.amount
            statement = "${transaction.date} | ${transaction.amount} | $balance\\n" + statement
        }

        statement = "DATE | AMOUNT | BALANCE\\n$statement"

        printer.println(statement)
    }
}