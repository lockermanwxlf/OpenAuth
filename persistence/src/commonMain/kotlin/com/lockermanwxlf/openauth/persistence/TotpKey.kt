package com.lockermanwxlf.openauth.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "totpKeys")
data class TotpKey(
    @PrimaryKey val key: ByteArray,
    val digits: Int,
    val period: Int,
    val label: String,
    val algorithm: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TotpKey

        if (!key.contentEquals(other.key)) return false
        if (digits != other.digits) return false
        if (period != other.period) return false
        if (label != other.label) return false
        if (algorithm != other.algorithm) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.contentHashCode()
        result = 31 * result + digits
        result = 31 * result + period
        result = 31 * result + label.hashCode()
        result = 31 * result + algorithm.hashCode()
        return result
    }
}
