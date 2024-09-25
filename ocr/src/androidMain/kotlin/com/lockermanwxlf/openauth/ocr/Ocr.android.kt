package com.lockermanwxlf.openauth.ocr

import android.graphics.BitmapFactory
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.io.InputStream

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Ocr {
    actual companion object {
        actual fun readQR(inputStream: InputStream): String? {
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val pixels = IntArray(bitmap.width * bitmap.height).apply {
                bitmap.getPixels(this, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
            }
            val source = RGBLuminanceSource(bitmap.width, bitmap.height, pixels)
            val binarizer = HybridBinarizer(source)
            val binaryBitmap = BinaryBitmap(binarizer)
            return MultiFormatReader().decode(binaryBitmap)?.text
        }
    }
}