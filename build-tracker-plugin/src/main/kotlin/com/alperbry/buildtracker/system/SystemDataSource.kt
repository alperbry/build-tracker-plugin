package com.alperbry.buildtracker.system

/**
 * Source for _Java_ System properties.
 */
interface SystemDataSource {

    /**
     * Name of the underlying Operating System.
     */
    val osName: String

    /**
     * Architecture of the computer, e.g. x86_64.
     */
    val architecture: String

    /**
     * Version of the underlying operating system, e.g. 10.1.2.
     */
    val version: String
}
