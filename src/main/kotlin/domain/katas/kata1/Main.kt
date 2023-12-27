package org.katas.domain.katas.kata1

import com.google.inject.Guice

fun main() {
    val injector = Guice.createInjector()
    val fizzBuzzSolver = injector.getInstance(FizzBuzzSolver::class.java)

    println(fizzBuzzSolver.solve(15))
}