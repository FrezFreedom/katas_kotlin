package org.katas.domain.katas.kata3.validator

import org.katas.domain.katas.kata3.dto.PasswordValidationResult


class SpecialValidator(private val minNumberOfSpecialCharacter: Int = 1,
                       private val numberToEnglishConvertor: NumberToEnglishConvertor): Validator {

    companion object {
        private const val ERROR_MESSAGE_FORMAT = "Password must contain at least %s special character"
        private const val SPECIAL_CHARACTERS = "!@#$%^&*()_+-={}[]|\\:;'\"><,.?/`~"
    }

    override fun validate(password: String): PasswordValidationResult {
        if (numberOfSpecialCharacter(password) < minNumberOfSpecialCharacter){
            val englishNumber = numberToEnglishConvertor.convert(minNumberOfSpecialCharacter)
            return PasswordValidationResult(false, mutableSetOf(String.format(ERROR_MESSAGE_FORMAT, englishNumber)))
        }
        return PasswordValidationResult(true, mutableSetOf())
    }


    private fun numberOfSpecialCharacter(string: String): Int {
        var cnt = 0
        SPECIAL_CHARACTERS.forEach {
            if(it in string)
                cnt++
        }

        return cnt
    }
}