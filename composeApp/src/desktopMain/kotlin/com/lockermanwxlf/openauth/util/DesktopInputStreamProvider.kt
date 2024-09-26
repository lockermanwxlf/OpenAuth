package com.lockermanwxlf.openauth.util

import java.io.FileInputStream
import java.io.InputStream

class DesktopInputStreamProvider: InputStreamProvider {
    override fun getInputStream(filename: String): InputStream? {
        return FileInputStream(filename)
    }
}