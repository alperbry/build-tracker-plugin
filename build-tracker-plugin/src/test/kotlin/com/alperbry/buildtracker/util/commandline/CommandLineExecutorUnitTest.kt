package com.alperbry.buildtracker.util.commandline

import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.gradle.api.Project
import org.gradle.process.ExecSpec
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.ByteArrayOutputStream

@ExtendWith(MockKExtension::class)
class CommandLineExecutorUnitTest {

    @RelaxedMockK
    lateinit var project: Project

    @MockK
    lateinit var actionFactory: CommandLineExecSpecActionFactory

    @MockK
    lateinit var action: CommandLineExecSpecAction

    private lateinit var executor: CommandLineExecutor

    @BeforeEach
    fun setup() {
        executor = CommandLineExecutorImpl(project, actionFactory)

        every { actionFactory.create(any(), any(), any()) } returns action
    }

    @Test
    fun `When execute is called, it executes the commands with the help of CommandLineExecSpecActionFactory`() {
        // Given
        val command = "command"
        val option = "-n"

        // When
        executor.execute(command, option)

        // Then
        verify {
            actionFactory.create(any(), command, option)
            project.exec(action)
        }
    }

    @Test
    fun `When CommandLineExecSpecAction#execute is called, it sets given commands and outputStream to the ExecSpec`() {
        // Given
        val execSpec = mockk<ExecSpec>(relaxed = true)
        val command = "command"
        val option = "-n"
        val outputStream = ByteArrayOutputStream()
        val action = CommandLineExecSpecAction(listOf(command, option), outputStream)

        // When
        action.execute(execSpec)

        // Then
        verify {
            execSpec.commandLine = listOf(command, option)
            execSpec.standardOutput = outputStream
        }
    }

    @Test
    fun `When create of CommandLineExecSpecActionFactory is called, it creates expected CommandLineExecSpecAction`() {
        // Given
        val commands = arrayOf<String>()
        val outputStream = ByteArrayOutputStream()
        val factory = CommandLineExecSpecActionFactory()

        // When
        val result = factory.create(outputStream, *commands)

        // Then
        result.shouldBeInstanceOf<CommandLineExecSpecAction>()
    }
}
