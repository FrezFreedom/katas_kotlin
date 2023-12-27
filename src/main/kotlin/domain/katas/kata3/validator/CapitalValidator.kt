package org.katas.domain.katas.kata3.validator

import com.google.inject.Inject
import org.katas.domain.katas.kata3.dto.PasswordValidationResult

class CapitalValidator @Inject constructor(private val minCapitalCharacter: Int = 1,
                       private val numberToEnglishConvertor: NumberToEnglishConvertor): Validator {

    private var errorMessageFormat = "Password must contain at least %s capital letter"

    override fun validate(password: String): PasswordValidationResult {
        if(this.numberOfCapitalCharacters(password) < minCapitalCharacter) {
            val englishNumber = numberToEnglishConvertor.convert(minCapitalCharacter)
            return PasswordValidationResult(false, mutableSetOf(errorMessageFormat.format(englishNumber)))
        }
        return PasswordValidationResult(true, mutableSetOf())
    }

    private fun numberOfCapitalCharacters(string: String): Int {
        var ans = 0
        for(char in string){
            if(char.isUpperCase())
                ans++
        }

        return ans
    }
}