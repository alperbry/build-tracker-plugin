package com.alperbry.buildtracker.util.commandline

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.process.ExecSpec
import java.io.ByteArrayOutputStream

class CommandLineExecutorImpl(
    private val project: Project,
    private val actionFactory: CommandLineExecSpecActionFactory = CommandLineExecSpecActionFactory()
) : CommandLineExecutor {

    override fun execute(vararg cmd: String): String {
        val outputStream = ByteArrayOutputStream()
        val execSpec = actionFactory.create(cmd, outputStream)
        project.exec(execSpec)

        return outputStream.toString()
    }
}

class CommandLineExecSpecAction(
    private val commands: Array<out String>,
    private val outputStream: ByteArrayOutputStream
) : Action<ExecSpec> {

    override fun execute(execSpec: ExecSpec) {
        execSpec.setCommandLine(commands)
        execSpec.standardOutput = outputStream
    }
}

class CommandLineExecSpecActionFactory {

    fun create(
        commands: Array<out String>,
        outputStream: ByteArrayOutputStream
    ): Action<ExecSpec> = CommandLineExecSpecAction(commands, outputStream)
}
