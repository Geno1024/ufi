package com.geno1024.ufi

class ByteStream(var d: ByteArray)
{
    var ptr = 0

    fun readS1LE() = d[ptr++]
    fun readS2LE() = (d[ptr++] + (d[ptr++].toInt() shl 8)).toShort()
    fun readS4LE() = d[ptr++] + (d[ptr++].toInt() shl 8) + (d[ptr++].toInt() shl 16) + (d[ptr++].toInt() shl 24)
    fun readS8LE() = d[ptr++] + (d[ptr++].toInt() shl 8) + (d[ptr++].toInt() shl 16) + (d[ptr++].toInt() shl 24) + (d[ptr++] + (d[ptr++].toInt() shl 8) + (d[ptr++].toInt() shl 16) + (d[ptr++].toInt() shl 24)) * 0x100000000

    fun readU1LE() = d[ptr++].toUByte()
    fun readU2LE() = (d[ptr++].toUByte().toInt() + (d[ptr++].toUByte().toInt() shl 8)).toUShort()
    fun readU4LE() = (d[ptr++].toUByte().toInt() + (d[ptr++].toUByte().toInt() shl 8) + (d[ptr++].toUByte().toInt() shl 16) + (d[ptr++].toUByte().toInt() shl 24)).toUInt()
    fun readU8LE() = (d[ptr++].toUByte().toInt() + (d[ptr++].toUByte().toInt() shl 8) + (d[ptr++].toUByte().toInt() shl 16) + (d[ptr++].toUByte().toInt() shl 24) + (d[ptr++].toUByte().toInt() + (d[ptr++].toUByte().toInt() shl 8) + (d[ptr++].toUByte().toInt() shl 16) + (d[ptr++].toUByte().toInt() shl 24)) * 0x100000000).toULong()

    fun readS1BE() = d[ptr++]
    fun readS2BE() = ((d[ptr++].toInt() shl 8) + d[ptr++]).toShort()
    fun readS4BE() = (d[ptr++].toInt() shl 24) + (d[ptr++].toInt() shl 16) + (d[ptr++].toInt() shl 8) + d[ptr++]
    fun readS8BE() = ((d[ptr++].toInt() shl 24) + (d[ptr++].toInt() shl 16) + (d[ptr++].toInt() shl 8) + d[ptr++]) * 0x100000000 + (d[ptr++].toInt() shl 24) + (d[ptr++].toInt() shl 16) + (d[ptr++].toInt() shl 8) + d[ptr++]

    fun readU1BE() = d[ptr++].toUByte()
    fun readU2BE() = ((d[ptr++].toUByte().toInt() shl 8) + d[ptr++].toUByte().toInt()).toUShort()
    fun readU4BE() = ((d[ptr++].toUByte().toInt() shl 24) + (d[ptr++].toUByte().toInt() shl 16) + (d[ptr++].toUByte().toInt() shl 8) + d[ptr++].toUByte().toInt()).toUInt()
    fun readU8BE() = (((d[ptr++].toUByte().toInt() shl 24) + (d[ptr++].toUByte().toInt() shl 16) + (d[ptr++].toUByte().toInt() shl 8) + d[ptr++].toUByte().toInt()) * 0x100000000 + (d[ptr++].toUByte().toInt() shl 24) + (d[ptr++].toUByte().toInt() shl 16) + (d[ptr++].toUByte().toInt() shl 8) + d[ptr++].toUByte().toInt()).toULong()

    fun readC4() = d[ptr++].toInt().toChar().toString() + d[ptr++].toInt().toChar().toString() + d[ptr++].toInt().toChar().toString() + d[ptr++].toInt().toChar().toString()

    fun range(from: Int, to: Int) = d.slice(from..to)
    fun range(from: UInt, to: UInt) = d.slice(from.toInt() until to.toInt())
}
