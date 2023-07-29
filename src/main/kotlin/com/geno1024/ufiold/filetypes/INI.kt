package com.geno1024.ufiold.filetypes

import com.geno1024.ufiold.UFI
import java.io.File

object INI : UFI
{
    override val extension: String = "ini"

    override val mime: String = ""

    override val rootDs: Struct = Struct(mutableListOf())

    override fun deserialize(file: File): INI = deserialize(file.readText())

    override fun deserialize(content: String): INI
    {
        content.lines().forEach {
            val line = it.trim()
            if (line.startsWith("[") and line.endsWith("]"))
                rootDs.sections.add(Struct.Section(line.drop(1).dropLast(1), mutableMapOf()))
            else
                with(line.split("=")) {
                    rootDs.sections.last().kvp[this[0]] = this[1]
                }
        }
        return this
    }

    override fun serialize(): ByteArray
    {
        TODO("Not yet implemented")
    }

    data class Struct(
        val sections: MutableList<Section>
    ) {
        data class Section(
            val name: String,
            val kvp: MutableMap<String, String>
        )
    }
}
