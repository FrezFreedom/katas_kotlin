package domains.katas.kata3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.katas.domains.katas.kata3.PasswordValidator

class PasswordValidatorTest {

    private val passwordValidator = PasswordValidator()

    @Test
    fun testPasswordValidator(){
        val result = passwordValidator.validate("123456789")

        Assertions.assertEquals(true, result.isValid)
        Assertions.assertEquals("", result.error)
    }

}