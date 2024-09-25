package com.lockermanwxlf.openauth.ocr

import org.junit.Test
import org.testng.Assert.assertEquals
import java.io.FileInputStream

class OcrTest {
    @Test
    fun testQRReaderAgainstDigitalImage() {
        val path = javaClass.getResource("/digital_qr.png")!!.path
        val fis = FileInputStream(path)
        val result = Ocr.readQR(fis)

        assertEquals(result, "otpauth://totp/totp@authenticationtest.com?secret=I65VU7K5ZQL7WB4E")
    }
}