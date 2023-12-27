package org.katas.domain.katas.kata6

import com.google.inject.Guice

fun main() {
    val injector = Guice.createInjector(TransactionModule())
    val account = injector.getInstance(Account::class.java)

    account.deposit(100)
    account.deposit(200)
    account.withdraw(20)
    account.deposit(1000)
    account.printStatement()
}