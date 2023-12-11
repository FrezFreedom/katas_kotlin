package org.katas.domain.katas.kata3.dto

data class PasswordValidationResponse(var isValid: Boolean, var error: String?){

    fun update(other: PasswordValidationResponse){
        isValid = isValid and other.isValid

        error = when {
            error == null && other.error == null -> null
            error != null && other.error != null -> error + "\\n" + other.error
            else -> (error ?: "") + (other.error ?: "")
        }
    }
}