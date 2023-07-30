package com.geno1024.ufi.filetypes

import com.geno1024.ufi.ByteStream
import com.geno1024.ufi.UFI
import java.io.File

object TTF : UFI
{
    override val mime: String
        get() = TODO("Not yet implemented")
    override val extension: String = "ttf"
    override val ds: Struct
        get() = TODO("Not yet implemented")

    data class Struct(
        var fontDirectory: FontDirectory = FontDirectory(),
        var tables: MutableMap<String, ITable> = mutableMapOf()
    ) : UFI.Struct
    {
        data class FontDirectory(
            var offsetSubtable: OffsetSubtable = OffsetSubtable(),
            var tableDirectories: MutableList<TableDirectoryEntry> = mutableListOf()
        )
        {
            data class OffsetSubtable(
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
            )
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
                        fun from(type: UInt) = values().firstOrNull { it.type == type }?:ILLEGAL
                    }
                }
            }

            data class TableDirectoryEntry(
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

        interface ITable

        data class CommonTable(var bytes: List<Byte>) : ITable

        /**
         * [Font Header Table](https://developer.apple.com/fonts/TrueType-Reference-Manual/RM06/Chap6head.html)
         */
        data class HeadTable(
            /**
             * 0x00010000 if (version 1.0)
             */
            var version: UInt = 0x00010000U,
            /**
             * set by font manufacturer
             */
            var fontRevision: UInt = 0U,
            /**
             * To compute: set it to 0, calculate the checksum for the 'head' table and put it in the table directory, sum the entire font as a uint32_t, then store 0xB1B0AFBA - sum. (The checksum for the 'head' table will be wrong as a result. That is OK; do not reset it.)
             */
            var checkSumAdjustment: UInt = 0U,
            /**
             * set to 0x5F0F3CF5
             */
            var magicNumber: UInt = 0U,
            /**
             * bit 0 - y value of 0 specifies baseline
             *
             * bit 1 - x position of left most black bit is LSB
             *
             * bit 2 - scaled point size and actual point size will differ (i.e. 24 point glyph differs from 12 point glyph scaled by factor of 2)
             *
             * bit 3 - use integer scaling instead of fractional
             *
             * bit 4 - (used by the Microsoft implementation of the TrueType scaler)
             *
             * bit 5 - This bit should be set in fonts that are intended to e laid out vertically, and in which the glyphs have been drawn such that an x-coordinate of 0 corresponds to the desired vertical baseline.
             *
             * bit 6 - This bit must be set to zero.
             *
             * bit 7 - This bit should be set if the font requires layout for correct linguistic rendering (e.g. Arabic fonts).
             *
             * bit 8 - This bit should be set for an AAT font which has one or more metamorphosis effects designated as happening by default.
             *
             * bit 9 - This bit should be set if the font contains any strong right-to-left glyphs.
             *
             * bit 10 - This bit should be set if the font contains Indic-style rearrangement effects.
             *
             * bits 11-13 - Defined by Adobe.
             *
             * bit 14 - This bit should be set if the glyphs in the font are simply generic symbols for code point ranges, such as for a last resort font.
             */
            var flags: Flags = Flags(),
            /**
             * range from 64 to 16384
             */
            var unitsPerEm: UShort = 64U,
            /**
             * international date
             */
            var created: ULong = 0U,
            /**
             * international date
             */
            var modified: ULong = 0U,
            /**
             * for all glyph bounding boxes
             */
            var xMin: Short = 0,
            /**
             * for all glyph bounding boxes
             */
            var yMin: Short = 0,
            /**
             * for all glyph bounding boxes
             */
            var xMax: Short = 0,
            /**
             * for all glyph bounding boxes
             */
            var yMax: Short = 0,
            /**
             * bit 0 bold
             *
             * bit 1 italic
             *
             * bit 2 underline
             *
             * bit 3 outline
             *
             * bit 4 shadow
             *
             * bit 5 condensed (narrow)
             *
             * bit 6 extended
             */
            var macStyle: MacStyle = MacStyle(),
            /**
             * smallest readable size in pixels
             */
            var lowestRecPPEM: UShort = 0U,
            /**
             * 0 Mixed directional glyphs
             *
             * 1 Only strongly left to right glyphs
             *
             * 2 Like 1 but also contains neutrals
             *
             * -1 Only strongly right to left glyphs
             *
             * -2 Like -1 but also contains neutrals
             */
            var fontDirectionHint: FontDirectionHint = FontDirectionHint.StrongLTR,
            /**
             * 0 for short offsets, 1 for long
             */
            var indexToLocFormat: IndexToLocFormat = IndexToLocFormat.ShortOffset,
            /**
             * 0 for current format
             */
            var glyphDataFormat: Short = 0
        ) : ITable
        {
            data class Flags(
                /**
                 * bit 0 - y value of 0 specifies baseline
                 */
                var yValueOf0SpecifiesBaseline: Boolean = false, /* 0 */
                /**
                 * bit 1 - x position of left most black bit is LSB
                 */
                var xPositionOfLeftMostBlackBitIsLsb: Boolean = false, /* 1 */
                /**
                 * bit 2 - scaled point size and actual point size will differ (i.e. 24 point glyph differs from 12 point glyph scaled by factor of 2)
                 */
                var scaledPointSizeAndActualPointSizeDiffer: Boolean = false, /* 2 */
                /**
                 * bit 3 - use integer scaling instead of fractional
                 */
                var useIntegerScalingInsteadOfFractional: Boolean = false, /* 3 */
//                var microsoftImplement: Boolean = false, /* 4 */
                /**
                 * bit 5 - This bit should be set in fonts that are intended to e laid out vertically, and in which the glyphs have been drawn such that an x-coordinate of 0 corresponds to the desired vertical baseline.
                 */
                var layoutVertically: Boolean = false, /* 5 */
                /**
                 * bit 7 - This bit should be set if the font requires layout for correct linguistic rendering (e.g. Arabic fonts).
                 */
                var requiresLayoutForCorrectLinguisticRendering: Boolean = false, /* 7 */
                /**
                 * bit 8 - This bit should be set for an AAT font which has one or more metamorphosis effects designated as happening by default.
                 */
                var aatFontWhichHaveMetamorphosisEffects: Boolean = false, /* 8 */
                /**
                 * bit 9 - This bit should be set if the font contains any strong right-to-left glyphs.
                 */
                var containsStrongRtlGlyphs: Boolean = false, /* 9 */
                /**
                 * bit 10 - This bit should be set if the font contains Indic-style rearrangement effects.
                 */
                var containsIndicStyleRearrangementEffects: Boolean = false, /* 10 */
                /**
                 * bit 14 - This bit should be set if the glyphs in the font are simply generic symbols for code point ranges, such as for a last resort font.
                 */
                var glyphsAreSimplyGenericSymbolsForCodePointRanges: Boolean = false /* 14 */
            )
            {
                companion object
                {
                    fun from(flag: UShort): Flags = Flags().apply {
                        yValueOf0SpecifiesBaseline = flag.toUInt() and (1U shl 0) == 1U shl 0
                        xPositionOfLeftMostBlackBitIsLsb = flag.toUInt() and (1U shl 1) == 1U shl 1
                        scaledPointSizeAndActualPointSizeDiffer = flag.toUInt() and (1U shl 2) == 1U shl 2
                        useIntegerScalingInsteadOfFractional = flag.toUInt() and (1U shl 3) == 1U shl 3
                        layoutVertically = flag.toUInt() and (1U shl 5) == 1U shl 5
                        requiresLayoutForCorrectLinguisticRendering = flag.toUInt() and (1U shl 7) == 1U shl 7
                        aatFontWhichHaveMetamorphosisEffects = flag.toUInt() and (1U shl 8) == 1U shl 8
                        containsStrongRtlGlyphs = flag.toUInt() and (1U shl 9) == 1U shl 9
                        containsIndicStyleRearrangementEffects = flag.toUInt() and (1U shl 10) == 1U shl 10
                        glyphsAreSimplyGenericSymbolsForCodePointRanges = flag.toUInt() and (1U shl 14) == 1U shl 14
                    }
                }
            }

            data class MacStyle(
                var bold: Boolean = false, /* 0 */
                var italic: Boolean = false, /* 1 */
                var underline: Boolean = false, /* 2 */
                var outline: Boolean = false, /* 3 */
                var shadow: Boolean = false, /* 4 */
                var condensed: Boolean = false, /* 5 */
                var extended: Boolean = false /* 6 */
            )
            {
                companion object
                {
                    fun from(macStyle: UShort) = MacStyle().apply {
                        bold = macStyle.toUInt() and (1U shl 0) == 1U shl 0
                        italic = macStyle.toUInt() and (1U shl 1) == 1U shl 1
                        underline = macStyle.toUInt() and (1U shl 2) == 1U shl 2
                        outline = macStyle.toUInt() and (1U shl 3) == 1U shl 3
                        shadow = macStyle.toUInt() and (1U shl 4) == 1U shl 4
                        condensed = macStyle.toUInt() and (1U shl 5) == 1U shl 5
                        extended = macStyle.toUInt() and (1U shl 6) == 1U shl 6
                    }
                }
            }

            enum class FontDirectionHint(val fontDirectionHint: Short)
            {
                Mixed(0),
                StrongLTR(1),
                StrongLTRWithNeutral(2),
                StrongRTL(-1),
                StrongRTLWithNeutral(-2);

                companion object
                {
                    fun from(fontDirectionHint: Short) = values().firstOrNull { it.fontDirectionHint == fontDirectionHint }?:Mixed
                }
            }

            enum class IndexToLocFormat(val indexToLocFormat: Short)
            {
                ShortOffset(0),
                LongOffset(1);

                companion object
                {
                    fun from(indexToLocFormat: Short) = values().firstOrNull { it.indexToLocFormat == indexToLocFormat }?:ShortOffset
                }
            }

            companion object
            {
                fun from(bytes: List<Byte>): HeadTable = HeadTable().apply {
                    val bs = ByteStream(bytes.toByteArray())
                    version = bs.readU4BE()
                    fontRevision = bs.readU4BE()
                    checkSumAdjustment = bs.readU4BE()
                    magicNumber = bs.readU4BE()
                    if (magicNumber != 0x5F0F3CF5U)
                    {
                        System.err.println("Warning: TTF.HeadTable.MagicNumber should be 0x5F0F3CF5 but actually ${magicNumber}")
                    }
                    flags = Flags.from(bs.readU2BE())
                    unitsPerEm = bs.readU2BE()
                    created = bs.readU8BE()
                    modified = bs.readU8BE()
                    xMin = bs.readS2BE()
                    yMin = bs.readS2BE()
                    xMax = bs.readS2BE()
                    yMax = bs.readS2BE()
                    macStyle = MacStyle.from(bs.readU2BE())
                    lowestRecPPEM = bs.readU2BE()
                    fontDirectionHint = FontDirectionHint.from(bs.readS2BE())
                    indexToLocFormat = IndexToLocFormat.from(bs.readS2BE())
                    glyphDataFormat = bs.readS2BE()
                }
            }
        }

        /**
         * [Horizontal Header Table](https://developer.apple.com/fonts/TrueType-Reference-Manual/RM06/Chap6hhea.html)
         */
        data class HheaTable(
            /**
             * 0x00010000 (1.0)
             */
            var version: UInt = 0x00010000U,
            /**
             * Distance from baseline of highest ascender
             */
            var ascent: Short = 0,
            /**
             * Distance from baseline of lowest descender
             */
            var descent: Short = 0,
            /**
             * typographic line gap
             */
            var lineGap: Short = 0,
            /**
             * must be consistent with horizontal metrics
             */
            var advanceWidthMax: UShort = 0U,
            /**
             * must be consistent with horizontal metrics
             */
            var minLeftSideBearing: Short = 0,
            /**
             * must be consistent with horizontal metrics
             */
            var minRightSideBearing: Short = 0,
            /**
             * max(lsb + (xMax-xMin))
             */
            var xMaxExtent: Short = 0,
            /**
             * used to calculate the slope of the caret (rise/run) set to 1 for vertical caret
             */
            var caretSlopeRise: Short = 0,
            /**
             * 0 for vertical
             */
            var caretSlopeRun: Short = 0,
            /**
             * set value to 0 for non-slanted fonts
             */
            var caretOffset: Short = 0,
            /**
             * set value to 0
             */
            var reserved0: Short = 0,
            /**
             * set value to 0
             */
            var reserved1: Short = 0,
            /**
             * set value to 0
             */
            var reserved2: Short = 0,
            /**
             * set value to 0
             */
            var reserved3: Short = 0,
            /**
             * 0 for current format
             */
            var metricDataFormat: Short = 0,
            /**
             * number of advance widths in metrics table
             */
            var numOfLongHorMetrics: UShort = 0U
        ) : ITable
        {
            companion object
            {
                fun from(bytes: List<Byte>): HheaTable = HheaTable().apply {
                    val bs = ByteStream(bytes.toByteArray())
                    version = bs.readU4BE()
                    ascent = bs.readS2BE()
                    descent = bs.readS2BE()
                    lineGap = bs.readS2BE()
                    advanceWidthMax = bs.readU2BE()
                    minLeftSideBearing = bs.readS2BE()
                    minRightSideBearing = bs.readS2BE()
                    xMaxExtent = bs.readS2BE()
                    caretSlopeRise = bs.readS2BE()
                    caretSlopeRun = bs.readS2BE()
                    caretOffset = bs.readS2BE()
                    reserved0 = bs.readS2BE()
                    reserved1 = bs.readS2BE()
                    reserved2 = bs.readS2BE()
                    reserved3 = bs.readS2BE()
                    metricDataFormat = bs.readS2BE()
                    numOfLongHorMetrics = bs.readU2BE()
                }
            }
        }

        enum class PredefinedTable(val tag: String)
        {
            ACNT("acnt"),
            ANKR("ankr"),
            AVAR("avar"),
            BDAT("bdat"),
            BHED("bhed"),
            BLOC("bloc"),
            BSLN("bsln"),
            CMAP("cmap"),
            CVAR("cvar"),
            CVT ("cvt "),
            EBSC("EBSC"),
            FDSC("fdsc"),
            FEAT("feat"),
            FMTX("fmtx"),
            FOND("fond"),
            FPGM("fpgm"),
            FVAR("fvar"),
            GASP("gasp"),
            GCID("gcid"),
            GLYF("glyf"),
            GVAR("gvar"),
            HDMX("hdmx"),
            HEAD("head"),
            HHEA("hhea"),
            HMTX("hmtx"),
            JUST("just"),
            KERN("kern"),
            KERX("kerx"),
            LCAR("lcar"),
            LOCA("loca"),
            LTAG("ltag"),
            MAXP("maxp"),
            META("meta"),
            MORT("mort"),
            MORX("morx"),
            NAME("name"),
            OPBD("opbd"),
            OS2 ("OS/2"),
            POST("post"),
            PREP("prep"),
            PROP("prop"),
            SBIX("sbix"),
            TRAK("trak"),
            VHEA("vhea"),
            VMTX("vmtx"),
            XREF("xref"),
            ZAPF("Zapf");

            companion object
            {
                fun fromValue(value: String) = values().firstOrNull { it.tag == value }
            }
        }

        fun getTableByTag(predefined: PredefinedTable) = getTableByTag(predefined.tag)

        fun getTableByTag(tag: String) = tables[tag]
    }

    override fun deserialize(file: File): Struct = Struct().apply {
        val fr = ByteStream(file.readBytes())
        fontDirectory.offsetSubtable.scalerType = Struct.FontDirectory.OffsetSubtable.ScalerType.from(fr.readU4BE())
        fontDirectory.offsetSubtable.numTables = fr.readU2BE()
        fontDirectory.offsetSubtable.searchRange = fr.readU2BE()
        fontDirectory.offsetSubtable.entrySelector = fr.readU2BE()
        fontDirectory.offsetSubtable.rangeShift = fr.readU2BE()
        repeat(fontDirectory.offsetSubtable.numTables.toInt()) {
            fontDirectory.tableDirectories.add(
                Struct.FontDirectory.TableDirectoryEntry(
                    tag = fr.readC4(),
                    checksum = fr.readU4BE(),
                    offset = fr.readU4BE(),
                    length = fr.readU4BE()
                )
            )
        }
        fontDirectory.tableDirectories.forEach { tableDirectory ->
            val tableBytes = fr.range(tableDirectory.offset, tableDirectory.offset + tableDirectory.length)
            tables[tableDirectory.tag] = when (Struct.PredefinedTable.fromValue(tableDirectory.tag))
            {
                Struct.PredefinedTable.HEAD -> Struct.HeadTable.from(tableBytes)
                Struct.PredefinedTable.HHEA -> Struct.HheaTable.from(tableBytes)
                else -> Struct.CommonTable(tableBytes)
            }
        }
    }

    override fun serialize(struct: UFI.Struct): ByteArray
    {
        TODO("Not yet implemented")
    }

}
