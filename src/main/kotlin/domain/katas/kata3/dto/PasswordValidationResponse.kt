package org.katas.domain.katas.kata3.dtos

data class PasswordValidationResponse(var isValid: Boolean, var error: String){

    fun update(other: PasswordValidationResponse){
        isValid = isValid and other.isValid


        if(other.error.isNotEmpty()){
            if(error.isNotEmpty())
                error += "\\n"

            error += other.error
        }
    }
}