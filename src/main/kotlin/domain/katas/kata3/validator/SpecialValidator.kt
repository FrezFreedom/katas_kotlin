package org.katas.domain.katas.kata3.validator

import org.katas.domain.katas.kata3.dto.PasswordValidationResponse

class SpecialValidator(private val minNumberOfSpecialCharacter: Int = 1,
                       private val numberToEnglishConvertor: NumberToEnglishConvertor) {

    companion object {
        const val ERROR_MESSAGE_FORMAT = "Password must contain at least %s special character"
        const val SPECIAL_CHARACTERS = "!@#$%^&*()_+-={}[]|\\:;'\"><,.?/`~"
    }

    fun validate(password: String): PasswordValidationResponse {
        if (numberOfSpecialCharacter(password) < minNumberOfSpecialCharacter){
            val englishNumber = numberToEnglishConvertor.convert(minNumberOfSpecialCharacter)
            return PasswordValidationResponse(false, String.format(ERROR_MESSAGE_FORMAT, englishNumber))
        }
        return PasswordValidationResponse(true, null)
    }


    private fun numberOfSpecialCharacter(string: String): Int {
        var cnt = 0
        for(char in SPECIAL_CHARACTERS){
            if(char in string)
                cnt++
        }

        return cnt
    }
}