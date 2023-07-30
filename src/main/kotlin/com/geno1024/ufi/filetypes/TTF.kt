package com.geno1024.ufi.filetypes

import com.geno1024.ufi.ByteStream
import com.geno1024.ufi.UFI
import java.io.File

/**
 * True Type Font
 */
object TTF : UFI
{
    object DataTypes
    {
        class ShortFrac(raw: Short)

        class LongDateTime(raw: Long)
        {
//            val temporal = LocalDateTime.ofEpochSecond(
//                raw - (
//                    LocalDateTime.of(1970, 1, 1, 0, 0, 0).minusYears(1970 - 1904).adjustInto()
//                    ),
//                0,
//                ZoneOffset.UTC
//            )
//
//            override fun toString(): String =

        /* : Temporal
        {
            override fun isSupported(unit: TemporalUnit?): Boolean = when (unit)
            {
                ChronoUnit.NANOS -> false
                ChronoUnit.MICROS -> false
                ChronoUnit.MILLIS -> false
                ChronoUnit.SECONDS -> true
                ChronoUnit.MINUTES -> true
                ChronoUnit.HOURS -> true
                ChronoUnit.HALF_DAYS -> true
                ChronoUnit.DAYS -> true
                ChronoUnit.WEEKS -> true
                ChronoUnit.MONTHS -> true
                ChronoUnit.YEARS -> true
                ChronoUnit.DECADES -> true
                ChronoUnit.CENTURIES -> true
                ChronoUnit.MILLENNIA -> true
                ChronoUnit.ERAS -> true
                ChronoUnit.FOREVER -> false
                else -> false
            }

            /**
             * Checks if the specified field is supported.
             *
             *
             * This checks if the date-time can be queried for the specified field.
             * If false, then calling the [range][.range] and [get][.get]
             * methods will throw an exception.
             *
             * @implSpec
             * Implementations must check and handle all fields defined in [ChronoField].
             * If the field is supported, then true must be returned, otherwise false must be returned.
             *
             *
             * If the field is not a `ChronoField`, then the result of this method
             * is obtained by invoking `TemporalField.isSupportedBy(TemporalAccessor)`
             * passing `this` as the argument.
             *
             *
             * Implementations must ensure that no observable state is altered when this
             * read-only method is invoked.
             *
             * @param field  the field to check, null returns false
             * @return true if this date-time can be queried for the field, false if not
             */
            override fun isSupported(field: TemporalField?): Boolean = when (field)
            {
                ChronoField.NANO_OF_SECOND -> false
                ChronoField.NANO_OF_DAY -> false
                ChronoField.MICRO_OF_SECOND -> false
                ChronoField.MICRO_OF_DAY -> false
                ChronoField.MILLI_OF_SECOND -> false
                ChronoField.MILLI_OF_DAY -> false
                ChronoField.SECOND_OF_MINUTE -> true
                ChronoField.SECOND_OF_DAY -> true
                ChronoField.MINUTE_OF_HOUR -> true
                ChronoField.MINUTE_OF_DAY -> true
                ChronoField.HOUR_OF_AMPM -> true
                ChronoField.CLOCK_HOUR_OF_AMPM -> true
                ChronoField.HOUR_OF_DAY -> true
                ChronoField.CLOCK_HOUR_OF_DAY -> true
                ChronoField.AMPM_OF_DAY -> true
                ChronoField.DAY_OF_WEEK -> true
                ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH -> true
                ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR -> true
                ChronoField.DAY_OF_MONTH -> true
                ChronoField.DAY_OF_YEAR -> true
                ChronoField.EPOCH_DAY -> true
                ChronoField.ALIGNED_WEEK_OF_MONTH -> true
                ChronoField.ALIGNED_WEEK_OF_YEAR -> true
                ChronoField.MONTH_OF_YEAR -> true
                ChronoField.PROLEPTIC_MONTH -> true
                ChronoField.YEAR_OF_ERA -> true
                ChronoField.YEAR -> true
                ChronoField.ERA -> true
                ChronoField.INSTANT_SECONDS -> true
                ChronoField.OFFSET_SECONDS -> true
            }

