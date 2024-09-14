package com.lockermanwxlf.otp

import java.math.BigInteger
import java.nio.ByteBuffer
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class Hotp {
    companion object {
        internal fun dynamicTruncate(bytes: ByteArray): Int {
            val offset = bytes.last().toInt() and 15
            return (bytes[offset].toInt() and 127 shl 24) or
                    (bytes[offset + 1].toInt() and 255 shl 16) or
                    (bytes[offset + 2].toInt() and 255 shl 8) or
                    (bytes[offset + 3].toInt() and 255)
        }

        fun generateHotp(key: ByteArray, counter: Long, digits: Int, algorithm: String): Int {
            val mac = Mac.getInstance("Hmac$algorithm")
            val k = SecretKeySpec(key, algorithm)
            mac.init(k)
            val counterAsBytes = ByteBuffer.allocate(Long.SIZE_BYTES)
                .putLong(counter)
                .array()
            val hmacValue = mac.doFinal(counterAsBytes);
            return dynamicTruncate(hmacValue).toBigInteger().mod(BigInteger.TEN.pow(digits)).toInt();
        }
    }
}