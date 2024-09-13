package com.lockermanwxlf.openauth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform