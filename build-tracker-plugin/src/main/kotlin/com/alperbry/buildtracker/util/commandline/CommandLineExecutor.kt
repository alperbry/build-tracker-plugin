package com.alperbry.buildtracker.util.commandline

interface CommandLineExecutor {

    fun execute(vararg cmd: String): String
}
