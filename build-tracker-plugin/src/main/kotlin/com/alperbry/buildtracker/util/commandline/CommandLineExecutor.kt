package com.alperbry.buildtracker.util.commandline

interface CommandLineExecutor {

    fun execute(vararg cmd: String): String
}

fun CommandLineExecutor.safeExecute(vararg cmd: String): String {
    return try {
        execute(*cmd)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
