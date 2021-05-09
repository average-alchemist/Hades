package io.aethibo.utils

import io.ktor.util.*
import io.ktor.utils.io.charsets.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

val hashKey = hex("803d97ef794c9480c0eede6f8197")

val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")

fun hash(password: String): String {
    val hmac = Mac.getInstance("HmacSHA1")
    hmac.init(hmacKey)

    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}