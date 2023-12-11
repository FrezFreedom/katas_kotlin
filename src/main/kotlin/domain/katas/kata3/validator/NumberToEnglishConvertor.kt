package org.katas.domain.katas.kata3.validator

class NumberToEnglishConvertor {

    fun convert(number: Int): String? {
        if(number == 1)
            return "one"

        return null
    }
}