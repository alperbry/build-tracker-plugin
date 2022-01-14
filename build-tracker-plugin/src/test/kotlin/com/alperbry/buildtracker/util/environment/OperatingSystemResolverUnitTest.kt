package com.alperbry.buildtracker.util.environment

import com.alperbry.buildtracker.data.OperatingSystem
import com.alperbry.buildtracker.system.SystemDataSource
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class OperatingSystemResolverUnitTest {

    @MockK
    lateinit var repository: SystemDataSource

    private lateinit var resolver: OperatingSystemResolver

    @BeforeEach
    fun setup() {
        resolver = OperatingSystemResolverImpl(repository)
    }

    @Test
    fun `When operatingSystemInformation is called, then operating system information is returned in respect to the SystemRepository `() {
        // Given
        every { repository.osName } returns "mac os x"
        every { repository.architecture } returns "x86_64"
        every { repository.version } returns "10.1.2"

        // When
        val result = resolver.operatingSystemInformation()

        // Then
        result.name.shouldBe(OperatingSystem.MAC_OS)
        result.architecture.shouldBe("x86_64")
        result.version.shouldBe("10.1.2")
    }

    @Test
    fun `When the osName belongs to Windows, then OperatingSystem#WINDOWS is added as os name`() {
        // Given
        every { repository.osName } returns "windows 10"
        every { repository.architecture } returns "x86_64"
        every { repository.version } returns "10.1.2"

        // When
        val result = resolver.operatingSystemInformation()

        // Then
        result.name.shouldBe(OperatingSystem.WINDOWS)
    }

    @Test
    fun `When the osName belongs to Linux based systems, then OperatingSystem#LINUX is added as os name`() {
        // Given
        every { repository.osName } returns "linux red hat"
        every { repository.architecture } returns "x86_64"
        every { repository.version } returns "10.1.2"

        // When
        val result = resolver.operatingSystemInformation()

        // Then
        result.name.shouldBe(OperatingSystem.LINUX)
    }

    @Test
    fun `When the osName is not neither mac nor windows nor linux, then OperatingSystem#OTHER is added as name`() {
        // Given
        every { repository.osName } returns "solaris"
        every { repository.architecture } returns "x86_64"
        every { repository.version } returns "10.1.2"

        // When
        val result = resolver.operatingSystemInformation()

        // Then
        result.name.shouldBe(OperatingSystem.OTHER)
    }
}
