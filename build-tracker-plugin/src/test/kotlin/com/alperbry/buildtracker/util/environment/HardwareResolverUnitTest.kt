package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.system.HardwareDataSource
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class HardwareResolverUnitTest {

    @MockK
    lateinit var repository: HardwareDataSource

    private lateinit var resolver: HardwareResolver

    @BeforeEach
    fun setup() {
        resolver = HardwareResolverImpl(repository)
    }

    @Test
    fun `When hardwareInfo is called, then HardwareInformation object filled with expected values are returned`() {
        // Given
        every { repository.cpuBrand() } returns "intel"
        every { repository.totalPhysicalMemory() } returns 17220763648L
        every { repository.environmentIdentifier() } returns "MacBook"
        every { repository.coreCount() } returns 6

        // When
        val info = resolver.hardwareInfo()

        // Then
        info.cpuModelName.shouldBe("intel")
        info.physicalMemoryInMb.shouldBe(16423)
        info.environmentIdentifier.shouldBe("MacBook")
        info.coreCount.shouldBe(6)
    }

    @Test
    fun `When hardwareInfo is called and repository cannot return physical memory value, then HardwareInformation object filled -1 as totalMemoryInMb`() {
        // Given
        every { repository.cpuBrand() } returns "intel"
        every { repository.totalPhysicalMemory() } returns null
        every { repository.environmentIdentifier() } returns "MacBook"
        every { repository.coreCount() } returns 6

        // When
        val info = resolver.hardwareInfo()

        // Then
        info.physicalMemoryInMb.shouldBe(-1)
    }
}
