package com.alperbry.buildtracker.system

interface SystemRepository {

    val osName: String

    val architecture: String

    val version: String
}
