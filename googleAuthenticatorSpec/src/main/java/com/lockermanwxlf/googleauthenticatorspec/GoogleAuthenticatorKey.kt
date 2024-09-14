package com.lockermanwxlf.googleauthenticatorspec

import java.net.URI

data class GoogleAuthenticatorKey(
    val label: GoogleAuthenticatorKeyLabel,
    val secret: String,
    val issuer: String?,
    val algorithm: String?,
    val digits: Int?,
    val period: Int?
) {
    companion object {
        fun fromURI(uri: URI): GoogleAuthenticatorKey {
            val queryParams = uri.query.split("&").map { keyPair ->
                keyPair.split("=").let {
                    Pair(it[0], it[1])
                }
            }.toMap()

            return GoogleAuthenticatorKey(
                GoogleAuthenticatorKeyLabel.fromUriPath(uri.path.drop(1)),
                queryParams["secret"]!!,
                queryParams["issuer"],
                queryParams["algorithm"],
                queryParams["digits"]?.toInt(),
                queryParams["period"]?.toInt()
            )
        }
    }
}