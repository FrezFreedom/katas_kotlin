package org.katas.domain.katas.kata1

import com.google.inject.Singleton

@Singleton
class FizzBuzzSolver {

    fun solve(number: Int): String {

        return when {
            number % 15 == 0 -> "FizzBuzz"
            number % 3 == 0 -> "Fizz"
            number % 5 == 0 -> "Buzz"
            else -> number.toString()
        }
    }

}