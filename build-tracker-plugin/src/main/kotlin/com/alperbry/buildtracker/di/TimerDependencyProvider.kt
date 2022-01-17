package com.alperbry.buildtracker.di

import com.alperbry.buildtracker.util.timer.Timer
import com.alperbry.buildtracker.util.timer.TimerImpl

interface TimerDependencyProvider {

    val timer: Timer
}

object TimerDependencyProviderImpl : TimerDependencyProvider {

    override val timer: Timer by lazy {
        TimerImpl()
    }
}
