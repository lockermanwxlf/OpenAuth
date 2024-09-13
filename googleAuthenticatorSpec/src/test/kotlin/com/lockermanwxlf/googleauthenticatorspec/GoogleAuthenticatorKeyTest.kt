package com.lockermanwxlf.googleauthenticatorspec

import org.junit.Test
import org.testng.Assert.*
import java.net.URI

class GoogleAuthenticatorKeyTest {
    @Test
    fun testAgainstGoogleExample() {
        val uri = URI.create("otpauth://totp/ACME%20Co:john.doe@email.com?secret=HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ&issuer=ACME%20Co&algorithm=SHA1&digits=6&period=30")
        val expected = GoogleAuthenticatorKey(
            GoogleAuthenticatorKeyLabel("john.doe@email.com", "ACME Co"),
            "HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ",
            "ACME Co",
            "SHA1",
            6,
            30
        )

        assertEquals(expected, GoogleAuthenticatorKey.fromURI(uri))
    }
}