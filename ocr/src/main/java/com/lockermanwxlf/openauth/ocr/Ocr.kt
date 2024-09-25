package com.lockermanwxlf.openauth.ocr

import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.io.FileInputStream
import javax.imageio.ImageIO

class Ocr {
    companion object {
        fun readQR(fis: FileInputStream): String? {
            val image = ImageIO.read(fis)
            val source = BufferedImageLuminanceSource(image)
            val hybridBinarizer = HybridBinarizer(source)
            val binaryBitmap = BinaryBitmap(hybridBinarizer)
            val result = MultiFormatReader().decode(binaryBitmap)
            return result?.text
        }
    }
}