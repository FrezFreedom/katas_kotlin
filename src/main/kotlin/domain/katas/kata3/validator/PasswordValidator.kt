package org.katas.domain.katas.kata3.validator

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.Singleton
import org.katas.domain.katas.kata3.dto.PasswordValidationResult

class PasswordValidator(private val validators: List<Validator>) : Validator {
    override fun validate(password: String): PasswordValidationResult {
        val validationResult = PasswordValidationResult(true, mutableSetOf())

        for (validator in validators) {
            validationResult.update(validator.validate(password))
        }

        return validationResult
    }

    class Module : AbstractModule() {
        @Provides
        @Singleton
        fun provide(): PasswordValidator {
            val injector = Guice.createInjector(ValidatorConfig.Module())
            val lengthValidator = injector.getInstance(LengthValidator::class.java)
            val specialValidator = injector.getInstance(SpecialValidator::class.java)
            val numericValidator = injector.getInstance(NumericValidator::class.java)

            return PasswordValidator(
                listOf(
                    lengthValidator,
                    specialValidator,
                    numericValidator
                )
            )
        }
    }
}