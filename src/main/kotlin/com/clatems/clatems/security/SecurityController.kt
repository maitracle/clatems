package com.clatems.clatems.security

import com.clatems.clatems.users.User
import com.clatems.clatems.users.UserRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/security")
class SecurityController(private val userRepository: UserRepository, private val tokenProvider: TokenProvider) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto): String {
        val target: User = userRepository.getById(loginRequestDto.id)

        if (target.email == loginRequestDto.email && target.password == loginRequestDto.password) {
            return tokenProvider.generateToken(loginRequestDto)
        }
        return ""
    }
}