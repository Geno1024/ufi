package com.geno1024.ufi.filetypes

import com.geno1024.ufi.ByteStream
import com.geno1024.ufi.UFI
import java.io.File

object TTF : UFI
{
    /**
     * <a href="https://developer.apple.com/fonts/TrueType-Reference-Manual/RM06/Chap6.html#:~:text=The%20offset%20subtable">A</a>
     */
    data class Struct(
        /**
         * A tag to indicate the OFA scaler to be used to rasterize this font
         */
        var scalerType: ScalerType = ScalerType.TrueTypeWindows,
        /**
         * number of tables
         */
        var numTables: UShort = 0U,
        /**
         * (maximum power of 2 <= numTables)*16
         */
        var searchRange: UShort = 0U,
        /**
         * log2(maximum power of 2 <= numTables)
         */
        var entrySelector: UShort = 0U,
        /**
         * numTables*16-searchRange
         */
        var rangeShift: UShort = 0U,
        var tableDirectories: MutableList<TableDirectory> = mutableListOf()
    ) : UFI.Struct
    {
        /**
         * The values 'true' (0x74727565) and 0x00010000 are recognized by OS X and iOS as referring to TrueType fonts. The value 'typ1' (0x74797031) is recognized as referring to the old style of PostScript font housed in a sfnt wrapper. The value 'OTTO' (0x4F54544F) indicates an OpenType font with PostScript outlines (that is, a 'CFF ' table instead of a 'glyf' table). Other values are not currently supported.
         */
        enum class ScalerType(val type: UInt)
        {
            TrueTypeMac(0x74727565U /* "true" */),
            TrueTypeWindows(0x00010000U),
            OldStylePostScript(0x74797031U /* "typ1" */),
            OpenType(0x4F54544FU /* "OTTO" */),
            ILLEGAL(0x00000000U);

            companion object
            {
                fun fromValue(value: UInt) = values().firstOrNull { it.type == value }?:ILLEGAL
            }
        }

        data class TableDirectory(
            /**
             * 4-byte identifier
             */
            var tag: String,
            /**
             * checksum for this table
             */
            var checksum: UInt,
            /**
             * offset from beginning of sfnt
             */
            var offset: UInt,
            /**
             * length of this table in byte (actual length not padded length)
             */
            var length: UInt
        )
    }

    override val mime: String
        get() = TODO("Not yet implemented")
    override val extension: String = "ttf"
    override val ds: Struct = Struct()

    override fun deserialize(file: File): Struct = Struct().apply {
        val fr = ByteStream(file.readBytes())
        scalerType = Struct.ScalerType.fromValue(fr.readU4BE())
        numTables = fr.readU2BE()
        searchRange = fr.readU2BE()
        entrySelector = fr.readU2BE()
        rangeShift = fr.readU2BE()
        repeat(numTables.toInt()) {
            tableDirectories.add(
                Struct.TableDirectory(
                    fr.readC4(),
                    fr.readU4BE(),
                    fr.readU4BE(),
                    fr.readU4BE()
                )
            )
        }
    }

    override fun serialize(struct: UFI.Struct): ByteArray
    {
        TODO("Not yet implemented")
    }
}
