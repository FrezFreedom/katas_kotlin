package org.katas.domain.katas.kata6

import com.google.inject.*

class TransactionModule: AbstractModule() {
    override fun configure() {
        bind(DateProvider::class.java).to(RandomTimeDataProvider::class.java)
        bind(Printer::class.java).to(ConsolePrinter::class.java)
        bind(object : TypeLiteral<MutableList<Transaction>>() {}).toProvider(TransactionListProvider::class.java)
    }
}

class TransactionListProvider : Provider<MutableList<Transaction>> {

    @Provides
    override fun get(): MutableList<Transaction> =
        mutableListOf()

}