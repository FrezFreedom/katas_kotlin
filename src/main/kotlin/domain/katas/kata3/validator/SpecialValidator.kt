package org.katas.domain.katas.kata3.validator

import com.google.inject.Inject
import com.google.inject.Singleton
import org.katas.domain.katas.kata3.dto.PasswordValidationResult

@Singleton
class SpecialValidator @Inject constructor(private val config: ValidatorConfig,
                       private val numberToEnglishConvertor: NumberToEnglishConvertor): Validator {

    companion object {
        private const val ERROR_MESSAGE_FORMAT = "Password must contain at least %s special character"
        private const val SPECIAL_CHARACTERS = "!@#$%^&*()_+-={}[]|\\:;'\"><,.?/`~"
    }

    override fun validate(password: String): PasswordValidationResult {
        if (numberOfSpecialCharacter(password) < config.minNumberOfSpecialCharacter){
            val englishNumber = numberToEnglishConvertor.convert(config.minNumberOfSpecialCharacter)
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