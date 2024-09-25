package com.lockermanwxlf.openauth.ocr

import java.io.InputStream

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Ocr {
    companion object {
        fun readQR(inputStream: InputStream): String?
    }
}