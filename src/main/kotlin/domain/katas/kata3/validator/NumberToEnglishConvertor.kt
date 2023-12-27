package org.katas.domain.katas.kata3.validator

import com.google.inject.Singleton

@Singleton
class NumberToEnglishConvertor {

    fun convert(number: Int): String? {

        return when(number) {
            1 -> "one"
            2 -> "two"
            3 -> "three"
            4 -> "four"
            5 -> "five"
            else -> null
        }
    }
}