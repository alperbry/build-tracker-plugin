package com.alperbry.buildtracker.data.output

import kotlinx.serialization.Serializable

@Serializable
data class OSMetadataDTO(
    val name: String,
    val architecture: String,
    val version: String
)
