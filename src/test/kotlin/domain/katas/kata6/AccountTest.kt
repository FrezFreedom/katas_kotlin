package domain.katas.kata6

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.katas.domain.katas.kata6.Account
import org.katas.domain.katas.kata6.AccountTransactions
import org.katas.domain.katas.kata6.Date
import org.katas.domain.katas.kata6.Transaction

class AccountTest {
    private val mockDate = mockk<Date>() {
        every { now() } returns "01/04/2014"
    }
    private val accountTransactions = AccountTransactions()
    private val account = Account(mockDate, accountTransactions)

    @Test
    fun testDeposit(){
        val expectedValue = AccountTransactions(mutableListOf(Transaction("01/04/2014", 100)))

        account.deposit(100)

        Assertions.assertEquals(expectedValue, accountTransactions)
    }

    @Test
    fun testWithdraw(){
        val expectedValue = AccountTransactions(mutableListOf(Transaction("01/04/2014", -100)))

        account.withdraw(100)

        Assertions.assertEquals(expectedValue, accountTransactions)
    }
}