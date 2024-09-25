package com.lockermanwxlf.openauth.ocr

import java.io.FileInputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class OcrTest {
    @Test
    fun testQRReaderAgainstDigitalImage() {
        val path = javaClass.getResource("/digital_qr.png")!!.path
        val fis = FileInputStream(path)
        val result = Ocr.readQR(fis)

        assertEquals(result, "otpauth://totp/totp@authenticationtest.com?secret=I65VU7K5ZQL7WB4E")
    }
}