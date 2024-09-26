package com.lockermanwxlf.openauth.util

import java.io.InputStream

interface InputStreamProvider {
    fun getInputStream(filename: String): InputStream?
}