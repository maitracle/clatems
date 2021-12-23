package com.clatems.clatems.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter


@RequiredArgsConstructor
@Component
class TokenProvider {
    private var signingKey: Key

    companion object {
        const val AUTHORITIES_KEY: String = "auth"
        const val USER_PK: String = "user_pk"
        const val BEARER_TYPE: String = "bearer"
        const val ACCESS_TOKEN_EXPIRE_TIME: Long = 1000 * 60 * 30
        const val REFRESH_TOKEN_EXPIRE_TIME: Long = 1000 * 60 * 60 * 24 * 7
        const val SECRET_KEY: String =
            "thisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdf"
    }

    init {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS512
        val secretKeyBytes: ByteArray = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey: Key = SecretKeySpec(secretKeyBytes, signatureAlgorithm.jcaName)
        this.signingKey = signingKey
    }

    fun generateToken(user: LoginRequestDto): String {
        val now: Long = Date().time
        val accessTokenExpiresIn = Date(now + ACCESS_TOKEN_EXPIRE_TIME)
        val subject = "access_token"

        return Jwts.builder()
            .setSubject(subject)
            .claim(USER_PK, user.id)
            .setExpiration(accessTokenExpiresIn)
            .signWith(signingKey, SignatureAlgorithm.HS512)
            .compact()
    }
}