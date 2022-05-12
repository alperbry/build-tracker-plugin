package com.alperbry.buildtracker.report.client

import com.alperbry.buildtracker.data.output.BuildInformationReportDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

interface LocalStorageBuildInfoClient {

    fun newBuild(
        buildInformationReport: BuildInformationReportDTO,
        directory: File
    )
}

class LocalStorageBuildInfoClientImpl : LocalStorageBuildInfoClient {

    override fun newBuild(
        buildInformationReport: BuildInformationReportDTO,
        directory: File
    ) {
        val text = Json.encodeToString(buildInformationReport)

        File(directory, "report.json").also { // todo make it configurable
            it.parentFile.mkdirs()
        }.bufferedWriter().use {
            it.write(text)
        }
    }
}
