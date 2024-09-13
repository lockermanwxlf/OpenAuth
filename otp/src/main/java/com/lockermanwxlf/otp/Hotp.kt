package com.lockermanwxlf.otp

import kotlin.experimental.and

class Hotp {
    companion object {
        internal fun dynamicTruncate(bytes: ByteArray): Int {
            val offset = bytes.last().toInt() and 15
            return (bytes[offset].toInt() and 127 shl 24) or
                    (bytes[offset + 1].toInt() and 255 shl 16) or
                    (bytes[offset + 2].toInt() and 255 shl 8) or
                    (bytes[offset + 3].toInt() and 255)
        }
    }
}