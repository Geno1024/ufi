package com.geno1024.ufi.filetypes

import com.geno1024.ufi.UFI
import java.io.File
import java.util.regex.Pattern

object INI : UFI
{
    data class Struct(
        var sections: MutableList<Section> = mutableListOf()
    ) : UFI.Struct
    {
        data class Section(
            var name: String = "",
            var kvp: MutableMap<String, String> = mutableMapOf()
        ) {
        }
    }

    override val mime: String
        get() = TODO("Not yet implemented")
    override val extension: String = "ini"
    override val ds: Struct
        get() = TODO("Not yet implemented")

    override fun deserialize(file: File): Struct = Struct().apply {
        file.readLines().filter { line ->
            line.isNotBlank()
        }.forEach {
            val line = it.trim()
            if (line.startsWith("[") and line.endsWith("]"))
                ds.sections.add(Struct.Section(line.substring(1, line.length - 2), mutableMapOf()))
            else
                with (line.split(Pattern.compile("="), 2)) {
                    ds.sections.last().kvp[this[0]] = this[1]
                }
        }
    }

    override fun serialize(struct: UFI.Struct): ByteArray
    {
        TODO("Not yet implemented")
    }
}
