package com.alperbry.buildtracker.system

import io.kotest.matchers.shouldBe
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SystemRepositoryUnitTest {

    private val repository = SystemRepositoryImpl()

    @BeforeEach
    fun setup() {
        System.setProperty("os.name", "Mac OS X")
        System.setProperty("os.arch", "x86_64")
        System.setProperty("os.version", "10.1.2")
    }

    @AfterEach
    fun teardown() {
        System.clearProperty("os.name")
        System.clearProperty("os.arch")
        System.clearProperty("os.version")
    }

    @Test
    fun `When osName is called, then expected result from java System class is returned in lowercase`() {
        // When
        val result = repository.osName

        // Then
        result.shouldBe("mac os x")
    }

    @Test
    fun `When architecture is called, then expected result from java System class is returned`() {
        // When
        val result = repository.architecture

        // Then
        result.shouldBe("x86_64")
    }

    @Test
    fun `When version is called, then expected result from java System class is returned`() {
        // When
        val result = repository.version

        // Then
        result.shouldBe("10.1.2")
    }
}
