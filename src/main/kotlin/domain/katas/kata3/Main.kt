package org.katas.domain.katas.kata3

import com.google.inject.Guice
import org.katas.domain.katas.kata3.validator.PasswordValidator


fun main() {
    val injector = Guice.createInjector(PasswordValidator.Module())
    val passwordValidator = injector.getInstance(PasswordValidator::class.java)

    println(passwordValidator.validate("adsfasdfsbcd$"))
}