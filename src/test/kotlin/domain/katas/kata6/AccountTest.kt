package domain.katas.kata6

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.katas.domain.katas.kata6.Account
import org.katas.domain.katas.kata6.DateProvider
import org.katas.domain.katas.kata6.Printer
import org.katas.domain.katas.kata6.Transaction

class AccountTest {
    private val mockDateProvider = mockk<DateProvider>() {
        every { now() } returns "01/04/2014"
    }
    private val transactions = mutableListOf<Transaction>()
    private val mockPrinter = mockk<Printer>()
    private val account = Account(mockDateProvider, transactions, mockPrinter)

    @Test
    fun testDeposit(){
        val expectedValue = mutableListOf(Transaction("01/04/2014", 100))

        account.deposit(100)

        Assertions.assertEquals(expectedValue, transactions)
    }

    @Test
    fun testWithdraw(){
        val expectedValue = mutableListOf(Transaction("01/04/2014", -100))

        account.withdraw(100)

        Assertions.assertEquals(expectedValue, transactions)
    }

    @Test
    fun testPrintStatement() {
        val expectedValue =
            """DATE | AMOUNT | BALANCE\n01/04/2014 | 200 | 250\n01/04/2014 | -50 | 50\n01/04/2014 | 100 | 100\n"""
        every { mockPrinter.println(any()) } returns Unit

        account.deposit(100)
        account.withdraw(50)
        account.deposit(200)
        account.printStatement()

        verify { mockPrinter.println(expectedValue) }
    }
}