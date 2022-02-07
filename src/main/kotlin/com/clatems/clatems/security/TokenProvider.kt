package com.clatems.clatems.security

import com.clatems.clatems.users.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.servlet.http.HttpServletRequest
import javax.xml.bind.DatatypeConverter


@RequiredArgsConstructor
@Component
class TokenProvider(
    private val userService: UserService
) {
    private var secretKeyBytes: ByteArray
    private var signingKey: Key

    companion object {
        const val AUTHORITIES_KEY: String = "auth"
        const val USER_PK: String = "user_pk"
        const val BEARER_TYPE: String = "Bearer"
        const val ACCESS_TOKEN_EXPIRE_TIME: Long = 1000 * 60 * 30 * 24 * 7 * 4
        const val REFRESH_TOKEN_EXPIRE_TIME: Long = 1000 * 60 * 60 * 24 * 7
        const val SECRET_KEY: String =
            "thisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdfthisissecretkeyasdf"
    }

    init {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS512
        val secretKeyBytes: ByteArray = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey: Key = SecretKeySpec(secretKeyBytes, signatureAlgorithm.jcaName)
        this.secretKeyBytes = secretKeyBytes
        this.signingKey = signingKey
    }

    fun generateToken(userId: Long): String {
        val now: Long = Date().time
        val accessTokenExpiresIn = Date(now + ACCESS_TOKEN_EXPIRE_TIME)
        val subject = "access_token"

        return Jwts.builder()
            .setSubject(subject)
            .claim(USER_PK, userId)
            .setExpiration(accessTokenExpiresIn)
            .signWith(signingKey, SignatureAlgorithm.HS512)
            .compact()
    }

    private fun getClaims(token: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(this.secretKeyBytes)
            .build()
            .parseClaimsJws(token)
    }

    fun resolveToken(req: HttpServletRequest): String? {
        return req.getHeader("Authorization")
            ?.replace("$BEARER_TYPE ", "")
    }

    fun validateToken(token: String?): Boolean {
        return try {
            val claims: Jws<Claims> = getClaims(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getUserPk(token: String?): Long? {
        val pkBaseValue: Int = getClaims(token).body[USER_PK] as Int?
            ?: throw Exception("User Pk is not exist in token.")

        return pkBaseValue.toLong()
    }

    fun getAuthentication(token: String?): Authentication {
        val userDetails = userService.getById(this.getUserPk(token)!!)
        userDetails ?: throw Exception("user is not exist.")

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }
}
