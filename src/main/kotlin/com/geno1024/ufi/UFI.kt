package com.geno1024.ufi

import java.io.File

interface UFI
{
    val extension: String

    val mime: String

    val rootDs: Any?

    fun deserialize(file: File): UFI = deserialize(file.readText())

    fun deserialize(content: String): UFI

    fun serialize(): ByteArray
}
