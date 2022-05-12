package com.alperbry.buildtracker.data.extension

import javax.inject.Inject
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.UnknownDomainObjectException
import org.gradle.api.model.ObjectFactory

open class BuildTrackerExtension @Inject constructor(
    objects: ObjectFactory
) {

    val firebase = objects.newInstance(FirebaseStorageExtension::class.java)

    private var _projectId: String = ""
    internal val projectId: String
        get() = _projectId

    private var _trackingEnabled = true
    internal val trackingEnabled: Boolean
        get() = _trackingEnabled

    private var _outputDir: String = ""
    internal val outputDir: String
        get() = _outputDir

    fun firebase(action: Action<FirebaseStorageExtension>) {
        action.execute(firebase)
    }

    fun projectId(projectId: String) {
        this._projectId = projectId
    }

    fun trackingEnabled(enabled: Boolean) {
        this._trackingEnabled = enabled
    }

    fun outputDir(outputDir: String) {
        this._outputDir = outputDir
    }

    companion object {

        fun Project.buildTrackerExtensions() = try {
            extensions.getByType(BuildTrackerExtension::class.java)
        } catch (e: UnknownDomainObjectException) {
            extensions.create(EXTENSION_NAME, BuildTrackerExtension::class.java)
        }
    }
}

private const val EXTENSION_NAME = "buildTracker"
