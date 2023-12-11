package domain.katas.kata3.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.katas.domain.katas.kata3.validator.NumberToEnglishConvertor

class NumberToEnglishConvertorTest {
    private val numberToEnglishConvertor = NumberToEnglishConvertor()

    @Test
    fun testConvert(){
        val numberEnglish = numberToEnglishConvertor.convert(1)

        Assertions.assertEquals("one", numberEnglish)
    }
}