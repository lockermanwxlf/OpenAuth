package com.lockermanwxlf.openauth.util

import android.content.Context
import android.net.Uri
import java.io.InputStream

class AndroidInputStreamProvider(
    private val context: Context
): InputStreamProvider {
    override fun getInputStream(filename: String): InputStream? {
        return context.contentResolver.openInputStream(Uri.parse(filename))
    }
}