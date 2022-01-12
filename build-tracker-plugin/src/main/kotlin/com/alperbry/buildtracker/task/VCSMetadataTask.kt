package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.data.VersionControlMetadata
import com.alperbry.buildtracker.di.VCSDependencyProvider
import com.alperbry.buildtracker.util.git.GitResolver
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class VCSMetadataTask @Inject constructor(
    private val provider: VCSDependencyProvider
) : DefaultTask() {

    private val resolver: GitResolver
        get() = provider.gitResolver()

    @TaskAction
    fun execute() {
        val vcsMetadata = VersionControlMetadata(
            currentStateIdentifier = resolver.revision()
        )
        println(vcsMetadata)
    }
}
