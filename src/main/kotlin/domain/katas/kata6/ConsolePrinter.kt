package org.katas.domain.katas.kata6

import com.google.inject.Singleton

@Singleton
class ConsolePrinter: Printer {
    override fun println(text: String) {
        kotlin.io.println(text)
    }
}