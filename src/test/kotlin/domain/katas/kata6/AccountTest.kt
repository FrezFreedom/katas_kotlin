package domain.katas.kata6

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.katas.domain.katas.kata6.Account
import org.katas.domain.katas.kata6.DateProvider
import org.katas.domain.katas.kata6.Transaction

class AccountTest {
    private val mockDateProvider = mockk<DateProvider>() {
        every { now() } returns "01/04/2014"
    }
    private val transactions = mutableListOf<Transaction>()
    private val account = Account(mockDateProvider, transactions)

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
}