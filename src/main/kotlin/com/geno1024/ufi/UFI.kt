package com.geno1024.ufi

import java.io.File

interface UFI
{
    interface Struct

    val mime: String
    val extension: String
    val ds: Struct

    fun deserialize(file: File): Struct

    fun serialize(struct: Struct): ByteArray
}
