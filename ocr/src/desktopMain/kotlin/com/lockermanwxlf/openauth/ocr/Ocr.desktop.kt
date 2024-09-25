package com.lockermanwxlf.openauth.ocr

import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.io.InputStream
import javax.imageio.ImageIO

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Ocr {
    actual companion object {
        actual fun readQR(inputStream: InputStream): String? {
            val image = ImageIO.read(inputStream)
            val source = BufferedImageLuminanceSource(image)
            val binarizer = HybridBinarizer(source)
            val bitmap = BinaryBitmap(binarizer)
            return MultiFormatReader().decode(bitmap)?.text
        }
    }
}