package com.alperbry.buildtracker.util.commandline

import org.gradle.api.Project
import java.io.ByteArrayOutputStream

class CommandLineExecutorImpl(
    private val project: Project
) : CommandLineExecutor {

    override fun execute(vararg cmd: String): String {
        val outputStream = ByteArrayOutputStream()

        project.exec {
            it.setCommandLine(*cmd)
            it.standardOutput = outputStream
        }
        return outputStream.toString()
    }
}
