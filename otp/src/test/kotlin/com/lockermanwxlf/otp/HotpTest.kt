package com.lockermanwxlf.otp

import org.junit.Test
import org.testng.Assert.*

class HotpTest {
    @Test
    fun testDynamicTruncateAgainstRFC4225Example() {
        val bytes = arrayOf(
            0x1f, 0x86, 0x98, 0x69, 0x0e, 0x02, 0xca, 0x16, 0x61, 0x85,
            0x50, 0xef, 0x7f, 0x19, 0xda, 0x8e, 0x94, 0x5b, 0x55, 0x5a
        ).map { it.toByte() }.toByteArray()

        assertEquals(Hotp.dynamicTruncate(bytes), 0x50ef7f19)
    }

    @Test
    fun testAgainstPrecomputedKeyAndCounter() {
        val key = arrayOf(
            0x47, 0xbb, 0x5a, 0x7d, 0x5d, 0xcc, 0x17, 0xfb, 0x07, 0x84
        ).map { it.toByte() }.toByteArray()
        val counter = 57540023L

        assertEquals(711559, Hotp.generateHotp(key, counter, 6, "Sha1"))
    }
}