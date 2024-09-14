package com.lockermanwxlf.googleauthenticatorspec

data class GoogleAuthenticatorKeyLabel(
    val accountName: String,
    val issuer: String?
) {
    companion object {
        internal fun fromUriPath(path: String): GoogleAuthenticatorKeyLabel {
            val pattern = "(?<issuer>.+)(?:%3A|:)(?:%20| )*(?<accountName>.+)|(?<accountName2>.+)"
            val regex = Regex(pattern)
            val result = regex.matchEntire(path)!!
            return GoogleAuthenticatorKeyLabel(
                result.groups["accountName"]?.value ?: result.groups["accountName2"]!!.value,
                result.groups["issuer"]?.value
            )
        }
    }
}