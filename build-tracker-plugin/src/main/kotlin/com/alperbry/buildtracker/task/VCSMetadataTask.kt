package com.alperbry.buildtracker.task

import com.alperbry.buildtracker.cache.BuildInformationCache
import com.alperbry.buildtracker.data.VersionControlMetadata
import com.alperbry.buildtracker.di.VCSDependencyProvider
import com.alperbry.buildtracker.util.vcs.VCSInfoResolver
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class VCSMetadataTask @Inject constructor(
    private val provider: VCSDependencyProvider,
    private val cache: BuildInformationCache<*>
) : DefaultTask() {

    private val resolver: VCSInfoResolver
        get() = provider.informationResolver()

    @TaskAction
    fun execute() {
        cache.vcsData = VersionControlMetadata(
            currentStateIdentifier = resolver.stateIdentifier()
        )
    }
}
