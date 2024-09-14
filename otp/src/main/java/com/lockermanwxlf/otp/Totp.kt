package com.lockermanwxlf.otp

class Totp {
    companion object {
        fun generateTotp(key: ByteArray, time: Long, period: Int, digits: Int, algorithm: String)
            = Hotp.generateHotp(key, time / period, digits, algorithm)
    }
}