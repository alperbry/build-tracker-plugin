package com.alperbry.buildtracker.system

interface SystemDataSource {

    val osName: String

    val architecture: String

    val version: String
}