            /**
             * Gets the value of the specified field as a `long`.
             *
             *
             * This queries the date-time for the value of the specified field.
             * The returned value may be outside the valid range of values for the field.
             * If the date-time cannot return the value, because the field is unsupported or for
             * some other reason, an exception will be thrown.
             *
             * @implSpec
             * Implementations must check and handle all fields defined in [ChronoField].
             * If the field is supported, then the value of the field must be returned.
             * If unsupported, then an `UnsupportedTemporalTypeException` must be thrown.
             *
             *
             * If the field is not a `ChronoField`, then the result of this method
             * is obtained by invoking `TemporalField.getFrom(TemporalAccessor)`
             * passing `this` as the argument.
             *
             *
             * Implementations must ensure that no observable state is altered when this
             * read-only method is invoked.
             *
             * @param field  the field to get, not null
             * @return the value for the field
             * @throws DateTimeException if a value for the field cannot be obtained
             * @throws UnsupportedTemporalTypeException if the field is not supported
             * @throws ArithmeticException if numeric overflow occurs
             */
            override fun getLong(field: TemporalField?): Long
            {
                TODO("Not yet implemented")
            }

            /**
             * Returns an object of the same type as this object with the specified field altered.
             *
             *
             * This returns a new object based on this one with the value for the specified field changed.
             * For example, on a `LocalDate`, this could be used to set the year, month or day-of-month.
             * The returned object will have the same observable type as this object.
             *
             *
             * In some cases, changing a field is not fully defined. For example, if the target object is
             * a date representing the 31st January, then changing the month to February would be unclear.
             * In cases like this, the field is responsible for resolving the result. Typically it will choose
             * the previous valid date, which would be the last valid day of February in this example.
             *
             * @implSpec
             * Implementations must check and handle all fields defined in [ChronoField].
             * If the field is supported, then the adjustment must be performed.
             * If unsupported, then an `UnsupportedTemporalTypeException` must be thrown.
             *
             *
             * If the field is not a `ChronoField`, then the result of this method
             * is obtained by invoking `TemporalField.adjustInto(Temporal, long)`
             * passing `this` as the first argument.
             *
             *
             * Implementations must not alter this object.
             * Instead, an adjusted copy of the original must be returned.
             * This provides equivalent, safe behavior for immutable and mutable implementations.
             *
             * @param field  the field to set in the result, not null
             * @param newValue  the new value of the field in the result
             * @return an object of the same type with the specified field set, not null
             * @throws DateTimeException if the field cannot be set
             * @throws UnsupportedTemporalTypeException if the field is not supported
             * @throws ArithmeticException if numeric overflow occurs
             */
            override fun with(field: TemporalField?, newValue: Long): Temporal
            {
                TODO("Not yet implemented")
            }

            /**
             * Returns an object of the same type as this object with the specified period added.
             *
             *
             * This method returns a new object based on this one with the specified period added.
             * For example, on a `LocalDate`, this could be used to add a number of years, months or days.
             * The returned object will have the same observable type as this object.
             *
             *
             * In some cases, changing a field is not fully defined. For example, if the target object is
             * a date representing the 31st January, then adding one month would be unclear.
             * In cases like this, the field is responsible for resolving the result. Typically it will choose
             * the previous valid date, which would be the last valid day of February in this example.
             *
             * @implSpec
             * Implementations must check and handle all units defined in [ChronoUnit].
             * If the unit is supported, then the addition must be performed.
             * If unsupported, then an `UnsupportedTemporalTypeException` must be thrown.
             *
             *
             * If the unit is not a `ChronoUnit`, then the result of this method
             * is obtained by invoking `TemporalUnit.addTo(Temporal, long)`
             * passing `this` as the first argument.
             *
             *
             * Implementations must not alter this object.
             * Instead, an adjusted copy of the original must be returned.
             * This provides equivalent, safe behavior for immutable and mutable implementations.
             *
             * @param amountToAdd  the amount of the specified unit to add, may be negative
             * @param unit  the unit of the amount to add, not null
             * @return an object of the same type with the specified period added, not null
             * @throws DateTimeException if the unit cannot be added
             * @throws UnsupportedTemporalTypeException if the unit is not supported
             * @throws ArithmeticException if numeric overflow occurs
             */
            override fun plus(amountToAdd: Long, unit: TemporalUnit?): Temporal
            {
                TODO("Not yet implemented")
            }

