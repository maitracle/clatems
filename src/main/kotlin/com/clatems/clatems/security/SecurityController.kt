package com.clatems.clatems.security

import com.clatems.clatems.users.User
import com.clatems.clatems.users.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/security")
class SecurityController(private val userRepository: UserRepository, private val tokenProvider: TokenProvider) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto): String {

        val target: User = userRepository.getByEmail(loginRequestDto.email)
            ?: throw UserNotFoundException("User not found")

        if (target.email == loginRequestDto.email &&
            target.password == loginRequestDto.password) {
            return tokenProvider.generateToken(loginRequestDto)
        }
        else{
            throw WrongPassword("Wrong password")
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class UserNotFoundException(message: String?) : RuntimeException(message)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class WrongPassword(message: String?) : RuntimeException(message)
}