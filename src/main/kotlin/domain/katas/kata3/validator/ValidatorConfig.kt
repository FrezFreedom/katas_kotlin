package org.katas.domain.katas.kata3.validator

import com.google.inject.AbstractModule
import com.google.inject.Inject
import com.google.inject.Provides
import com.google.inject.Singleton

class ValidatorConfig(val minPasswordSize: Int, val minNumberOfSpecialCharacter: Int) {
    class Module: AbstractModule() {
        @Provides
        @Singleton
        fun provide() = ValidatorConfig(8, 2)
    }
}