package com.alperbry.buildtracker.util.timestamp

import kotlinx.datetime.Clock

interface TimestampGenerator {

    fun now(): String
}

class TimestampGeneratorImpl(
    private val clock: Clock = Clock.System
) : TimestampGenerator {

    override fun now(): String {
        return clock.now().toString()
    }
}
