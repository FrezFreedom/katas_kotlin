package org.katas.domain.katas.kata4

import com.google.inject.Guice

fun main(){
    val injector = Guice.createInjector()
    val citySearcher = injector.getInstance(CitySearcher::class.java)

    println( citySearcher.search("Va") )
}