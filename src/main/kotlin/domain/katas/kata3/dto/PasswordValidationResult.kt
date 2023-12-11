package org.katas.domain.katas.kata3.dto

data class PasswordValidationResult(var isValid: Boolean, var error: MutableSet<String>){

    fun update(other: PasswordValidationResult){
        isValid = isValid and other.isValid

        error.addAll(other.error)
    }
}