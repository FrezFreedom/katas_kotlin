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

        val result2 = passwordValidator.validate("1234567")

        Assertions.assertEquals(false, result2.isValid)
        Assertions.assertEquals("Password must be at least 8 characters", result2.error)
    }

}