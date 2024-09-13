package com.lockermanwxlf.otp

import org.junit.Test
import org.testng.Assert.*

class TotpTest {
    @Test
    fun testAgainstPrecomputedKeyAndTime() {
        val key = arrayOf(
            0x47, 0xbb, 0x5a, 0x7d, 0x5d, 0xcc, 0x17, 0xfb, 0x07, 0x84
        ).map { it.toByte() }.toByteArray()
        val time = 1726201067L
        val period = 30

        assertEquals(988052, Totp.generateTotp(key, time, period, 6, "Sha1"))
    }
}