package com.alperbry.buildtracker.system

import com.alperbry.buildtracker.util.commandline.CommandLineExecutor
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class HardwareRepositoryForUnixUnitTest {

    @RelaxedMockK
    lateinit var executor: CommandLineExecutor

    private lateinit var repositoryForUnix: HardwareDataSourceForUnix

    @BeforeEach
    fun setup() {
        repositoryForUnix = HardwareDataSourceForUnix(executor)
    }

    @Test
    fun `When cpuBrand`() {
        // Given
        every { executor.execute(*anyVararg()) } returns "cpu"

        // When
        val result = repositoryForUnix.cpuBrand()

        // Then
        verify {
            executor.execute("sysctl", "-n", "machdep.cpu.brand_string")
        }
        result.shouldBe("cpu")
    }

    @Test
    fun `When environmentIdentifier`() {
        // Given
        every { executor.execute(*anyVararg()) } returns "identifier"

        // When
        val result = repositoryForUnix.environmentIdentifier()

        // Then
        verify {
            executor.execute("sysctl", "-n", "hw.model")
        }
        result.shouldBe("identifier")
    }

    @Test
    fun `When totalPhysicalMemory`() {
        // Given
        every { executor.execute(*anyVararg()) } returns "  12345\n"

        // When
        val result = repositoryForUnix.totalPhysicalMemory()

        // Then
        verify {
            executor.execute("sysctl", "-n", "hw.memsize")
        }
        result.shouldBe(12345L)
    }

    @Test
    fun `When coreCount`() {
        // Given
        every { executor.execute(*anyVararg()) } returns "  5\n"

        // When
        val result = repositoryForUnix.coreCount()

        // Then
        verify {
            executor.execute("sysctl", "-n", "hw.physicalcpu")
        }
        result.shouldBe(5)
    }
}
