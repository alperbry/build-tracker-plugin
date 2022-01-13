package com.alperbry.buildtracker.util.timer

interface Timer {

    fun start()

    fun stop()

    fun reset()

    fun duration(): Long
}
