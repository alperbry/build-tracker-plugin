package com.alperbry.buildtracker.util.timer

import java.util.concurrent.atomic.AtomicLong

class TimerImpl : Timer {

    private val startTimeInMs = AtomicLong(0)

    private val durationAccumulator = AtomicLong(0)

    override fun start() {
        startTimeInMs.set(System.currentTimeMillis())
    }

    override fun stop() {
        durationAccumulator.getAndAdd(System.currentTimeMillis() - startTimeInMs.get())
    }

    override fun reset() {
        startTimeInMs.set(System.currentTimeMillis())
        durationAccumulator.set(0)
    }

    override fun duration(): Long {
        val acc = durationAccumulator.get()
        return acc + System.currentTimeMillis() - startTimeInMs.get()
    }
}