            /**
             * Calculates the amount of time until another temporal in terms of the specified unit.
             *
             *
             * This calculates the amount of time between two temporal objects
             * in terms of a single `TemporalUnit`.
             * The start and end points are `this` and the specified temporal.
             * The end point is converted to be of the same type as the start point if different.
             * The result will be negative if the end is before the start.
             * For example, the amount in hours between two temporal objects can be
             * calculated using `startTime.until(endTime, HOURS)`.
             *
             *
             * The calculation returns a whole number, representing the number of
             * complete units between the two temporals.
             * For example, the amount in hours between the times 11:30 and 13:29
             * will only be one hour as it is one minute short of two hours.
             *
             *
             * There are two equivalent ways of using this method.
             * The first is to invoke this method directly.
             * The second is to use [TemporalUnit.between]:
             * <pre>
             * // these two lines are equivalent
             * temporal = start.until(end, unit);
             * temporal = unit.between(start, end);
            </pre> *
             * The choice should be made based on which makes the code more readable.
             *
             *
             * For example, this method allows the number of days between two dates to
             * be calculated:
             * <pre>
             * long daysBetween = start.until(end, DAYS);
             * // or alternatively
             * long daysBetween = DAYS.between(start, end);
            </pre> *
             *
             * @implSpec
             * Implementations must begin by checking to ensure that the input temporal
             * object is of the same observable type as the implementation.
             * They must then perform the calculation for all instances of [ChronoUnit].
             * An `UnsupportedTemporalTypeException` must be thrown for `ChronoUnit`
             * instances that are unsupported.
             *
             *
             * If the unit is not a `ChronoUnit`, then the result of this method
             * is obtained by invoking `TemporalUnit.between(Temporal, Temporal)`
             * passing `this` as the first argument and the converted input temporal as
             * the second argument.
             *
             *
             * In summary, implementations must behave in a manner equivalent to this pseudo-code:
             * <pre>
             * // convert the end temporal to the same type as this class
             * if (unit instanceof ChronoUnit) {
             * // if unit is supported, then calculate and return result
             * // else throw UnsupportedTemporalTypeException for unsupported units
             * }
             * return unit.between(this, convertedEndTemporal);
            </pre> *
             *
             *
             * Note that the unit's `between` method must only be invoked if the
             * two temporal objects have exactly the same type evaluated by `getClass()`.
             *
             *
             * Implementations must ensure that no observable state is altered when this
             * read-only method is invoked.
             *
             * @param endExclusive  the end temporal, exclusive, converted to be of the
             * same type as this object, not null
             * @param unit  the unit to measure the amount in, not null
             * @return the amount of time between this temporal object and the specified one
             * in terms of the unit; positive if the specified object is later than this one,
             * negative if it is earlier than this one
             * @throws DateTimeException if the amount cannot be calculated, or the end
             * temporal cannot be converted to the same type as this temporal
             * @throws UnsupportedTemporalTypeException if the unit is not supported
             * @throws ArithmeticException if numeric overflow occurs
             */
            override fun until(endExclusive: Temporal?, unit: TemporalUnit?): Long
            {
                TODO("Not yet implemented")
            }
*/
        }
    }

    /**
     * described from [Apple](https://developer.apple.com/fonts/TrueType-Reference-Manual/RM06/Chap6.html).
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
        var tableDirectories: MutableList<TableDirectory> = mutableListOf(),
        var tables: MutableMap<String, ITable> = mutableMapOf()
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

        interface ITable

        data class NormalTable(var bytes: List<Byte>) : ITable

        /**
         * [The 'head' table](https://developer.apple.com/fonts/TrueType-Reference-Manual/RM06/Chap6head.html)
         */
        data class HeadTable(
            /**
             * 0x00010000 if (version 1.0)
             */
            var version: UInt = 0U,
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
        ) : ITable
        {
            data class Flags(
                var yValueOf0SpecifiesBaseline: Boolean = false,
            )
            {

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
        tableDirectories.forEach { tableDirectory ->
            tables[tableDirectory.tag] = when (Struct.PredefinedTable.fromValue(tableDirectory.tag))
            {
                Struct.PredefinedTable.HEAD -> Struct.HeadTable.from(fr.range(tableDirectory.offset, tableDirectory.offset + tableDirectory.length))
                else -> Struct.NormalTable(fr.range(tableDirectory.offset, tableDirectory.offset + tableDirectory.length))
            }
        }
    }

    override fun serialize(struct: UFI.Struct): ByteArray
    {
        TODO("Not yet implemented")
    }
}
