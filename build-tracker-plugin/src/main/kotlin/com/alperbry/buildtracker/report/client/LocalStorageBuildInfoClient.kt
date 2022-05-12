package com.alperbry.buildtracker.report.client

import com.alperbry.buildtracker.data.output.BuildInformationReportDTO
import com.alperbry.buildtracker.util.file.safeChild
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gradle.api.Project
import java.io.File

interface LocalStorageBuildInfoClient {

    fun newBuild(
        buildInformationReport: BuildInformationReportDTO,
        directory: File? = null
    )
}

class LocalStorageBuildInfoClientImpl(
    private val project: Project
) : LocalStorageBuildInfoClient {

    override fun newBuild(
        buildInformationReport: BuildInformationReportDTO,
        directory: File?
    ) {
        val text = Json.encodeToString(buildInformationReport)

        val directoryOfReport = directory?.let {
            directory.safeChild("$FILE_NAME-${buildInformationReport.timestamp}$FILE_SUFFIX")
        } ?: project.buildDir.safeChild("$FILE_DIRECTORY$FILE_NAME$FILE_SUFFIX")

        directoryOfReport
            .bufferedWriter().use {
                it.write(text)
            }
    }
}

private const val FILE_DIRECTORY = "/reports/build-tracker/"
private const val FILE_NAME = "report"
private const val FILE_SUFFIX = ".json"
